package com.marijannovak.autismhelper.common.base

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marijannovak.autismhelper.common.errors.ErrorHandler
import com.marijannovak.autismhelper.common.events.EventLiveData
import com.marijannovak.autismhelper.common.events.UIEvent
import com.marijannovak.autismhelper.data.repository.ResourceRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel: ViewModel(), KoinComponent {

    private val defaultErrorHandler = { throwable: Throwable ->
        val messageResId = ErrorHandler.resolveExceptionMessageId(throwable)
        notifyEvent(UIEvent.PostExecutionMessage(messageResId))
    }

    val uiEventLiveData = EventLiveData<UIEvent>()

    protected val res: ResourceRepository by inject()

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

    protected fun notifyEvent(uiEvent: UIEvent) {
        uiEventLiveData.postEventValue(uiEvent)
    }

    protected fun navigate(@IdRes action: Int) {
        notifyEvent(UIEvent.NavigateTo(action))
    }

}