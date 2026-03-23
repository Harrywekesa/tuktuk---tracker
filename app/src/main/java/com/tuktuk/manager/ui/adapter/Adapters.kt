package com.tuktuk.manager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuktuk.manager.R
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.local.entity.Expense
import com.tuktuk.manager.databinding.ItemDailyEntryBinding
import com.tuktuk.manager.databinding.ItemExpenseBinding
import com.tuktuk.manager.databinding.ItemRecentEntryBinding
import com.tuktuk.manager.util.CurrencyFormatter
import com.tuktuk.manager.util.DateUtils

// ── Daily Entry Adapter (History screen) ───────────────────────────────────
class DailyEntryAdapter(
    private val onItemClick: (DailyEntry) -> Unit,
    private val onEditClick: (DailyEntry) -> Unit
) : ListAdapter<DailyEntry, DailyEntryAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val b: ItemDailyEntryBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(entry: DailyEntry) {
            b.tvEntryDate.text = DateUtils.toDisplay(entry.date)
            b.tvEntryDow.text = DateUtils.dayOfWeek(entry.date)
            b.tvEntryGross.text = CurrencyFormatter.format(entry.grossIncome)
            b.tvEntryNet.text = CurrencyFormatter.format(entry.netProfit)
            b.tvEntryBankDep.text = CurrencyFormatter.format(entry.bankDeposit)

            // Target status indicator
            val (color, text) = when {
                entry.meetsTarget -> Pair(
                    ContextCompat.getColor(b.root.context, R.color.status_good), "✅"
                )
                entry.aboveMinimum -> Pair(
                    ContextCompat.getColor(b.root.context, R.color.status_warning), "⚠️"
                )
                else -> Pair(
                    ContextCompat.getColor(b.root.context, R.color.status_alert), "🔴"
                )
            }
            b.tvEntryTargetBadge.text = text
            b.tvEntryTargetBadge.setTextColor(color)

            // Fuel status
            val fuelText = when (entry.fuelStatus) {
                DailyEntry.FuelStatus.GOOD -> "✅"
                DailyEntry.FuelStatus.HIGH -> "⚠️ Fuel"
                DailyEntry.FuelStatus.ALERT -> "🔴 Fuel"
                DailyEntry.FuelStatus.NONE -> ""
            }
            b.tvEntryFuelBadge.text = fuelText

            b.root.setOnClickListener { onItemClick(entry) }
            b.btnEntryEdit.setOnClickListener { onEditClick(entry) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemDailyEntryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<DailyEntry>() {
        override fun areItemsTheSame(o: DailyEntry, n: DailyEntry) = o.id == n.id
        override fun areContentsTheSame(o: DailyEntry, n: DailyEntry) = o == n
    }
}

// ── Expense Adapter ────────────────────────────────────────────────────────
class ExpenseAdapter(
    private val onItemClick: (Expense) -> Unit
) : ListAdapter<Expense, ExpenseAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val b: ItemExpenseBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(expense: Expense) {
            b.tvExpenseDate.text = DateUtils.toDisplayShort(expense.date)
            b.tvExpenseCategory.text = expense.category
            b.tvExpenseDescription.text = expense.description.ifEmpty { "—" }
            b.tvExpenseCost.text = CurrencyFormatter.format(expense.cost)
            b.tvExpensePaidFrom.text = expense.paidFrom

            val icon = when (expense.category) {
                "Oil Change", "Preventive Maintenance" -> "🔧"
                "Minor Repair", "Major Repair" -> "🔩"
                "Replacing Parts" -> "⚙️"
                "Labor / Mechanic" -> "👨‍🔧"
                "County Fees" -> "🏛️"
                "Insurance" -> "🛡️"
                else -> "📋"
            }
            b.tvExpenseIcon.text = icon

            b.root.setOnClickListener { onItemClick(expense) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(o: Expense, n: Expense) = o.id == n.id
        override fun areContentsTheSame(o: Expense, n: Expense) = o == n
    }
}

// ── Recent Entry Adapter (Dashboard) ──────────────────────────────────────
class RecentEntryAdapter(
    private val onItemClick: (DailyEntry) -> Unit
) : ListAdapter<DailyEntry, RecentEntryAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val b: ItemRecentEntryBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(entry: DailyEntry) {
            b.tvRecentDate.text = DateUtils.toDisplayShort(entry.date)
            b.tvRecentDow.text = DateUtils.dayOfWeek(entry.date)
            b.tvRecentGross.text = CurrencyFormatter.format(entry.grossIncome)
            b.tvRecentNet.text = CurrencyFormatter.format(entry.netProfit)

            val dotColor = when {
                entry.meetsTarget -> ContextCompat.getColor(b.root.context, R.color.status_good)
                entry.aboveMinimum -> ContextCompat.getColor(b.root.context, R.color.status_warning)
                else -> ContextCompat.getColor(b.root.context, R.color.status_alert)
            }
            b.viewStatusDot.setBackgroundColor(dotColor)

            b.root.setOnClickListener { onItemClick(entry) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemRecentEntryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<DailyEntry>() {
        override fun areItemsTheSame(o: DailyEntry, n: DailyEntry) = o.id == n.id
        override fun areContentsTheSame(o: DailyEntry, n: DailyEntry) = o == n
    }
}
