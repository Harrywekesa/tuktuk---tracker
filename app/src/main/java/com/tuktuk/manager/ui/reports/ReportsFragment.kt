package com.tuktuk.manager.ui.reports

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.local.entity.Expense
import com.tuktuk.manager.data.model.MonthlyStats
import com.tuktuk.manager.data.repository.TukTukRepository
import com.tuktuk.manager.databinding.FragmentReportsBinding
import com.tuktuk.manager.util.CurrencyFormatter
import com.tuktuk.manager.util.DateUtils
import com.tuktuk.manager.util.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

// ── Report State ──────────────────────────────────────────────────────────
sealed class ReportState {
    object Idle : ReportState()
    object Generating : ReportState()
    data class Ready(val file: File, val type: String) : ReportState()
    data class Error(val message: String) : ReportState()
}

// ── ViewModel ─────────────────────────────────────────────────────────────
class ReportsViewModel(private val repo: TukTukRepository) : ViewModel() {

    private val _selectedYearMonth = MutableStateFlow(DateUtils.currentYearMonth())
    val selectedYearMonth: StateFlow<String> = _selectedYearMonth.asStateFlow()

    private val _reportState = MutableStateFlow<ReportState>(ReportState.Idle)
    val reportState: StateFlow<ReportState> = _reportState.asStateFlow()

    fun selectMonth(yearMonth: String) { _selectedYearMonth.value = yearMonth }
    fun previousMonth() {
        val parts = _selectedYearMonth.value.split("-")
        if (parts.size == 2) {
            val y = parts[0].toInt()
            val m = parts[1].toInt()
            _selectedYearMonth.value = "%04d-%02d".format(
                if (m == 1) y - 1 else y, if (m == 1) 12 else m - 1
            )
        }
    }
    fun nextMonth() {
        val parts = _selectedYearMonth.value.split("-")
        if (parts.size == 2) {
            val y = parts[0].toInt()
            val m = parts[1].toInt()
            _selectedYearMonth.value = "%04d-%02d".format(
                if (m == 12) y + 1 else y, if (m == 12) 1 else m + 1
            )
        }
    }

    fun generatePdf(context: Context) {
        viewModelScope.launch {
            _reportState.value = ReportState.Generating
            try {
                val ym = _selectedYearMonth.value
                val stats = repo.getMonthlyStats(ym)
                val entries = repo.getEntriesForMonth(ym).first()
                val expenses = repo.getExpensesForMonth(ym).first()
                val file = withContext(Dispatchers.IO) {
                    buildPdf(context, ym, stats, entries, expenses)
                }
                _reportState.value = ReportState.Ready(file, "pdf")
            } catch (e: Exception) {
                _reportState.value = ReportState.Error(e.message ?: "Failed to generate PDF")
            }
        }
    }

    fun generateExcel(context: Context) {
        viewModelScope.launch {
            _reportState.value = ReportState.Generating
            try {
                val ym = _selectedYearMonth.value
                val stats = repo.getMonthlyStats(ym)
                val entries = repo.getEntriesForMonth(ym).first()
                val expenses = repo.getExpensesForMonth(ym).first()
                val file = withContext(Dispatchers.IO) {
                    buildExcel(context, ym, stats, entries, expenses)
                }
                _reportState.value = ReportState.Ready(file, "xlsx")
            } catch (e: Exception) {
                _reportState.value = ReportState.Error(e.message ?: "Failed to generate Excel")
            }
        }
    }

    fun resetState() { _reportState.value = ReportState.Idle }

    private fun buildPdf(
        context: Context, ym: String, stats: MonthlyStats,
        entries: List<DailyEntry>, expenses: List<Expense>
    ): File {
        val navyColor = DeviceRgb(13, 27, 42)
        val orangeColor = DeviceRgb(244, 162, 97)
        val lightGray = DeviceRgb(240, 244, 248)

        val fileName = "TukTuk_Report_${ym}.pdf"
        val file = File(context.getExternalFilesDir(null), fileName)
        val writer = PdfWriter(FileOutputStream(file))
        val pdf = PdfDocument(writer)
        val doc = Document(pdf)

        // Title
        doc.add(Paragraph("TUK-TUK MANAGER")
            .setFontSize(24f).setBold().setFontColor(navyColor)
            .setTextAlignment(TextAlignment.CENTER))
        doc.add(Paragraph("Monthly Report — ${DateUtils.monthLabel(ym)}")
            .setFontSize(14f).setFontColor(orangeColor)
            .setTextAlignment(TextAlignment.CENTER))
        doc.add(Paragraph(" "))

        // Summary Section
        doc.add(Paragraph("MONTHLY SUMMARY").setFontSize(12f).setBold().setFontColor(navyColor))
        val summaryTable = Table(UnitValue.createPercentArray(floatArrayOf(60f, 40f)))
            .setWidth(UnitValue.createPercentValue(100f))

        val summaryRows = listOf(
            "Total Gross Income" to CurrencyFormatter.format(stats.totalGross),
            "Total Fuel Cost" to CurrencyFormatter.format(stats.totalFuel),
            "Total Net Profit" to CurrencyFormatter.format(stats.totalNetProfit),
            "Owner Salary (25%)" to CurrencyFormatter.format(stats.totalOwnerSalary),
            "Rider Pay (25%)" to CurrencyFormatter.format(stats.totalRiderPay),
            "Maintenance Fund (25%)" to CurrencyFormatter.format(stats.totalMaintenanceSave),
            "Business Profit (25%)" to CurrencyFormatter.format(stats.totalBusinessProfit),
            "Total Expenses" to CurrencyFormatter.format(stats.totalExpenses),
            "Net Maintenance Balance" to CurrencyFormatter.format(stats.netMaintenanceBalance),
            "Bank Balance" to CurrencyFormatter.format(stats.currentBankBalance),
            "Days Worked" to "${stats.daysWorked}",
            "Days Hit Target" to "${stats.daysHitTarget}",
            "Days Below Minimum" to "${stats.daysBelowMinimum}",
            "Average Daily Gross" to CurrencyFormatter.format(stats.avgDailyGross),
            "Best Day" to CurrencyFormatter.format(stats.bestDay),
            "Worst Day" to CurrencyFormatter.format(stats.worstDay),
            "Avg Fuel %" to CurrencyFormatter.formatPercent(stats.fuelAsPercentOfGross),
            "Total Business KMs" to CurrencyFormatter.formatKm(stats.totalBusinessKm)
        )

        summaryRows.forEachIndexed { i, (label, value) ->
            val bg = if (i % 2 == 0) lightGray else ColorConstants.WHITE
            summaryTable.addCell(Cell().add(Paragraph(label).setFontSize(10f))
                .setBackgroundColor(bg).setPadding(6f))
            summaryTable.addCell(Cell().add(Paragraph(value).setFontSize(10f).setBold())
                .setBackgroundColor(bg).setPadding(6f)
                .setTextAlignment(TextAlignment.RIGHT))
        }
        doc.add(summaryTable)
        doc.add(Paragraph(" "))

        // Daily Entries Table
        if (entries.isNotEmpty()) {
            doc.add(Paragraph("DAILY ENTRIES").setFontSize(12f).setBold().setFontColor(navyColor))
            val entryTable = Table(UnitValue.createPercentArray(
                floatArrayOf(15f, 12f, 12f, 12f, 12f, 12f, 12f, 13f)))
                .setWidth(UnitValue.createPercentValue(100f))
                .setFontSize(8f)

            listOf("Date", "Gross", "Fuel", "Net", "Salary", "Rider", "Maint", "Bank Dep")
                .forEach { header ->
                    entryTable.addHeaderCell(Cell().add(Paragraph(header).setBold())
                        .setBackgroundColor(navyColor)
                        .setFontColor(ColorConstants.WHITE).setPadding(5f))
                }
            entries.sortedBy { it.date }.forEachIndexed { index, e ->
                val bg = if (index % 2 == 0) lightGray else ColorConstants.WHITE
                listOf(
                    DateUtils.toDisplayShort(e.date),
                    CurrencyFormatter.formatCompact(e.grossIncome),
                    CurrencyFormatter.formatCompact(e.actualFuelCost),
                    CurrencyFormatter.formatCompact(e.netProfit),
                    CurrencyFormatter.formatCompact(e.ownerSalary),
                    CurrencyFormatter.formatCompact(e.riderPay),
                    CurrencyFormatter.formatCompact(e.maintenanceSave),
                    CurrencyFormatter.formatCompact(e.bankDeposit)
                ).forEach { textValue ->
                    entryTable.addCell(Cell().add(Paragraph(textValue)).setBackgroundColor(bg).setPadding(4f))
                }
            }
            doc.add(entryTable)
            doc.add(Paragraph(" "))
        }

        // Expenses Table
        if (expenses.isNotEmpty()) {
            doc.add(Paragraph("EXPENSES").setFontSize(12f).setBold().setFontColor(navyColor))
            val expTable = Table(UnitValue.createPercentArray(floatArrayOf(15f, 25f, 35f, 25f)))
                .setWidth(UnitValue.createPercentValue(100f)).setFontSize(9f)
            listOf("Date", "Category", "Description", "Cost").forEach { h ->
                expTable.addHeaderCell(Cell().add(Paragraph(h).setBold())
                    .setBackgroundColor(navyColor).setFontColor(ColorConstants.WHITE).setPadding(5f))
            }
            expenses.sortedBy { it.date }.forEachIndexed { index, exp ->
                val bg = if (index % 2 == 0) lightGray else ColorConstants.WHITE
                listOf(DateUtils.toDisplayShort(exp.date), exp.category, exp.description,
                    CurrencyFormatter.format(exp.cost)).forEach { v ->
                    expTable.addCell(Cell().add(Paragraph(v)).setBackgroundColor(bg).setPadding(4f))
                }
            }
            doc.add(expTable)
        }

        // Footer
        doc.add(Paragraph(" "))
        doc.add(Paragraph("Generated by Tuk-Tuk Manager  •  ${DateUtils.toDisplay(DateUtils.today())}")
            .setFontSize(8f).setFontColor(DeviceRgb(150, 150, 150))
            .setTextAlignment(TextAlignment.CENTER))

        doc.close()
        return file
    }

    private fun buildExcel(
        context: Context, ym: String, stats: MonthlyStats,
        entries: List<DailyEntry>, expenses: List<Expense>
    ): File {
        val wb = XSSFWorkbook()

        // ── Summary sheet ──────────────────────────────────────────────────
        val ss = wb.createSheet("Monthly Summary")

        ss.createRow(0).createCell(0).setCellValue("TUK-TUK MONTHLY REPORT — ${DateUtils.monthLabel(ym)}")
        var r = 2
        val summaryData = listOf(
            "Total Gross Income" to stats.totalGross,
            "Total Fuel Cost" to stats.totalFuel,
            "Total Net Profit" to stats.totalNetProfit,
            "Owner Salary (25%)" to stats.totalOwnerSalary,
            "Rider Pay (25%)" to stats.totalRiderPay,
            "Maintenance Fund (25%)" to stats.totalMaintenanceSave,
            "Business Profit (25%)" to stats.totalBusinessProfit,
            "Total Expenses" to stats.totalExpenses,
            "Net Maintenance Balance" to stats.netMaintenanceBalance,
            "Bank Balance" to stats.currentBankBalance,
            "Days Worked" to stats.daysWorked.toDouble(),
            "Days Hit Target" to stats.daysHitTarget.toDouble(),
            "Days Below Minimum" to stats.daysBelowMinimum.toDouble(),
            "Average Daily Gross" to stats.avgDailyGross,
            "Best Day" to stats.bestDay,
            "Worst Day" to stats.worstDay,
            "Total Business KMs" to stats.totalBusinessKm
        )
        summaryData.forEach { (label, value) ->
            val row = ss.createRow(r++)
            row.createCell(0).setCellValue(label)
            row.createCell(1).setCellValue(value)
        }

        // ── Daily Entries sheet ────────────────────────────────────────────
        val ds = wb.createSheet("Daily Entries")
        val dHeaders = listOf("Date", "Time In", "Time Out", "Gross", "Fuel", "Net Profit",
            "Owner Salary", "Rider Pay", "Maintenance", "Biz Profit", "Float", "Bank Deposit",
            "Business KM", "Income/KM", "Fuel/KM", "Fuel %", "Fuel Status", "Notes")
        val dhRow = ds.createRow(0)
        dHeaders.forEachIndexed { i, h -> dhRow.createCell(i).setCellValue(h) }
        entries.sortedBy { it.date }.forEachIndexed { i, e ->
            val row = ds.createRow(i + 1)
            val rowValues = listOf(
                e.date, e.timeIn, e.timeOut, e.grossIncome, e.actualFuelCost, e.netProfit,
                e.ownerSalary, e.riderPay, e.maintenanceSave, e.businessProfit, 200.0,
                e.bankDeposit, e.businessKm, e.incomePerKm, e.fuelPerKm, e.fuelPercentage,
                when (e.fuelStatus) {
                    DailyEntry.FuelStatus.GOOD -> "GOOD"
                    DailyEntry.FuelStatus.HIGH -> "HIGH"
                    DailyEntry.FuelStatus.ALERT -> "ALERT"
                    else -> ""
                }, e.notes
            )
            rowValues.forEachIndexed { ci, v ->
                val cell = row.createCell(ci)
                when (v) {
                    is Double -> cell.setCellValue(v)
                    else -> cell.setCellValue(v.toString())
                }
            }
        }

        // ── Expenses sheet ─────────────────────────────────────────────────
        val es = wb.createSheet("Expenses")
        val eHeaders = listOf("Date", "Category", "Description", "Mechanic/Vendor", "Cost", "Paid From", "Notes")
        val ehRow = es.createRow(0)
        eHeaders.forEachIndexed { i, h -> ehRow.createCell(i).setCellValue(h) }
        expenses.sortedBy { it.date }.forEachIndexed { i, exp ->
            val row = es.createRow(i + 1)
            val rowValues = listOf(exp.date, exp.category, exp.description, exp.mechanicVendor, exp.cost,
                exp.paidFrom, exp.receiptNotes)
            rowValues.forEachIndexed { ci, v ->
                val cell = row.createCell(ci)
                when (v) {
                    is Double -> cell.setCellValue(v)
                    else -> cell.setCellValue(v.toString())
                }
            }
        }

        val fileName = "TukTuk_Report_${ym}.xlsx"
        val file = File(context.getExternalFilesDir(null), fileName)
        FileOutputStream(file).use { wb.write(it) }
        wb.close()
        return file
    }
}

// ── Fragment ───────────────────────────────────────────────────────────────
class ReportsFragment : Fragment() {

    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReportsViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMonthNav()
        setupButtons()
        observeState()
    }

    private fun setupMonthNav() {
        binding.btnPrevMonth.setOnClickListener { viewModel.previousMonth() }
        binding.btnNextMonth.setOnClickListener { viewModel.nextMonth() }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedYearMonth.collectLatest { ym ->
                binding.tvReportMonth.text = DateUtils.monthLabel(ym)
            }
        }
    }

    private fun setupButtons() {
        binding.btnExportPdf.setOnClickListener { viewModel.generatePdf(requireContext()) }
        binding.btnExportExcel.setOnClickListener { viewModel.generateExcel(requireContext()) }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.reportState.collectLatest { state ->
                binding.progressGenerating.isVisible = state is ReportState.Generating
                binding.btnExportPdf.isEnabled = state !is ReportState.Generating
                binding.btnExportExcel.isEnabled = state !is ReportState.Generating
                binding.cardReportReady.isVisible = state is ReportState.Ready

                when (state) {
                    is ReportState.Ready -> {
                        val label = if (state.type == "pdf") "PDF" else "Excel"
                        binding.tvReportReadyLabel.text = "$label report ready"
                        binding.btnViewReport.setOnClickListener { openFile(state.file, state.type) }
                        binding.btnShareReport.setOnClickListener { shareFile(state.file, state.type) }
                    }
                    is ReportState.Error -> {
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                        viewModel.resetState()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun openFile(file: File, type: String) {
        val uri = FileProvider.getUriForFile(requireContext(),
            "${requireContext().packageName}.fileprovider", file)
        val mime = if (type == "pdf") "application/pdf" else
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, mime)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        })
    }

    private fun shareFile(file: File, type: String) {
        val uri = FileProvider.getUriForFile(requireContext(),
            "${requireContext().packageName}.fileprovider", file)
        val mime = if (type == "pdf") "application/pdf" else
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        startActivity(Intent.createChooser(
            Intent(Intent.ACTION_SEND).apply {
                this.type = mime
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }, "Share Report"
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
