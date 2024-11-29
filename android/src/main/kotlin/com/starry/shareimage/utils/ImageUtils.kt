package com.starry.shareimage.utils

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.starry.shareimage.consts.Constants

/**
 * 图片工具类
 * */
object ImageUtils {

    /**
     * 获取磁盘缓存类型
     * @param diskCacheType 缓存类型
     * */
    fun getDiskCacheType(diskCacheType: Int): DiskCacheStrategy {
        return when (diskCacheType) {
            Constants.IMAGE_DISK_CACHE_ALL -> {
                DiskCacheStrategy.ALL
            }

            Constants.IMAGE_DISK_CACHE_DATA -> {
                DiskCacheStrategy.DATA
            }

            Constants.IMAGE_DISK_CACHE_RESOURCE -> {
                DiskCacheStrategy.RESOURCE
            }

            Constants.IMAGE_DISK_CACHE_NONE -> {
                DiskCacheStrategy.NONE
            }

            else -> {
                DiskCacheStrategy.ALL
            }
        }
    }
}