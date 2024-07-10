package com.rhy.note.cesium;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Rhy
 * @datetime: 2024/4/18 17:24
 * @description:
 */
@Data
@Accessors(chain = true)
public class Cartographic {
    public Cartographic() {
    }

    public Cartographic(double longitude, double latitude, double height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public double longitude;
    public double latitude;
    public double height;
}
