package com.matigurten.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Mati on 27/12/2016.
 */

public class BitmapUtils {

    //    static util methods
    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, false);
    }

    public static Bitmap shrinkBitmap(Bitmap source, float sizeFactor) {
        Matrix matrix = new Matrix();
        matrix.postScale(sizeFactor, sizeFactor);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, false);
    }

}
