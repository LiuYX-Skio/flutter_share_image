import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_share_image_method_channel.dart';

abstract class FlutterShareImagePlatform extends PlatformInterface {
  /// Constructs a FlutterShareImagePlatform.
  FlutterShareImagePlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterShareImagePlatform _instance = MethodChannelFlutterShareImage();

  /// The default instance of [FlutterShareImagePlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterShareImage].
  static FlutterShareImagePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterShareImagePlatform] when
  /// they register themselves.
  static set instance(FlutterShareImagePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
