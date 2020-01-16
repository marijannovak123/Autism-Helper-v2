package com.marijannovak.autismhelper.data.repository

import android.content.Context

class ResourceRepository(val context: Context) {

    fun getString(resId: Int, vararg formatValues: Any) =
        context.resources.getString(resId, *formatValues)

}