package com.marijannovak.autismhelper.ui.screens.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.common.base.BaseFragment
import com.marijannovak.autismhelper.sync.SyncWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<Nothing, Nothing>() {

    override val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startSync()
    }

    private fun startSync() {
        val workRequest = OneTimeWorkRequestBuilder<SyncWorker>().build()
        WorkManager.getInstance(requireContext()).enqueue(workRequest)
    }
}
