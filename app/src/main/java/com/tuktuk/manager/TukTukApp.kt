package com.tuktuk.manager

import android.app.Application
import com.tuktuk.manager.worker.SyncWorker

class TukTukApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Schedule background sync every 15 minutes when connected
        SyncWorker.schedule(this)
    }
}
