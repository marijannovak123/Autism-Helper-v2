package com.marijannovak.autismhelper.data.storage

import com.marijannovak.autismhelper.data.PrefsHelper

class AuthStorage (private val prefs: PrefsHelper) {

    fun saveSessionId(sessionId: String) {
        prefs.sessionId = sessionId
    }

}