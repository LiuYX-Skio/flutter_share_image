package com.starry.shareimage


import com.starry.shareimage.api.IImageLoadTask
import com.starry.shareimage.api.ISurfaceEngine
import com.starry.shareimage.engine.ImageLoadTask
import com.starry.shareimage.engine.SurfaceEngine
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class FlutterShareImagePlugin : FlutterPlugin, MethodChannel.MethodCallHandler {

    private lateinit var channel: MethodChannel

    private val imageLoadTask: IImageLoadTask by lazy { ImageLoadTask() }
    private val surfaceEngine: ISurfaceEngine by lazy { SurfaceEngine() }

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        surfaceEngine.createSurfaceTexture(flutterPluginBinding)
        // 注册方法通道
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_share_image")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "loadImage") {
            loadImageTexture(call, result)
        } else {
            result.notImplemented()
        }
    }

    /**
     * 加载图片并生成纹理
     * */
    private fun loadImageTexture(call: MethodCall, result: MethodChannel.Result) {
        val imageUrl: String? = call.argument("imageUrl")
        val width: Int? = call.argument("width")
        val height: Int? = call.argument("height")
        val isSkipMemoryCache: Boolean? = call.argument("isSkipMemoryCache")
        val diskCacheType: Int? = call.argument("diskCacheType")
        imageLoadTask.load(
            surfaceEngine.getContext(),
            imageUrl, width ?: 0, height ?: 0, diskCacheType ?: 0, isSkipMemoryCache ?: false
        ) {
            surfaceEngine.loadImageTexture(it, it.width, it.height)
        }
        result.success(surfaceEngine.getTextureId())
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        imageLoadTask.release()
        surfaceEngine.release()
        channel.setMethodCallHandler(null)
    }

}
