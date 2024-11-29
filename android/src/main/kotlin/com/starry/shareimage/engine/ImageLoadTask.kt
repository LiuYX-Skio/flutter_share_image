package com.starry.shareimage.engine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.starry.shareimage.R
import com.starry.shareimage.api.IImageLoadTask
import com.starry.shareimage.extend.isValid
import com.starry.shareimage.utils.ImageUtils

class ImageLoadTask : IImageLoadTask {

    private val handler = Handler(Looper.getMainLooper())

    override fun load(
        context: Context?,
        url: String?,
        width: Int,
        height: Int,
        diskCacheType: Int,
        isSkipMemoryCache: Boolean,
        block: (Bitmap) -> Unit
    ) {
        load(
            context,
            url,
            width,
            height,
            diskCacheType,
            isSkipMemoryCache,
            R.drawable.default_dafault_image,
            R.drawable.default_error_image,
            block
        )
    }

    /**
     * 加载图片
     * @param context 图片控件
     * @param url  网络图片地址
     */
    override fun load(
        context: Context?,
        url: String?,
        width: Int,
        height: Int,
        diskCacheType: Int,
        isSkipMemoryCache: Boolean,
        placeHolderId: Int,
        block: (Bitmap) -> Unit
    ) {
        load(
            context,
            url,
            width,
            height,
            diskCacheType,
            isSkipMemoryCache,
            placeHolderId,
            R.drawable.default_error_image,
            block
        )
    }

    override fun load(
        context: Context?,
        url: String?,
        width: Int,
        height: Int,
        diskCacheType: Int,
        isSkipMemoryCache: Boolean,
        placeHolderId: Int,
        errorId: Int,
        block: (Bitmap) -> Unit
    ) {
        if (context == null || url.isNullOrEmpty() || !context.isValid()) {
            return
        }
        if (width == 0 || height == 0) {
            load(context, url, diskCacheType, isSkipMemoryCache, placeHolderId, errorId, block)
            return
        }
        var cacheBitmap: Bitmap? = null
        val options: RequestOptions = RequestOptions()
            .diskCacheStrategy(ImageUtils.getDiskCacheType(diskCacheType))
        Glide.with(context)
            .asBitmap()
            .apply(options)
            .load(url)
            .override(width, height)
            .placeholder(placeHolderId)
            .error(errorId)
            .transition(BitmapTransitionOptions.withCrossFade())
            .skipMemoryCache(isSkipMemoryCache)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap?>?) {
                    cacheBitmap = bitmap
                    handler.post {
                        block.invoke(bitmap)
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    cacheBitmap = null
                }
            })
    }

    override fun load(
        context: Context?,
        url: String?,
        diskCacheType: Int,
        isSkipMemoryCache: Boolean,
        placeHolderId: Int,
        errorId: Int,
        block: (Bitmap) -> Unit
    ) {
        if (context == null || url.isNullOrEmpty() || !context.isValid()) {
            return
        }
        var cacheBitmap: Bitmap? = null
        val options: RequestOptions = RequestOptions()
            .diskCacheStrategy(ImageUtils.getDiskCacheType(diskCacheType))
        Glide.with(context)
            .asBitmap()
            .apply(options)
            .load(url)
            .placeholder(placeHolderId)
            .error(errorId)
            .transition(BitmapTransitionOptions.withCrossFade())
            .skipMemoryCache(isSkipMemoryCache)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap?>?) {
                    cacheBitmap = bitmap
                    handler.post {
                        block.invoke(bitmap)
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    cacheBitmap = null
                }
            })
    }

}
