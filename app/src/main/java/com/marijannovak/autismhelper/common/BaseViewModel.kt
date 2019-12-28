package com.marijannovak.autismhelper.common

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marijannovak.autismhelper.common.events.Event
import com.marijannovak.autismhelper.common.events.EventLiveData
import com.marijannovak.autismhelper.common.events.UIEvent
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    private val defaultErrorHandler = { throwable: Throwable ->
        val messageResId = ErrorHandler.resolveExceptionMessageId(throwable)
        notifyEvent(UIEvent.PostExecutionMessage(messageResId))
    }

    val uiEventLiveData = EventLiveData<UIEvent>()

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