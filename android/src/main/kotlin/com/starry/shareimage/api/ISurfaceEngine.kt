package com.starry.shareimage.api

import android.content.Context
import android.graphics.Bitmap
import io.flutter.embedding.engine.plugins.FlutterPlugin

interface ISurfaceEngine {

    //创建纹理
    fun createSurfaceTexture(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding)

    //加载图片纹理
    fun loadImageTexture(bitmap: Bitmap, width: Int, height: Int)

    //获取纹理Id
    fun getTextureId(): Int

    //获取上下文对象
    fun getContext(): Context?

    //释放
    fun release()

}