package com.marijannovak.autismhelper.sync

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker.Result.success
import androidx.work.WorkerParameters
import com.marijannovak.autismhelper.data.service.AuthService
import com.marijannovak.autismhelper.data.service.DataService
import com.marijannovak.autismhelper.data.storage.AuthStorage
import com.marijannovak.autismhelper.data.storage.DataStorage
import dagger.hilt.android.AndroidEntryPoint

/**
 * Sync all the user content and data after logging in
 */
class SyncWorker @WorkerInject constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
    private val dataService: DataService,
    private val dataStorage: DataStorage,
    private val userService: AuthService,
    private val userStorage: AuthStorage
) : CoroutineWorker(context, parameters) {

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