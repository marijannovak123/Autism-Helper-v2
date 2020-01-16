package com.marijannovak.autismhelper.common.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.marijannovak.autismhelper.common.events.EventObserver
import com.marijannovak.autismhelper.common.events.UIEvent
import com.marijannovak.autismhelper.common.extensions.navController
import com.marijannovak.autismhelper.common.extensions.snackbar
import com.marijannovak.autismhelper.ui.dialogs.LoadingDialog

abstract class BaseFragment<V: BaseViewModel>: Fragment() {

    abstract val viewModel: V

    private var pbLoading: LoadingDialog? = null

    @CallSuper
    protected open fun observe() {
        viewModel.uiEventLiveData.observe(viewLifecycleOwner, eventObserver)
    }

    private val eventObserver = EventObserver<UIEvent> { uiEvent -> handleEvent(uiEvent) }

    open fun handleEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.RemoveDestination -> navController?.popBackStack(uiEvent.destinationId, true)
            is UIEvent.BackTo -> navController?.popBackStack(uiEvent.destinationId, false)
            is UIEvent.PostExecutionMessage -> {
                setLoading(false)
                snackbar(uiEvent.messageResId)
            }
            is UIEvent.NavigateTo -> navController?.navigate(uiEvent.action, uiEvent.bundle)
            is UIEvent.ShowLoading -> setLoading(true)
            is UIEvent.HideLoading -> setLoading(false)
        }
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    open fun setLoading(show: Boolean) {
        if (show) {
            if (pbLoading == null) {
                pbLoading = LoadingDialog()
            }
            if (pbLoading?.isAdded == false) {
                pbLoading?.show(childFragmentManager, null)
            }
        } else {
            pbLoading?.dismiss()
            pbLoading = null
        }
    }
}