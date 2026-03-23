package com.tuktuk.manager.ui.history

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class EntryDetailFragmentArgs(
  public val entryId: Long,
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
    public fun fromBundle(bundle: Bundle): EntryDetailFragmentArgs {
      bundle.setClassLoader(EntryDetailFragmentArgs::class.java.classLoader)
      val __entryId : Long
      if (bundle.containsKey("entryId")) {
        __entryId = bundle.getLong("entryId")
      } else {
        throw IllegalArgumentException("Required argument \"entryId\" is missing and does not have an android:defaultValue")
      }
      return EntryDetailFragmentArgs(__entryId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): EntryDetailFragmentArgs {
      val __entryId : Long?
      if (savedStateHandle.contains("entryId")) {
        __entryId = savedStateHandle["entryId"]
        if (__entryId == null) {
          throw IllegalArgumentException("Argument \"entryId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"entryId\" is missing and does not have an android:defaultValue")
      }
      return EntryDetailFragmentArgs(__entryId)
    }
  }
}
