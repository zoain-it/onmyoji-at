package com.ikrka.core;

import java.util.HashMap;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImgMatch {

    public static Map<String, Integer> match(String target, String source) {
        Mat s, t;
        s = Imgcodecs.imread(source);
        t = Imgcodecs.imread(target);
        Mat result = Mat.zeros(s.rows() - t.rows() + 1, s.cols() - t.cols() + 1, CvType.CV_32FC1);
        Imgproc.matchTemplate(s, t, result, Imgproc.TM_SQDIFF_NORMED);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
        Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
        Point matchLoc = mlr.minLoc;
        return new HashMap<>() {
            private static final long serialVersionUID = 1L;
            {
                put("x", (int) matchLoc.x);
                put("y", (int) matchLoc.y);
            }
        };
    }

}