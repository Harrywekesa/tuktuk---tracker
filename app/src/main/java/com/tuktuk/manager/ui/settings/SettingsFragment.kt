package com.tuktuk.manager.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.tuktuk.manager.data.local.entity.AppSettings
import com.tuktuk.manager.data.repository.TukTukRepository
import com.tuktuk.manager.databinding.FragmentSettingsBinding
import com.tuktuk.manager.util.ViewModelFactory
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(private val repo: TukTukRepository) : ViewModel() {

    val settings: StateFlow<AppSettings?> = repo.getSettings()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    init {
        viewModelScope.launch {
            val all = repo.getAllEntries().first()
            val grouped = all.groupBy { it.date }
            grouped.forEach { (_, dupes) ->
                if (dupes.size > 1) {
                    dupes.drop(1).forEach { repo.deleteEntry(it) }
                }
            }
        }
    }

    fun saveSettings(settings: AppSettings) {
        viewModelScope.launch { repo.saveSettings(settings) }
    }

    fun syncNow() {
        viewModelScope.launch {
            _syncState.value = SyncState.Syncing
            try {
                repo.syncUnsynced()
                _syncState.value = SyncState.Success("Synced to cloud ✓")
            } catch (e: Exception) {
                _syncState.value = SyncState.Error("Sync failed: ${e.message}")
            }
        }
    }

    fun setTheme(mode: String) {
        viewModelScope.launch {
            val current = settings.value ?: AppSettings()
            repo.saveSettings(current.copy(themeMode = mode))
            val delegate = when (mode) {
                "light" -> AppCompatDelegate.MODE_NIGHT_NO
                "dark" -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
            AppCompatDelegate.setDefaultNightMode(delegate)
        }
    }

    fun importLegacyData(jsonEntries: String, jsonExpenses: String) {
        viewModelScope.launch {
            _syncState.value = SyncState.Syncing
            try {
                val existingDates = repo.getAllEntries().first().map { it.date }.toSet()
                val entriesArr = org.json.JSONArray(jsonEntries)
                for (i in 0 until entriesArr.length()) {
                    val obj = entriesArr.getJSONObject(i)
                    if (existingDates.contains(obj.getString("date"))) continue
                    
                    val entry = com.tuktuk.manager.data.local.entity.DailyEntry(
                        date = obj.getString("date"),
                        timeIn = obj.getString("timeIn"),
                        timeOut = obj.getString("timeOut"),
                        startOdometer = obj.getDouble("startOdometer"),
                        endOdometer = obj.getDouble("endOdometer"),
                        grossIncome = obj.getDouble("grossIncome"),
                        actualFuelCost = obj.getDouble("actualFuelCost"),
                        notes = obj.optString("notes", ""),
                        isSynced = false
                    )
                    repo.saveEntry(entry)
                }

                val expensesArr = org.json.JSONArray(jsonExpenses)
                for (i in 0 until expensesArr.length()) {
                    val obj = expensesArr.getJSONObject(i)
                    val expense = com.tuktuk.manager.data.local.entity.Expense(
                        date = obj.getString("date"),
                        category = obj.getString("category"),
                        description = obj.getString("description"),
                        mechanicVendor = obj.getString("mechanicVendor"),
                        cost = obj.getDouble("cost"),
                        paidFrom = obj.getString("paidFrom"),
                        receiptNotes = obj.optString("receiptNotes", ""),
                        isSynced = false
                    )
                    repo.saveExpense(expense)
                }
                _syncState.value = SyncState.Success("Legacy Data Imported Successfully!")
            } catch (e: Exception) {
                _syncState.value = SyncState.Error("Import failed: ${e.message}")
            }
        }
    }

    fun resetSyncState() { _syncState.value = SyncState.Idle }
}

sealed class SyncState {
    object Idle : SyncState()
    object Syncing : SyncState()
    data class Success(val msg: String) : SyncState()
    data class Error(val msg: String) : SyncState()
}

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModels { ViewModelFactory(requireContext()) }
    private var currentSettings: AppSettings? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeSettings()
        observeSyncState()
    }

    private fun setupClickListeners() {
        binding.btnSaveSettings.setOnClickListener { saveSettings() }
        binding.btnSyncNow.setOnClickListener { viewModel.syncNow() }
        binding.btnImportLegacy.setOnClickListener { importData() }

        binding.rgTheme.setOnCheckedChangeListener { _, checkedId ->
            val mode = when (checkedId) {
                binding.rbThemeDark.id -> "dark"
                binding.rbThemeLight.id -> "light"
                else -> "system"
            }
            viewModel.setTheme(mode)
        }
    }

    private fun importData() {
        try {
            val entriesJson = requireContext().assets.open("daily_entries.json").bufferedReader().use { it.readText() }
            val expensesJson = requireContext().assets.open("expenses.json").bufferedReader().use { it.readText() }
            viewModel.importLegacyData(entriesJson, expensesJson)
        } catch (e: Exception) {
            Snackbar.make(binding.root, "No legacy data found in assets.", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun saveSettings() {
        val s = currentSettings ?: AppSettings()
        val updated = s.copy(
            ownerName = binding.etOwnerName.text?.toString()?.trim() ?: s.ownerName,
            riderName = binding.etRiderName.text?.toString()?.trim() ?: s.riderName,
            dailyGrossTarget = binding.etDailyTarget.text?.toString()?.toDoubleOrNull() ?: s.dailyGrossTarget,
            minimumGross = binding.etMinGross.text?.toString()?.toDoubleOrNull() ?: s.minimumGross,
            startingFloat = binding.etFloat.text?.toString()?.toDoubleOrNull() ?: s.startingFloat,
            commuteKmOneWay = binding.etCommuteKm.text?.toString()?.toDoubleOrNull() ?: s.commuteKmOneWay,
            targetWorkingDays = binding.etWorkingDays.text?.toString()?.toIntOrNull() ?: s.targetWorkingDays
        )
        viewModel.saveSettings(updated)
        Snackbar.make(binding.root, "Settings saved ✓", Snackbar.LENGTH_SHORT).show()
    }

    private fun observeSettings() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.settings.collectLatest { settings ->
                settings ?: return@collectLatest
                currentSettings = settings
                binding.etOwnerName.setText(settings.ownerName)
                binding.etRiderName.setText(settings.riderName)
                binding.etDailyTarget.setText(settings.dailyGrossTarget.toInt().toString())
                binding.etMinGross.setText(settings.minimumGross.toInt().toString())
                binding.etFloat.setText(settings.startingFloat.toInt().toString())
                binding.etCommuteKm.setText(settings.commuteKmOneWay.toInt().toString())
                binding.etWorkingDays.setText(settings.targetWorkingDays.toString())
                when (settings.themeMode) {
                    "dark" -> binding.rbThemeDark.isChecked = true
                    "light" -> binding.rbThemeLight.isChecked = true
                    else -> binding.rbThemeSystem.isChecked = true
                }
            }
        }
    }

    private fun observeSyncState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.syncState.collectLatest { state ->
                binding.btnSyncNow.isEnabled = state !is SyncState.Syncing
                binding.btnSyncNow.text = if (state is SyncState.Syncing) "Syncing…" else "Sync Now"
                when (state) {
                    is SyncState.Success -> {
                        Snackbar.make(binding.root, state.msg, Snackbar.LENGTH_SHORT).show()
                        viewModel.resetSyncState()
                    }
                    is SyncState.Error -> {
                        Snackbar.make(binding.root, state.msg, Snackbar.LENGTH_LONG).show()
                        viewModel.resetSyncState()
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
