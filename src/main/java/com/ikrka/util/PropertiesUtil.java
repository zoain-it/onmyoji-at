package com.ikrka.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import cn.hutool.core.lang.Console;

public class PropertiesUtil {

    private static Properties prop = new Properties();

    private PropertiesUtil() {

    }

    public synchronized static void init(String configPath) {
        File config = new File(configPath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(config);
            prop.load(fis);
        } catch (IOException e) {
            Console.log("com.ikrka.util.PropertiesUtil (line : 21) -> IOException : {}", e.getMessage());
        }
    }

    public synchronized static String getByKey(String key) {
        return prop.get(key).toString();
    }

}