package com.rhy.note.cesium;

/**
 * @author: Rhy
 * @datetime: 2024/4/18 17:58
 * @description:
 */
public class CesiumMath {
    public static double sign(double value) {
        value = +value; // coerce to number
        if (value == 0 || value != value) {
            // zero or NaN
            return value;
        }
        return value > 0 ? 1 : -1;
    }
}
