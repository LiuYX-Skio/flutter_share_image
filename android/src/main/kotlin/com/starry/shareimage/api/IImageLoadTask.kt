package com.starry.shareimage.api

import android.content.Context
import android.graphics.Bitmap

interface IImageLoadTask {

    fun load(
        context: Context?,
        url: String?,
        width: Int,
        height: Int,
        diskCacheType: Int,
        isSkipMemoryCache: Boolean = false,
        block: (Bitmap) -> Unit
    )


    fun load(
        context: Context?,
        url: String?,
        width: Int,
        height: Int,
        diskCacheType: Int,
        isSkipMemoryCache: Boolean = false,
        placeHolderId: Int,
        block: (Bitmap) -> Unit
    )

    fun load(
        context: Context?,
        url: String?,
        width: Int,
        height: Int,
        diskCacheType: Int,
        isSkipMemoryCache: Boolean = false,
        placeHolderId: Int,
        errorId: Int,
        block: (Bitmap) -> Unit
    )

    fun load(
        context: Context?,
        url: String?,
        diskCacheType: Int,
        isSkipMemoryCache: Boolean = false,
        placeHolderId: Int,
        errorId: Int,
        block: (Bitmap) -> Unit
    )

    fun release()

}