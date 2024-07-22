package com.hgshkt.data.local.image

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import java.io.OutputStream
import java.net.URL
import java.util.UUID

class LocalImageStorageImpl(
    private val context: Context
) : LocalImageStorage {

    override suspend fun save(url: String?) {
        try {
            val bitmap = bitmapFromUrl(url!!)!!

            val outputStream: OutputStream?
            val displayName = UUID.randomUUID().toString()
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            outputStream = resolver.openOutputStream(imageUri!!)

            outputStream.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it!!)
                it.flush()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun bitmapFromUrl(url: String): Bitmap? {
        val imageUrl = URL(url)
        return BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
    }
}