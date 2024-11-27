import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_share_image_platform_interface.dart';

/// An implementation of [FlutterShareImagePlatform] that uses method channels.
class MethodChannelFlutterShareImage extends FlutterShareImagePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_share_image');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
