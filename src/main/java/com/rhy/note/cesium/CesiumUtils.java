package com.rhy.note.cesium;

import java.util.Arrays;

/**
 * @author: Rhy
 * @datetime: 2024/4/18 17:24
 * @description:
 */
public class CesiumUtils {
    private static Cartesian3 DEFAULT_WGS84_ONE_OVER_RADII = new Cartesian3(
            1.0 / 6378137.0,
            1.0 / 6378137.0,
            1.0 / 6356752.3142451793
    );
    private static Cartesian3 DEFAULT_WGS84_ONE_OVER_RADII_SQUARED = new Cartesian3(
            1.0 / (6378137.0 * 6378137.0),
            1.0 / (6378137.0 * 6378137.0),
            1.0 / (6356752.3142451793 * 6356752.3142451793)
    );
    private static Double DEFAULT_WGS84_CENTER_TOLERANCE_SQUARED = 0.1;
    private static Cartesian3 cartesianToCartographicP = new Cartesian3();
    private static Cartesian3 scaleToGeodeticSurfaceIntersection = new Cartesian3();
    private static final double EPSILON12 = 1e-12;
    public static Cartographic fromCartesian(Cartesian3 cartesian) {
        Cartesian3 oneOverRadii = DEFAULT_WGS84_ONE_OVER_RADII;
        Cartesian3 oneOverRadiiSquared = DEFAULT_WGS84_ONE_OVER_RADII_SQUARED;
        double centerToleranceSquared = DEFAULT_WGS84_CENTER_TOLERANCE_SQUARED;

        // `cartesian is required.` is thrown from scaleToGeodeticSurface
        Cartesian3 p = scaleToGeodeticSurface(cartesian, oneOverRadii, oneOverRadiiSquared, centerToleranceSquared,null);

        if (p == null) {
            return null;
        }


        Cartesian3 n = Cartesian3.multiplyComponents(
                p,
                oneOverRadiiSquared
        );
        n = Cartesian3.normalize(n);

        Cartesian3 h = Cartesian3.subtract(cartesian, p);

        double longitude = Math.atan2(n.y, n.x);
        double latitude = Math.asin(n.z);
        double height = CesiumMath.sign(Cartesian3.dot(h, cartesian)) * Cartesian3.magnitude(h);

        return new Cartographic(longitude, latitude, height);
    }

    private static Cartesian3 scaleToGeodeticSurface(Cartesian3 cartesian, Cartesian3 oneOverRadii, Cartesian3 oneOverRadiiSquared, double centerToleranceSquared, Cartesian3 result) {
        // Debug checks
        if (cartesian == null) {
            throw new IllegalArgumentException("cartesian is required.");
        }
        if (oneOverRadii == null) {
            throw new IllegalArgumentException("oneOverRadii is required.");
        }
        if (oneOverRadiiSquared == null) {
            throw new IllegalArgumentException("oneOverRadiiSquared is required.");
        }
        if (Double.isNaN(centerToleranceSquared)) {
            throw new IllegalArgumentException("centerToleranceSquared is required.");
        }

        double positionX = cartesian.getX();
        double positionY = cartesian.getY();
        double positionZ = cartesian.getZ();

        double oneOverRadiiX = oneOverRadii.getX();
        double oneOverRadiiY = oneOverRadii.getY();
        double oneOverRadiiZ = oneOverRadii.getZ();

        double x2 = positionX * positionX * oneOverRadiiX * oneOverRadiiX;
        double y2 = positionY * positionY * oneOverRadiiY * oneOverRadiiY;
        double z2 = positionZ * positionZ * oneOverRadiiZ * oneOverRadiiZ;

        // Compute the squared ellipsoid norm.
        double squaredNorm = x2 + y2 + z2;
        double ratio = Math.sqrt(1.0 / squaredNorm);

        // As an initial approximation, assume that the radial intersection is the projection point.
        Cartesian3 intersection = Cartesian3.multiplyByScalar(
                cartesian,
                ratio,
                scaleToGeodeticSurfaceIntersection
        );

        // If the position is near the center, the iteration will not converge.
        if (squaredNorm < centerToleranceSquared) {
            return Double.isInfinite(ratio) ? null : Cartesian3.clone(intersection,result);
        }

        double oneOverRadiiSquaredX = oneOverRadiiSquared.getX();
        double oneOverRadiiSquaredY = oneOverRadiiSquared.getY();
        double oneOverRadiiSquaredZ = oneOverRadiiSquared.getZ();

        // Use the gradient at the intersection point in place of the true unit normal.
        // The difference in magnitude will be absorbed in the multiplier.
        Cartesian3 gradient = new Cartesian3();
        gradient.setX(intersection.getX() * oneOverRadiiSquaredX * 2.0);
        gradient.setY(intersection.getY() * oneOverRadiiSquaredY * 2.0);
        gradient.setZ(intersection.getZ() * oneOverRadiiSquaredZ * 2.0);

        // Compute the initial guess at the normal vector multiplier, lambda.
        double lambda =
                ((1.0 - ratio) * Cartesian3.magnitude(cartesian)) /
                        (0.5 * Cartesian3.magnitude(gradient));
        double correction = 0.0;

        double func;
        double denominator;
        double xMultiplier;
        double yMultiplier;
        double zMultiplier;
        double xMultiplier2;
        double yMultiplier2;
        double zMultiplier2;
        double xMultiplier3;
        double yMultiplier3;
        double zMultiplier3;

        do {
            lambda -= correction;

            xMultiplier = 1.0 / (1.0 + lambda * oneOverRadiiSquaredX);
            yMultiplier = 1.0 / (1.0 + lambda * oneOverRadiiSquaredY);
            zMultiplier = 1.0 / (1.0 + lambda * oneOverRadiiSquaredZ);

            xMultiplier2 = xMultiplier * xMultiplier;
            yMultiplier2 = yMultiplier * yMultiplier;
            zMultiplier2 = zMultiplier * zMultiplier;

            xMultiplier3 = xMultiplier2 * xMultiplier;
            yMultiplier3 = yMultiplier2 * yMultiplier;
            zMultiplier3 = zMultiplier2 * zMultiplier;

            func = x2 * xMultiplier2 + y2 * yMultiplier2 + z2 * zMultiplier2 - 1.0;

            // "denominator" here refers to the use of this expression in the velocity and acceleration
            // computations in the sections to follow.
            denominator =
                    x2 * xMultiplier3 * oneOverRadiiSquaredX +
                            y2 * yMultiplier3 * oneOverRadiiSquaredY +
                            z2 * zMultiplier3 * oneOverRadiiSquaredZ;

            double derivative = -2.0 * denominator;

            correction = func / derivative;
        } while (Math.abs(func) > EPSILON12);

        return new Cartesian3(
                positionX * xMultiplier,
                positionY * yMultiplier,
                positionZ * zMultiplier
        );
    }

    public static void main(String[] args) {
        Cartesian3 certesian = new Cartesian3(-2104279.0,4716307.5,3730728.0);
        Cartographic res = fromCartesian(certesian);
        System.out.println(res);
        System.out.println(Math.toDegrees(res.longitude));
        System.out.println(Math.toDegrees(res.latitude));
    }
}
