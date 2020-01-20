package com.marijannovak.autismhelper.sync

import android.app.IntentService
import android.content.Intent
import com.marijannovak.autismhelper.data.service.DataService
import com.marijannovak.autismhelper.data.storage.DataStorage
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class SyncService: IntentService("SyncService"), KoinComponent, CoroutineScope {

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable -> Timber.e(throwable) }

    private val dataService: DataService by inject()
    private val dataStorage: DataStorage by inject()

    override fun onHandleIntent(intent: Intent?) {
        launch (exceptionHandler) {
            dataStorage.saveContent(dataService.getContent())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}