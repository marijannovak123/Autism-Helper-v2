package com.marijannovak.autismhelper.common.events

import androidx.lifecycle.Observer

typealias OnEventValueChanged<T> = (T) -> Unit

class EventObserver<T>(
    private val onEventValueChanged: OnEventValueChanged<T>
): Observer<Event<T>> {

    override fun onChanged(event: Event<T>?) {
        event?.take(onEventValueChanged)
    }
}