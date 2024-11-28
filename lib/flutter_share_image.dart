
import 'flutter_share_image_platform_interface.dart';

class FlutterShareImage {
  Future<String?> getPlatformVersion() {
    return FlutterShareImagePlatform.instance.getPlatformVersion();
  }

}
