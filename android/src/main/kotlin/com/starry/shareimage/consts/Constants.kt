package com.starry.shareimage.consts

object Constants {
    const val IMAGE_DISK_CACHE_ALL = 0 //缓存图像的所有版本，包括原图和缩略图
    const val IMAGE_DISK_CACHE_RESOURCE = 1 //只缓存变换后的图像（例如缩放后的图像）。不缓存原始数据
    const val IMAGE_DISK_CACHE_DATA = 2 //只缓存原始的图像数据（不缓存经过变换后的图像）
    const val IMAGE_DISK_CACHE_NONE = 3 //不缓存图像到磁盘
}