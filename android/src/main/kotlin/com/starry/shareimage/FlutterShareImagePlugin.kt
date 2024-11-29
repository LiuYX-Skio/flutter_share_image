package com.starry.shareimage


import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.view.Surface
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.TextureRegistry


class FlutterShareImagePlugin : FlutterPlugin, MethodChannel.MethodCallHandler {
    private var textureRegistry: TextureRegistry? = null
    private var surfaceTexture: TextureRegistry.SurfaceTextureEntry? = null
    private var textureId = 0
    private var context: Context? = null
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        textureRegistry = flutterPluginBinding.textureRegistry
        context = flutterPluginBinding.applicationContext
        surfaceTexture = textureRegistry?.createSurfaceTexture()
        textureId = (surfaceTexture?.id() ?: -1).toInt()
        // 注册方法通道
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_share_image")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "loadImage") {
            loadImageTexture(result)
        } else {
            result.notImplemented()
        }
    }

    /**
     *
     * 加载图片并生成纹理
     * */
    private fun loadImageTexture(result: MethodChannel.Result) {
        val bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.img_test)
        textureRegistry?.apply {
            val surface = Surface(surfaceTexture?.surfaceTexture()?.apply {
                setDefaultBufferSize(bitmap.width, bitmap.height)
            })
            val rect = Rect(0, 0, bitmap.width, bitmap.height)
            val canvas = surface.lockCanvas(rect)
            canvas.drawBitmap(bitmap, rect, rect, null)
            bitmap.recycle()
            surface.unlockCanvasAndPost(canvas)
        }
        result.success(textureId)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        surfaceTexture?.release()
        channel.setMethodCallHandler(null)
    }
}
