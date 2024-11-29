

import 'package:flutter_share_image/share_image_interface.dart';

class ShareCacheManager {
  Future<int?> loadImage() {
    return ShareImagePlatform.instance.loadImage();
  }
}
