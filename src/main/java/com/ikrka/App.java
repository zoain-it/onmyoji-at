package com.ikrka;

import com.ikrka.run.ATStart;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        if (args.length >= 2 && ("-config".equals(args[0]) || "-c".equals(args[0]))) {
            ATStart.run(args[1]);
        } else {
            System.err.println("config error.");
        }
    }

    public static String getRealPath() {
        java.net.URL url = App.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        java.io.File file = new java.io.File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }

}
