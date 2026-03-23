package com.tuktuk.manager.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tuktuk.manager.R
import com.tuktuk.manager.data.local.TukTukDatabase
import com.tuktuk.manager.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyThemeFromSettings()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        observeThemeChanges()
    }

    private fun applyThemeFromSettings() {
        lifecycleScope.launch {
            val settings = TukTukDatabase.getDatabase(this@MainActivity)
                .settingsDao().getSettingsOnce()
            val mode = when (settings?.themeMode) {
                "light" -> AppCompatDelegate.MODE_NIGHT_NO
                "dark" -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }

    private fun observeThemeChanges() {
        lifecycleScope.launch {
            TukTukDatabase.getDatabase(this@MainActivity)
                .settingsDao().getSettings().collectLatest { settings ->
                    settings?.let {
                        val mode = when (it.themeMode) {
                            "light" -> AppCompatDelegate.MODE_NIGHT_NO
                            "dark" -> AppCompatDelegate.MODE_NIGHT_YES
                            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        }
                        if (AppCompatDelegate.getDefaultNightMode() != mode) {
                            AppCompatDelegate.setDefaultNightMode(mode)
                        }
                    }
                }
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        // Hide bottom nav on entry/detail screens
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val hideNav = destination.id in listOf(
                R.id.dailyEntryFragment,
                R.id.addExpenseFragment,
                R.id.entryDetailFragment
            )
            binding.bottomNav.visibility = if (hideNav)
                android.view.View.GONE else android.view.View.VISIBLE
                
            val params = binding.navHostFragment.layoutParams as android.view.ViewGroup.MarginLayoutParams
            params.bottomMargin = if (hideNav) 0 else (80 * resources.displayMetrics.density).toInt()
            binding.navHostFragment.layoutParams = params
        }
    }
}
