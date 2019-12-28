package com.marijannovak.autismhelper.common.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.marijannovak.autismhelper.common.InputType
import com.marijannovak.autismhelper.common.Validator

class InputField (private val inputType: InputType = InputType.Unvalidated) {
    /**
     * Track UI input and expose it for two way binding
     */
    val inputLiveData = MutableLiveData<String?>()

    /**
     * Resolve if the field is valid
     */
    fun validate() = Validator.resolveError(inputLiveData.value, inputType) == null

    /**
     * Bind error message to UI
     */
    val errorLiveData: LiveData<Int?>
        get() = Transformations.map(inputLiveData) {
            Validator.resolveError(it, inputType)
        }

    val safeValue: String get() = inputLiveData.value?.trim() ?: ""

}