package com.marijannovak.autismhelper.data

import com.tumblr.remember.Remember

const val KEY_SESSION_ID = "session_id"

class PrefsHelper {

    var sessionId: String
        get() {
            return Remember.getString(KEY_SESSION_ID, "")
        }
        set(value) {
            Remember.putString(KEY_SESSION_ID, value)
        }

}