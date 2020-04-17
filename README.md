### 阴阳师辅助

1.支持御魂任意模式及层数<br>
2.支持觉醒任意模式及层数

> 使用环境说明 ：<br>

    1.使用前需安装OpenCV4.1.0版本，且JDK版本为11。
    2.安卓模拟器无限制，但是仅支持1280x720分辨率(Mac版本不需要开2倍屏)，游戏内默认精细画质。
    
[关于adb连接错误解决方案](https://blog.csdn.net/leon1741/article/details/54896861)

> 启动说明 ：<br>

    mvn clean -Dmaven.test.skip=true assembly:assembly

    java -jar target/onmyoji-at-1.0-SNAPSHOT-jar-with-dependencies.jar -l /User/onmyoji/libopencv_java410.dylib -a /usr/bin/adb -t /User/onmyoji/temp/

> 参数说明 ：<br>

    -l // opencv驱动 （必须。Windows为dll，MAC为dylib）

    -a // adb工具 （若已加入环境变量可不设置）

    -t // 模板图路径 （必须）


声明：本软件及代码仅供学习参考不得进行任何商业用途否则产生的一切后果将由使用者本人承担。
