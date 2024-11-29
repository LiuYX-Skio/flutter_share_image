import 'package:flutter_share_image/share_image_interface.dart';

class ShareCacheManager {
  Future<int?> loadImage(String? imageUrl, int width, int height,
      int diskCacheType, bool isSkipMemoryCache) {
    return ShareImagePlatform.instance
        .loadImage(imageUrl, width, height, diskCacheType, isSkipMemoryCache);
  }
}
