package com.marijannovak.autismhelper.data

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class StringProvider @Inject constructor(@ActivityContext val context: Context) {

    fun getString(resId: Int, vararg formatValues: Any) =
        context.resources.getString(resId, *formatValues)
}