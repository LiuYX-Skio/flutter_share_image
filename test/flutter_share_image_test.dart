import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_share_image/flutter_share_image.dart';
import 'package:flutter_share_image/flutter_share_image_platform_interface.dart';
import 'package:flutter_share_image/flutter_share_image_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterShareImagePlatform
    with MockPlatformInterfaceMixin
    implements FlutterShareImagePlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterShareImagePlatform initialPlatform = FlutterShareImagePlatform.instance;

  test('$MethodChannelFlutterShareImage is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterShareImage>());
  });

  test('getPlatformVersion', () async {
    FlutterShareImage flutterShareImagePlugin = FlutterShareImage();
    MockFlutterShareImagePlatform fakePlatform = MockFlutterShareImagePlatform();
    FlutterShareImagePlatform.instance = fakePlatform;

    expect(await flutterShareImagePlugin.getPlatformVersion(), '42');
  });
}
