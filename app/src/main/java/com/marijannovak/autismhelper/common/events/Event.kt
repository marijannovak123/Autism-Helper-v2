package com.marijannovak.autismhelper.common.events

data class Event<out T>(private val content: T) {

    private var handled = false

    /**
     * Returns the content and put the event has handled
     */
    private fun take(): T? {
        return if (handled) {
            null
        } else {
            handled = true
            content
        }
    }

    /**
     * Return and execute code on given value
     * put the event has handled
     */
    fun take(code: (T) -> Unit) {
        take()?.let(code)
    }

}