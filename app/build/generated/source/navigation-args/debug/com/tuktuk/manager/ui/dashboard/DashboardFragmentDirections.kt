package com.tuktuk.manager.ui.dashboard

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.tuktuk.manager.R
import kotlin.Int
import kotlin.Long

public class DashboardFragmentDirections private constructor() {
  private data class ActionDashboardToDailyEntry(
    public val entryId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_dashboard_to_dailyEntry

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("entryId", this.entryId)
        return result
      }
  }

  private data class ActionDashboardToEntryDetail(
    public val entryId: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_dashboard_to_entryDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("entryId", this.entryId)
        return result
      }
  }

  public companion object {
    public fun actionDashboardToDailyEntry(entryId: Long = -1L): NavDirections =
        ActionDashboardToDailyEntry(entryId)

    public fun actionDashboardToHistory(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboard_to_history)

    public fun actionDashboardToAnalytics(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboard_to_analytics)

    public fun actionDashboardToEntryDetail(entryId: Long): NavDirections =
        ActionDashboardToEntryDetail(entryId)

    public fun actionDashboardToSettings(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboard_to_settings)
  }
}
