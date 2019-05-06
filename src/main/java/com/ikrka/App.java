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

}
