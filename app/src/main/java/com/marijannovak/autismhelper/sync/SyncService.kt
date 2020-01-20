package com.marijannovak.autismhelper.sync

import android.app.IntentService
import android.content.Intent
import com.marijannovak.autismhelper.data.network.API
import org.koin.core.KoinComponent
import org.koin.core.inject

class SyncService: IntentService("SyncService"), KoinComponent {

    private val api: API by inject()
    private val 

    override fun onHandleIntent(intent: Intent?) {

    }

}