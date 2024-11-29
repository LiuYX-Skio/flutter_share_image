import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:flutter_share_image/share_image_interface.dart';


/// An implementation of [FlutterShareImagePlatform] that uses method channels.
class ShareImageMethodChannel extends ShareImagePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_share_image');

  @override
  Future<int?> loadImage() async {
    final textureId = await methodChannel.invokeMethod<int?>('loadImage');
    return textureId;
  }
}
