package com.marijannovak.autismhelper.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker.Result.success
import androidx.work.WorkerParameters
import com.marijannovak.autismhelper.data.service.AuthService
import com.marijannovak.autismhelper.data.service.DataService
import com.marijannovak.autismhelper.data.storage.AuthStorage
import com.marijannovak.autismhelper.data.storage.DataStorage
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Sync all the user content and data after logging in
 */
class SyncWorker(context: Context, parameters: WorkerParameters): CoroutineWorker(context, parameters), KoinComponent {

    private val dataService: DataService by inject()
    private val dataStorage: DataStorage by inject()
    private val userService: AuthService by inject()
    private val userStorage: AuthStorage by inject()

    override suspend fun doWork(): Result {
        if (dataStorage.shouldSync) {
            dataStorage.saveContent(dataService.getContent())
            val uid = userStorage.getUserUid()
            uid?.let {
                val userData = userService.getUserData(uid)
                userData?.let { userStorage.saveUser(userData) }
            }
        }
        return success()
    }

}