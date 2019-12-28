package com.marijannovak.autismhelper.common.events

import androidx.lifecycle.MutableLiveData

class EventLiveData<T>: MutableLiveData<Event<T>>() {

    fun setEventValue(eventValue: T) {
        super.setValue(Event(eventValue))
    }

    fun postEventValue(eventValue: T) {
        super.postValue(Event(eventValue))
    }
}