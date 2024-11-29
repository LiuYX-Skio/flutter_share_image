package com.starry.shareimage.engine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.view.Surface
import com.starry.shareimage.api.ISurfaceEngine
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.view.TextureRegistry

class SurfaceEngine : ISurfaceEngine {

    private var textureRegistry: TextureRegistry? = null
    private var surfaceTexture: TextureRegistry.SurfaceTextureEntry? = null
    private var textureId = 0
    private var context: Context? = null
    override fun createSurfaceTexture(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        textureRegistry = flutterPluginBinding.textureRegistry
        context = flutterPluginBinding.applicationContext
        surfaceTexture = textureRegistry?.createSurfaceTexture()
        textureId = (surfaceTexture?.id() ?: -1).toInt()
    }

    override fun loadImageTexture(bitmap: Bitmap, width: Int, height: Int) {
        textureRegistry?.apply {
            val surface = Surface(surfaceTexture?.surfaceTexture()?.apply {
                setDefaultBufferSize(width, height)
            })
            val rect = Rect(0, 0, width, height)
            val canvas = surface.lockCanvas(rect)
            canvas.drawBitmap(bitmap, rect, rect, null)
            surface.unlockCanvasAndPost(canvas)
        }
    }

    override fun getTextureId(): Int = textureId
    override fun getContext(): Context? = context
    override fun release() {
        surfaceTexture?.surfaceTexture()?.release()
        surfaceTexture?.release()
    }

}