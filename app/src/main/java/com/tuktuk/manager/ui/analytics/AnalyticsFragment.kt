package com.tuktuk.manager.ui.analytics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.tuktuk.manager.R
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.model.MonthlyStats
import com.tuktuk.manager.databinding.FragmentAnalyticsBinding
import com.tuktuk.manager.util.CurrencyFormatter
import com.tuktuk.manager.util.DateUtils
import com.tuktuk.manager.util.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnalyticsViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharts()
        setupMonthNavigation()
        observeData()
    }

    private fun setupMonthNavigation() {
        binding.btnPrevMonth.setOnClickListener { viewModel.previousMonth() }
        binding.btnNextMonth.setOnClickListener { viewModel.nextMonth() }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedYearMonth.collectLatest { ym ->
                binding.tvMonthLabel.text = DateUtils.monthLabel(ym)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.monthlyStats.collectLatest { stats ->
                stats?.let {
                    bindStatsCards(it)
                    updatePieChart(it)
                    updateFuelBars(it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.entries.collectLatest { entries ->
                updateIncomeLineChart(entries)
                updateFuelStatusBars(entries)
            }
        }
    }

    private fun bindStatsCards(stats: MonthlyStats) {
        with(binding) {
            tvStatGross.text = CurrencyFormatter.format(stats.totalGross)
            tvStatNet.text = CurrencyFormatter.format(stats.totalNetProfit)
            tvStatFuel.text = CurrencyFormatter.format(stats.totalFuel)
            tvStatKm.text = CurrencyFormatter.formatKm(stats.totalBusinessKm)
            tvStatDaysWorked.text = "${stats.daysWorked}"
            tvStatDaysTarget.text = "${stats.daysHitTarget}"
            tvStatDaysBelow.text = "${stats.daysBelowMinimum}"
            tvStatAvgGross.text = CurrencyFormatter.format(stats.avgDailyGross)
            tvStatBestDay.text = CurrencyFormatter.format(stats.bestDay)
            tvStatWorstDay.text = CurrencyFormatter.format(stats.worstDay)
            tvStatFuelPct.text = CurrencyFormatter.formatPercent(stats.fuelAsPercentOfGross)
            tvStatFuelGood.text = "${stats.fuelGoodDays}"
            tvStatFuelHigh.text = "${stats.fuelHighDays}"
            tvStatFuelAlert.text = "${stats.fuelAlertDays}"
            tvStatBankBalance.text = CurrencyFormatter.format(stats.currentBankBalance)
            tvStatMaintBalance.text = CurrencyFormatter.format(stats.netMaintenanceBalance)
            tvStatProfitMargin.text = CurrencyFormatter.formatPercent(stats.profitMargin)

            // Performance Card mapping
            statDaysWorked.tvStatLabel.text = "Days Worked"
            statDaysWorked.tvStatValue.text = "${stats.daysWorked}"
            
            statDaysTarget.tvStatLabel.text = "Hit Target"
            statDaysTarget.tvStatValue.text = "${stats.daysHitTarget}"
            statDaysTarget.tvStatValue.setTextColor(android.graphics.Color.parseColor("#52B788"))
            
            statDaysBelow.tvStatLabel.text = "Below Min"
            statDaysBelow.tvStatValue.text = "${stats.daysBelowMinimum}"
            statDaysBelow.tvStatValue.setTextColor(android.graphics.Color.parseColor("#E63946"))
            
            statAvgGross.tvStatLabel.text = "Avg per Day"
            statAvgGross.tvStatValue.text = CurrencyFormatter.format(stats.avgDailyGross)
            
            statBestDay.tvStatLabel.text = "Best Day"
            statBestDay.tvStatValue.text = CurrencyFormatter.format(stats.bestDay)
            statBestDay.tvStatValue.setTextColor(android.graphics.Color.parseColor("#52B788"))
            
            statWorstDay.tvStatLabel.text = "Worst Day"
            statWorstDay.tvStatValue.text = CurrencyFormatter.format(stats.worstDay)
            statWorstDay.tvStatValue.setTextColor(android.graphics.Color.parseColor("#E63946"))
        }
    }

    private fun setupCharts() {
        setupLineChart(binding.chartIncomeTrend)
        setupPieChart(binding.chartBreakdown)
        setupBarChart(binding.chartFuelDays)
    }

    private fun setupLineChart(chart: LineChart) {
        chart.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(false)
            setDrawGridBackground(false)
            legend.isEnabled = false
            axisRight.isEnabled = false
            axisLeft.apply {
                textColor = Color.parseColor("#80FFFFFF")
                gridColor = Color.parseColor("#20FFFFFF")
                axisLineColor = Color.TRANSPARENT
                textSize = 10f
            }
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textColor = Color.parseColor("#80FFFFFF")
                gridColor = Color.TRANSPARENT
                axisLineColor = Color.TRANSPARENT
                textSize = 10f
                granularity = 1f
            }
            setBackgroundColor(Color.TRANSPARENT)
            setNoDataText("No data for this month")
            setNoDataTextColor(Color.parseColor("#80FFFFFF"))
        }
    }

    private fun setupPieChart(chart: PieChart) {
        chart.apply {
            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 55f
            transparentCircleRadius = 60f
            setHoleColor(Color.TRANSPARENT)
            setTransparentCircleAlpha(0)
            setUsePercentValues(true)
            legend.isEnabled = false
            setDrawEntryLabels(false)
            setNoDataText("No data")
            setNoDataTextColor(Color.parseColor("#80FFFFFF"))
            centerText = "Split"
            setCenterTextColor(Color.WHITE)
            setCenterTextSize(14f)
        }
    }

    private fun setupBarChart(chart: BarChart) {
        chart.apply {
            description.isEnabled = false
            setTouchEnabled(false)
            legend.isEnabled = false
            setDrawValueAboveBar(true)
            axisRight.isEnabled = false
            axisLeft.apply {
                textColor = Color.parseColor("#80FFFFFF")
                gridColor = Color.parseColor("#20FFFFFF")
                axisLineColor = Color.TRANSPARENT
                textSize = 10f
            }
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textColor = Color.parseColor("#80FFFFFF")
                gridColor = Color.TRANSPARENT
                axisLineColor = Color.TRANSPARENT
                textSize = 11f
                valueFormatter = IndexAxisValueFormatter(arrayOf("✅ Good", "⚠️ High", "🔴 Alert"))
                granularity = 1f
            }
            setBackgroundColor(Color.TRANSPARENT)
            setNoDataText("No data")
            setNoDataTextColor(Color.parseColor("#80FFFFFF"))
        }
    }

    private fun updateIncomeLineChart(entries: List<DailyEntry>) {
        if (entries.isEmpty()) { binding.chartIncomeTrend.clear(); return }

        val sorted = entries.sortedBy { it.date }
        val labels = sorted.map { DateUtils.toDisplayShort(it.date) }

        val grossEntries = sorted.mapIndexed { i, e -> Entry(i.toFloat(), e.grossIncome.toFloat()) }
        val netEntries = sorted.mapIndexed { i, e -> Entry(i.toFloat(), e.netProfit.toFloat()) }
        val targetLine = sorted.mapIndexed { i, _ -> Entry(i.toFloat(), 3000f) }

        val grossSet = LineDataSet(grossEntries, "Gross").apply {
            color = Color.parseColor("#F4A261")
            setCircleColor(Color.parseColor("#F4A261"))
            lineWidth = 2.5f; circleRadius = 4f; setDrawCircleHole(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawValues(false)
            fillAlpha = 30; fillColor = Color.parseColor("#F4A261")
            setDrawFilled(true)
        }
        val netSet = LineDataSet(netEntries, "Net").apply {
            color = Color.parseColor("#2A9D8F")
            setCircleColor(Color.parseColor("#2A9D8F"))
            lineWidth = 2f; circleRadius = 3f; setDrawCircleHole(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawValues(false)
        }
        val targetSet = LineDataSet(targetLine, "Target").apply {
            color = Color.parseColor("#40E9C46A")
            lineWidth = 1.5f; setDrawCircles(false)
            enableDashedLine(10f, 5f, 0f)
            setDrawValues(false)
        }

        binding.chartIncomeTrend.apply {
            data = LineData(listOf(grossSet, netSet, targetSet))
            xAxis.valueFormatter = IndexAxisValueFormatter(labels.toTypedArray())
            invalidate()
        }
    }

    private fun updatePieChart(stats: MonthlyStats) {
        val entries = listOf(
            PieEntry(stats.totalOwnerSalary.toFloat(), "Owner"),
            PieEntry(stats.totalRiderPay.toFloat(), "Rider"),
            PieEntry(stats.totalMaintenanceSave.toFloat(), "Maint"),
            PieEntry(stats.totalBusinessProfit.toFloat(), "Profit"),
            PieEntry(stats.totalFuel.toFloat(), "Fuel")
        ).filter { it.value > 0 }

        if (entries.isEmpty()) { binding.chartBreakdown.clear(); return }

        val colors = listOf(
            Color.parseColor("#F4A261"),
            Color.parseColor("#2A9D8F"),
            Color.parseColor("#E9C46A"),
            Color.parseColor("#52B788"),
            Color.parseColor("#E63946")
        )

        val dataSet = PieDataSet(entries, "").apply {
            this.colors = colors
            sliceSpace = 3f
            selectionShift = 5f
        }
        binding.chartBreakdown.apply {
            data = PieData(dataSet).apply {
                setValueFormatter(PercentFormatter(binding.chartBreakdown))
                setValueTextSize(11f)
                setValueTextColor(Color.WHITE)
            }
            invalidate()
        }
    }

    private fun updateFuelBars(stats: MonthlyStats) {
        val entries = listOf(
            BarEntry(0f, stats.fuelGoodDays.toFloat()),
            BarEntry(1f, stats.fuelHighDays.toFloat()),
            BarEntry(2f, stats.fuelAlertDays.toFloat())
        )
        val colors = listOf(
            Color.parseColor("#52B788"),
            Color.parseColor("#E9C46A"),
            Color.parseColor("#E63946")
        )
        val dataSet = BarDataSet(entries, "Fuel Days").apply {
            this.colors = colors
            valueTextColor = Color.WHITE
            valueTextSize = 12f
        }
        binding.chartFuelDays.apply {
            data = BarData(dataSet).apply { barWidth = 0.5f }
            invalidate()
        }
    }

    private fun updateFuelStatusBars(entries: List<DailyEntry>) {
        // Already handled in updateFuelBars via stats
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
