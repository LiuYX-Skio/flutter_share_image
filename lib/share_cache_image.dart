import 'package:flutter/material.dart';
import 'package:flutter_share_image/share_cache_manager.dart';

class ShareCacheImage extends StatefulWidget {
  final int width;
  final int height;
  final int diskCacheType;
  final bool isSkipMemoryCache;
  final String? imageUrl;

  const ShareCacheImage({
    super.key,
    this.width = 0,
    this.height = 0,
    this.isSkipMemoryCache = false,
    this.imageUrl = "",
    this.diskCacheType = 0,
  });

  @override
  State<StatefulWidget> createState() {
    return _ShareCacheState();
  }
}

class _ShareCacheState extends State<ShareCacheImage> {
  final _shareCacheManager = ShareCacheManager();

  int? _textureId;

  @override
  void initState() {
    super.initState();
    initPlugin();
  }

  void initPlugin() async {
    int? textureId = await _shareCacheManager.loadImage(
        widget.imageUrl,
        widget.width,
        widget.height,
        widget.diskCacheType,
        widget.isSkipMemoryCache);
    if (mounted) {
      setState(() {
        _textureId = textureId;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
        width: widget.width.toDouble(),
        height: widget.height.toDouble(),
        child: _textureId != null
            ? Texture(textureId: _textureId ?? 0)
            : Container(color: Colors.white));
  }
}
