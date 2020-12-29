package com.marijannovak.autismhelper.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.marijannovak.autismhelper.common.events.EventObserver
import com.marijannovak.autismhelper.common.events.UIEvent
import com.marijannovak.autismhelper.common.extensions.navController
import com.marijannovak.autismhelper.common.extensions.snackbar
import com.marijannovak.autismhelper.ui.dialogs.LoadingDialog

abstract class BaseFragment<S, E> : Fragment() {

    /**
     * Provide the ViewModel
     */
    abstract val viewModel: BaseViewModel<S, E>

    /**
     * DialogFragment to add on top of fragment to show loading
     */
    private var pbLoading: LoadingDialog? = null

    /**
     * Observe ViewModel LiveData changes. Override to add subscriptions
     */
    @CallSuper
    protected open fun observe() {
        viewModel.uiEventLiveData.observe(viewLifecycleOwner, eventObserver)
    }

    private val eventObserver = EventObserver<UIEvent> { uiEvent -> handleEvent(uiEvent) }

    /**
     * Default UIEvent handling
     */
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

    /**
     * Show or hide the loading overlay (dialog)
     */
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