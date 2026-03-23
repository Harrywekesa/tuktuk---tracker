package com.tuktuk.manager.ui.dailyentry

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class DailyEntryFragmentArgs(
  public val entryId: Long = -1L,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("entryId", this.entryId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("entryId", this.entryId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DailyEntryFragmentArgs {
      bundle.setClassLoader(DailyEntryFragmentArgs::class.java.classLoader)
      val __entryId : Long
      if (bundle.containsKey("entryId")) {
        __entryId = bundle.getLong("entryId")
      } else {
        __entryId = -1L
      }
      return DailyEntryFragmentArgs(__entryId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): DailyEntryFragmentArgs {
      val __entryId : Long?
      if (savedStateHandle.contains("entryId")) {
        __entryId = savedStateHandle["entryId"]
        if (__entryId == null) {
          throw IllegalArgumentException("Argument \"entryId\" of type long does not support null values")
        }
      } else {
        __entryId = -1L
      }
      return DailyEntryFragmentArgs(__entryId)
    }
  }
}
