package com.rhy.note.cesium;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Rhy
 * @datetime: 2024/4/18 17:26
 * @description:
 */
@Data
@Accessors(chain = true)
public class Cartesian3 {
    public Cartesian3() {
    }

    public Cartesian3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x;
    public double y;
    public double z;



    public static double magnitude(Cartesian3 cartesian) {
        return Math.sqrt(Cartesian3.magnitudeSquared(cartesian));
    }
    public static double magnitudeSquared(Cartesian3 cartesian) {
        return (
                cartesian.x * cartesian.x +
                cartesian.y * cartesian.y +
                cartesian.z * cartesian.z
        );
    }

    public static Cartesian3 multiplyByScalar(Cartesian3 cartesian,double scalar,Cartesian3 result) {
        if (result == null) {
            result = new Cartesian3();
        }
        result.x = cartesian.x * scalar;
        result.y = cartesian.y * scalar;
        result.z = cartesian.z * scalar;
        return result;
    }

    public static Cartesian3 clone(Cartesian3 cartesian,Cartesian3 result) {
        result.x = cartesian.x;
        result.y = cartesian.y;
        result.z = cartesian.z;
        return result;
    }

    public static Cartesian3 multiplyComponents(Cartesian3 left, Cartesian3  right) {
        Cartesian3 result = new Cartesian3();
        result.x = left.x * right.x;
        result.y = left.y * right.y;
        result.z = left.z * right.z;
        return result;
    }

    public static Cartesian3 normalize(Cartesian3 cartesian) {

        double magnitude = Cartesian3.magnitude(cartesian);
        Cartesian3 result = new Cartesian3();
        result.x = cartesian.x / magnitude;
        result.y = cartesian.y / magnitude;
        result.z = cartesian.z / magnitude;
        return result;
    }

    public static Cartesian3 subtract(Cartesian3 left, Cartesian3 right) {

        Cartesian3 result = new Cartesian3();
        result.x = left.x - right.x;
        result.y = left.y - right.y;
        result.z = left.z - right.z;
        return result;
    }

    public static double dot(Cartesian3 left, Cartesian3 right) {
        return left.x * right.x + left.y * right.y + left.z * right.z;
    }

    @Override
    public String toString() {
        return "Cartesian3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
