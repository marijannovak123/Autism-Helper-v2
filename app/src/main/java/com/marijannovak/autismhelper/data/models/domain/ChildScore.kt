package com.marijannovak.autismhelper.data.models.domain

data class ChildScore(
    val id: Int,
    val childId: String,
    val parentId: String,
    val timestamp: Long,
    val duration: Long,
    val mistakes: Int
)