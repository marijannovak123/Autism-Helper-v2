package com.marijannovak.autismhelper.common.events

import android.os.Bundle

sealed class UIEvent {
    data class RemoveDestination(val destinationId: Int): UIEvent()
    data class BackTo(val destinationId: Int): UIEvent()
    data class PostExecutionMessage(val messageResId: Int): UIEvent()
    data class NavigateTo(val action: Int, val bundle: Bundle? = null): UIEvent()
    object ShowLoading: UIEvent()
    object HideLoading: UIEvent()
}
