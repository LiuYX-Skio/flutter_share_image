import 'package:flutter/material.dart';
import 'package:flutter_share_image/share_cache_manager.dart';


class ShareCacheImage extends StatefulWidget {

  ShareCacheImage({super.key, this.width = 200, this.height = 200});

  var width;
  var height;

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
   int? textureId = await _shareCacheManager.loadImage();
   print("textureId=$textureId");
    setState(() {
      _textureId = textureId;
    });
  }

  @override
  Future<void> dispose() async {
    super.dispose();
    // _externalPlugin.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        width: widget.width.toDouble(),
        height: widget.height.toDouble(),
        child: _textureId != null
            ? Texture(textureId: _textureId ?? 0)
            : Container(color: Colors.blue));
  }
}
