package com.marijannovak.autismhelper.common.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * @param R request type being generated from this form
 */
abstract class UIForm<R> {
    /**
     * Fields that should be checked when validating the form, none by default
     */
    open val validatableFields: List<InputField> = listOf()

    /**
     * Track whole form validity
     */
    private val isValidInternalLiveData by lazy {
        MediatorLiveData<Boolean?>().apply {
            validatableFields.forEach {
                addSource(it.inputLiveData) {
                    value = validate()
                }
            }
        }
    }

    /**
     * Expose form validity to UI
     */
    val isValidLiveData: LiveData<Boolean?> get() = isValidInternalLiveData

    /**
     * Check if all validatable fields are valid
     */
    open fun validate(): Boolean {
        validatableFields.forEach { field ->
            if (!field.validate()) {
                return false
            }
        }
        return true
    }

    /**
     * Get a generated request
     */
    abstract val request: R
}