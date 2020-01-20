package com.marijannovak.autismhelper.data

import com.marijannovak.autismhelper.constants.PrefsConstants.KEY_SOUND_ON
import com.marijannovak.autismhelper.constants.PrefsConstants.KEY_TTS_PITCH
import com.marijannovak.autismhelper.constants.PrefsConstants.KEY_TTS_SPEED
import com.marijannovak.autismhelper.data.models.domain.Settings
import com.tumblr.remember.Remember

class PrefsHelper {
    var soundOn: Boolean
        get() = Remember.getBoolean(KEY_SOUND_ON, false)
        set(value) { Remember.putBoolean(KEY_SOUND_ON, value) }

    var ttsSpeed: Float
        get() = Remember.getFloat(KEY_TTS_SPEED, 0.0f)
        set(value) { Remember.putFloat(KEY_TTS_SPEED, value) }

    var ttsPitch: Float
        get() = Remember.getFloat(KEY_TTS_PITCH, 0.0f)
        set(value) { Remember.putFloat(KEY_TTS_PITCH, value) }

    val settings: Settings get() = Settings(soundOn, ttsSpeed, ttsPitch)

}