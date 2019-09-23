package com.ikrka.core;

import java.util.HashMap;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImgMatch {

  private ImgMatch() {

  }

  public static Map<String, Integer> match(String target, String source, String... out) {
    Mat t, s;
    t = Imgcodecs.imread(target);
    s = Imgcodecs.imread(source);
    Mat result = Mat.zeros(s.rows() - t.rows() + 1, s.cols() - t.cols() + 1, CvType.CV_32FC1);
    Imgproc.matchTemplate(s, t, result, Imgproc.TM_CCOEFF_NORMED);
    Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
    Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
    Point matchLoc = mlr.maxLoc;
    if (out.length > 0 && !"".equals(out[0])) {
      Imgproc.rectangle(s, matchLoc, new Point(matchLoc.x + t.width(), matchLoc.y + t.height()), new Scalar(0, 255, 0));
      Imgcodecs.imwrite(out[0], s);
    }
    Map<String, Integer> coordinate = new HashMap<>();
    coordinate.put("x", (int) matchLoc.x);
    coordinate.put("y", (int) matchLoc.y);
    return coordinate;
  }

}