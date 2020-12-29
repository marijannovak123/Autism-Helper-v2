package com.marijannovak.autismhelper.data

import com.marijannovak.autismhelper.data.models.domain.Settings
import com.tumblr.remember.Remember
import javax.inject.Inject
import javax.inject.Singleton

interface Prefs {

    companion object {
        const val PREFS_NAME = "autism_helper_prefs"

        const val KEY_PARENT_PASSWORD = "parent_password"
        const val KEY_SOUND_ON = "sound_on"
        const val KEY_TTS_PITCH = "tts_pitch"
        const val KEY_TTS_SPEED = "tts_speed"
        const val KEY_LAST_SYNC = "last_sync"
    }

    var lastSync: Long

    var soundOn: Boolean

    var ttsSpeed: Float

    var ttsPitch: Float

    val settings: Settings
}

class PrefsImpl @Inject constructor(): Prefs {

    override var lastSync: Long
        get() = Remember.getLong(Prefs.KEY_LAST_SYNC, 0)
        set(value) {
            Remember.putLong(Prefs.KEY_LAST_SYNC, value)
        }

    override var soundOn: Boolean
        get() = Remember.getBoolean(Prefs.KEY_SOUND_ON, false)
        set(value) {
            Remember.putBoolean(Prefs.KEY_SOUND_ON, value)
        }

    override var ttsSpeed: Float
        get() = Remember.getFloat(Prefs.KEY_TTS_SPEED, 0.0f)
        set(value) {
            Remember.putFloat(Prefs.KEY_TTS_SPEED, value)
        }

    override var ttsPitch: Float
        get() = Remember.getFloat(Prefs.KEY_TTS_PITCH, 0.0f)
        set(value) {
            Remember.putFloat(Prefs.KEY_TTS_PITCH, value)
        }

    override val settings: Settings get() = Settings(soundOn, ttsSpeed, ttsPitch)
}