package com.matigurten.util;

/**
 * Created by Mati on 27/12/2016.
 */

public class MathUtils {
    public static double angleDifferenceRad(double angle1, double angle2) {
        return Math.abs(mod(angle1 - angle2 + Math.PI, 2 * Math.PI) - Math.PI);
    }

    public static double mod(double num, double mod) throws IllegalArgumentException {
        if (mod > 0) {
            return (num >= 0 ? num % mod : num % mod + mod);
        } else {
            throw new IllegalArgumentException("Modulo must be a positive number");
        }
    }
}
