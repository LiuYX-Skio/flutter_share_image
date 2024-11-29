package com.starry.shareimage.extend

import android.app.Activity
import android.content.Context

/**
 * 新增判断当前的页面是否已经销毁了
 */
fun Context?.isValid(): Boolean {
    if (this is Activity) {
        if (this.isFinishing || this.isDestroyed) {
            return false
        }
    }
    return true
}
