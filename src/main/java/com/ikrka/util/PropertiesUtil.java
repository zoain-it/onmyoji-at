package com.ikrka.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties prop = new Properties();

    public synchronized static void init(String configPath) {
        File config = new File(configPath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(config);
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static String getByKey(String key) {
        return prop.get(key).toString();
    }

}