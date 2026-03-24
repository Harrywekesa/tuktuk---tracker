package com.tuktuk.manager.ui.dailyentry

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.tuktuk.manager.R
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.databinding.FragmentDailyEntryBinding
import com.tuktuk.manager.util.CurrencyFormatter
import com.tuktuk.manager.util.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar

class DailyEntryFragment : Fragment() {

    private var _binding: FragmentDailyEntryBinding? = null
    private val binding get() = _binding!!
    private val args: DailyEntryFragmentArgs by navArgs()
    private val viewModel: DailyEntryViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadEntry(args.entryId)
        setupToolbar()
        setupInputListeners()
        setupClickListeners()
        observeState()
        observeLivePreview()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupClickListeners() {
        // Date picker
        binding.etDate.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, y, m, d -> viewModel.setDate("%04d-%02d-%02d".format(y, m + 1, d)) },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Time In picker
        binding.etTimeIn.setOnClickListener {
            val cal = Calendar.getInstance()
            TimePickerDialog(
                requireContext(),
                { _, h, m -> viewModel.setTimeIn("%02d:%02d".format(h, m)) },
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true
            ).show()
        }

        // Time Out picker
        binding.etTimeOut.setOnClickListener {
            val cal = Calendar.getInstance()
            TimePickerDialog(
                requireContext(),
                { _, h, m -> viewModel.setTimeOut("%02d:%02d".format(h, m)) },
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true
            ).show()
        }

        binding.btnSave.setOnClickListener { viewModel.saveEntry() }

        binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete Entry")
                .setMessage("Are you sure you want to delete this entry? This cannot be undone.")
                .setPositiveButton("Delete") { _, _ -> viewModel.deleteEntry() }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun setupInputListeners() {
        binding.etStartOdo.addTextChangedListener(simpleWatcher {
            viewModel.setStartOdo(it.toDoubleOrNull() ?: 0.0)
        })
        binding.etEndOdo.addTextChangedListener(simpleWatcher {
            viewModel.setEndOdo(it.toDoubleOrNull() ?: 0.0)
        })
        binding.etGross.addTextChangedListener(simpleWatcher {
            viewModel.setGross(it.toDoubleOrNull() ?: 0.0)
        })
        binding.etFuel.addTextChangedListener(simpleWatcher {
            viewModel.setFuel(it.toDoubleOrNull() ?: 0.0)
        })
        binding.etNotes.addTextChangedListener(simpleWatcher {
            viewModel.setNotes(it)
        })
        binding.switchDeductFloat.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDeductFloat(isChecked)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                binding.etDate.setText(state.date)
                if (state.timeIn.isNotEmpty()) binding.etTimeIn.setText(state.timeIn)
                if (state.timeOut.isNotEmpty()) binding.etTimeOut.setText(state.timeOut)
                
                if (binding.switchDeductFloat.isChecked != state.deductFloat) {
                    binding.switchDeductFloat.isChecked = state.deductFloat
                }

                // Edit mode
                binding.toolbar.title = if (state.isEditMode) "Edit Entry" else "New Entry"
                binding.btnDelete.isVisible = state.isEditMode

                // KM hint
                if (state.businessKm > 0) {
                    binding.cardKmHint.isVisible = true
                    binding.tvKmHint.text = "Business KM: ${state.businessKm.toInt()} KM (10 KM commute deducted)"
                } else {
                    binding.cardKmHint.isVisible = false
                }

                // Loading
                binding.btnSave.isEnabled = !state.isSaving
                binding.btnSave.text = if (state.isSaving) "Saving…" else
                    if (state.isEditMode) "Update Entry" else "Save Entry"

                // Success
                if (state.savedSuccessfully) {
                    Snackbar.make(binding.root, "Entry saved ✓", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
                if (state.deletedSuccessfully) {
                    Snackbar.make(binding.root, "Entry deleted", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }

                // Error
                state.error?.let {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    viewModel.clearError()
                }
            }
        }
    }

    private fun observeLivePreview() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.livePreview.collectLatest { preview ->
                binding.tvPreviewNet.text = CurrencyFormatter.format(preview.netProfit)
                binding.tvPreviewOwner.text = CurrencyFormatter.format(preview.ownerSalary)
                binding.tvPreviewRider.text = CurrencyFormatter.format(preview.riderPay)
                binding.tvPreviewMaint.text = CurrencyFormatter.format(preview.maintenance)
                binding.tvPreviewBiz.text = CurrencyFormatter.format(preview.bizProfit)
                binding.tvPreviewBank.text = CurrencyFormatter.format(preview.bankDeposit)

                val (color, text) = when (preview.fuelStatus) {
                    DailyEntry.FuelStatus.GOOD -> Pair(
                        requireContext().getColor(R.color.status_good), "✅ GOOD"
                    )
                    DailyEntry.FuelStatus.HIGH -> Pair(
                        requireContext().getColor(R.color.status_warning), "⚠️ HIGH"
                    )
                    DailyEntry.FuelStatus.ALERT -> Pair(
                        requireContext().getColor(R.color.status_alert), "🔴 ALERT"
                    )
                    DailyEntry.FuelStatus.NONE -> Pair(
                        requireContext().getColor(R.color.dark_hint), "—"
                    )
                }
                binding.tvPreviewFuelStatus.text = text
                binding.tvPreviewFuelStatus.setTextColor(color)
            }
        }
    }

    private fun simpleWatcher(block: (String) -> Unit) = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { block(s?.toString()?.trim() ?: "") }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
