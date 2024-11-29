import 'package:flutter_share_image/share_image_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';


abstract class ShareImagePlatform extends PlatformInterface {
  /// Constructs a FlutterShareImagePlatform.
  ShareImagePlatform() : super(token: _token);

  static final Object _token = Object();

  static ShareImagePlatform _instance = ShareImageMethodChannel();

  ///
  /// Defaults to [MethodChannelFlutterShareImage].
  static ShareImagePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterShareImagePlatform] when
  /// they register themselves.
  static set instance(ShareImagePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<int?> loadImage(String? imageUrl,int width,int height,int diskCacheType,bool isSkipMemoryCache) {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
