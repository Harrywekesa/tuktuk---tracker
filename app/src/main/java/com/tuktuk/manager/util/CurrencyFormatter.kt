package com.tuktuk.manager.util

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object CurrencyFormatter {
    private val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 0
        minimumFractionDigits = 0
    }
    private val decimalFormatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 1
        minimumFractionDigits = 1
    }

    fun format(amount: Double): String = "KSh ${formatter.format(amount)}"
    fun formatCompact(amount: Double): String = formatter.format(amount)
    fun formatPerKm(amount: Double): String = "KSh ${decimalFormatter.format(amount)}/KM"
    fun formatPercent(ratio: Double): String = "%.1f%%".format(ratio * 100)
    fun formatKm(km: Double): String = "${formatter.format(km)} KM"
}

object DateUtils {
    val ISO = DateTimeFormatter.ISO_DATE
    val DISPLAY = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val DISPLAY_SHORT = DateTimeFormatter.ofPattern("dd MMM")
    val YEAR_MONTH = DateTimeFormatter.ofPattern("yyyy-MM")
    val MONTH_LABEL = DateTimeFormatter.ofPattern("MMMM yyyy")
    val TIME = DateTimeFormatter.ofPattern("HH:mm")

    fun today(): String = LocalDate.now().format(ISO)
    fun currentYearMonth(): String = LocalDate.now().format(YEAR_MONTH)
    fun toDisplay(isoDate: String): String = runCatching {
        LocalDate.parse(isoDate, ISO).format(DISPLAY)
    }.getOrDefault(isoDate)
    fun toDisplayShort(isoDate: String): String = runCatching {
        LocalDate.parse(isoDate, ISO).format(DISPLAY_SHORT)
    }.getOrDefault(isoDate)
    fun monthLabel(yearMonth: String): String = runCatching {
        LocalDate.parse("$yearMonth-01", ISO).format(MONTH_LABEL)
    }.getOrDefault(yearMonth)
    fun dayOfWeek(isoDate: String): String = runCatching {
        LocalDate.parse(isoDate, ISO).dayOfWeek.name
            .lowercase().replaceFirstChar { it.uppercase() }.take(3)
    }.getOrDefault("")
}
