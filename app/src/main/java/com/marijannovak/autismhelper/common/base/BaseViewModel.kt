package com.marijannovak.autismhelper.common.base

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marijannovak.autismhelper.common.errors.ErrorHandler
import com.marijannovak.autismhelper.common.events.EventLiveData
import com.marijannovak.autismhelper.common.events.UIEvent
import com.marijannovak.autismhelper.data.repository.ResourceRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel: ViewModel(), KoinComponent {

    /**
     * Default error handler for the [suspendCall] fail
     * Can be replaced with [CoroutineExceptionHandler]
     */
    private val defaultErrorHandler = { throwable: Throwable ->
        val messageResId = ErrorHandler.resolveExceptionMessageId(throwable)
        notifyEvent(UIEvent.PostExecutionMessage(messageResId))
    }

    val uiEventLiveData = EventLiveData<UIEvent>()

    /**
     * Fetch strings etc. from resources
     */
    protected val res: ResourceRepository by inject()

    /**
     * Launch a coroutine suspend function in the ViewModel scope
     * @param errorHandler Handle the error
     * @param block Coroutine code block to launch
     */
    fun <T> suspendCall(
        errorHandler: (Throwable) -> Unit = defaultErrorHandler,
        block: suspend () -> T) {
        viewModelScope.launch {
            try {
                block()
            } catch (t: Throwable) {
                errorHandler(t)
            }
        }
    }

    /**
     * Post any event
     * @param uiEvent Event to post
     */
    protected fun notifyEvent(uiEvent: UIEvent) {
        uiEventLiveData.postEventValue(uiEvent)
    }

    /**
     * Post navigation event
     * @param action Navigation action resource id
     */
    protected fun navigate(@IdRes action: Int) {
        notifyEvent(UIEvent.NavigateTo(action))
    }

}