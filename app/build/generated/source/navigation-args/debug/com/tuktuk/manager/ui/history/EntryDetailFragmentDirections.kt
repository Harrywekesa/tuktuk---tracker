package com.tuktuk.manager.ui.history

import android.os.Bundle
import androidx.navigation.NavDirections
import com.tuktuk.manager.R
import kotlin.Int
import kotlin.Long

public class EntryDetailFragmentDirections private constructor() {
  private data class ActionEntryDetailToDailyEntry(
    public val entryId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_entryDetail_to_dailyEntry

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("entryId", this.entryId)
        return result
      }
  }

  public companion object {
    public fun actionEntryDetailToDailyEntry(entryId: Long = -1L): NavDirections =
        ActionEntryDetailToDailyEntry(entryId)
  }
}
