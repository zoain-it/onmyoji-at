package com.ikrka.run;

import com.ikrka.common.LibLoad;
import com.ikrka.ui.IndexUi;

/**
 * 程序执行入口
 * 
 * @author zoain
 */
public class ATStart {

    public static void run() {
        LibLoad.load();
        IndexUi indexUi = new IndexUi();
        indexUi.showEvent();
    }

}