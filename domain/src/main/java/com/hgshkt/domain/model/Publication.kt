package com.hgshkt.domain.model

data class Publication(
    val id: String,
    val author: String,
    val date: Int,
    val imageUrl: String,
    val commentariesCount: Int
)
