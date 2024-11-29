import 'package:flutter/material.dart';
import 'package:flutter_share_image/share_cache_image.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  List<String> listImage = [
    "https://img2.baidu.com/it/u=3565369971,2082314928&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1732986000&t=08598d4ee0a5b5ca647292a9be2b9b1d",
    "https://img2.baidu.com/it/u=585401318,2246982&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1732986000&t=00ce502c842d6a337330984136e4c51f",
    "https://img1.baidu.com/it/u=3947465130,3009712317&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1732986000&t=44f3d3c5e6095813a43ea2a45bb2baa4",
    "https://img2.baidu.com/it/u=1041191017,3841558816&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1732986000&t=d2e3064d535a2b92c6fe8f6b70c08e03",
    "https://img1.baidu.com/it/u=2033371373,2531711947&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500",
    "https://img0.baidu.com/it/u=4253165415,1339865247&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281",
    "https://img0.baidu.com/it/u=2960190337,216864463&fm=253&fmt=auto&app=120&f=JPEG?w=759&h=426",
    "https://img1.baidu.com/it/u=2630836832,1672810284&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=667",
    "https://img0.baidu.com/it/u=2509425468,2477277571&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=333",
    "https://img1.baidu.com/it/u=3821883389,2247186365&fm=253&fmt=auto&app=138&f=JPEG?w=798&h=500",
    "https://img2.baidu.com/it/u=2337103383,198140788&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500",
  ];

  @override
  void initState() {
    super.initState();
  }

  List<Widget> getImageList() {
    List<ShareCacheImage> list = [];
    for (int i = 0; i < 10; i++) {
      for (var value in listImage) {
        list.add(ShareCacheImage(imageUrl: value, width: 500, height: 150));
      }
    }
    return list;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: ListView(
          children: getImageList(),
        ),
      ),
    );
  }
}
