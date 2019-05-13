### 阴阳师辅助

1.支持御魂任意模式及层数(目前仅支持八岐大蛇)<br>
2.支持觉醒任意模式及层数

> 使用环境说明 :<br>

    1.使用前需安装OpenCV4.1.0版本，且JDK版本为11。
    2.安卓模拟器无限制，但是仅支持1280x720分辨率(Mac版本不需要开2倍屏)，游戏内默认精细画质。
    
[关于adb连接错误解决方案](https://blog.csdn.net/leon1741/article/details/54896861)

> 启动配置说明 :<br>

    mvn clean -Dmaven.test.skip=true assembly:assembly

    java -jar target/onmyoji-at-1.0-SNAPSHOT-jar-with-dependencies.jar -c /home/root/config.properties

    // "/home/root/config.properties" 替换成自己的配置路径

> 配置说明 :<br>

    // OpenCV驱动地址
    libPath

    // ADB截图保存路径(多个截图文件是为了避免多线程操作时文件占用的问题)
    screenShotSavePath

    // ADB截图保存路径1
    screenShotSavePath1

    // ADB截图保存路径2
    screenShotSavePath2

    // 模板图路径(需要带最后一个斜杠, 代码可以判断, 但是我不想)
    tempPath

    // adb命令路径
    adb

交流QQ群 : 996806566

版权声明本软件及代码仅供学习参考不得进行任何商业用途否则产生的一切后果将由使用者本人承担
