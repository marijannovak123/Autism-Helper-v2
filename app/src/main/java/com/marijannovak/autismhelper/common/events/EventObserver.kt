package com.marijannovak.autismhelper.common.events

import androidx.lifecycle.Observer

typealias OnEventValueChanged<T> = (T) -> Unit

/**
 * Handle {@link package com.marijannovak.autismhelper.common.events.EventLiveData}
 */
class EventObserver<T>(
    private val onEventValueChanged: OnEventValueChanged<T>
): Observer<Event<T>> {

    override fun onChanged(event: Event<T>?) {
        event?.take(onEventValueChanged)
    }
}