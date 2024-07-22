package com.hgshkt.data.local.image

interface LocalImageStorage {
    suspend fun save(url: String?)
}