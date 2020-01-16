package com.marijannovak.autismhelper.data.models.domain

data class Child(
    val id: String,
    val parentId: String,
    val name: String,
    val gender: String,
    val dateOfBirth: Long
)