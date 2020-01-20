package com.marijannovak.autismhelper.common.events

import androidx.lifecycle.MutableLiveData

/**
 * Wrapper around live data to work with @see com.marijannovak.autismhelper.common.events
 */
class EventLiveData<T>: MutableLiveData<Event<T>>() {

    fun setEventValue(eventValue: T) {
        super.setValue(Event(eventValue))
    }

    fun postEventValue(eventValue: T) {
        super.postValue(Event(eventValue))
    }
}