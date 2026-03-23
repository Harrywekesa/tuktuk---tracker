package com.tuktuk.manager.ui.history

import android.os.Bundle
import androidx.navigation.NavDirections
import com.tuktuk.manager.R
import kotlin.Int
import kotlin.Long

public class HistoryFragmentDirections private constructor() {
  private data class ActionHistoryToEntryDetail(
    public val entryId: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_history_to_entryDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("entryId", this.entryId)
        return result
      }
  }

  private data class ActionHistoryToDailyEntry(
    public val entryId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_history_to_dailyEntry

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("entryId", this.entryId)
        return result
      }
  }

  public companion object {
    public fun actionHistoryToEntryDetail(entryId: Long): NavDirections =
        ActionHistoryToEntryDetail(entryId)

    public fun actionHistoryToDailyEntry(entryId: Long = -1L): NavDirections =
        ActionHistoryToDailyEntry(entryId)
  }
}
