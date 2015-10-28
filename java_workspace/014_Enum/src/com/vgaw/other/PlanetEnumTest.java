package com.vgaw.other;

import com.vgaw.test.PlanetEnum.Planet;

/**
 * Created by Administrator on 2015/8/21.
 */
public class PlanetEnumTest {
    public static void main(String[] args){
        double earthWeight = 175.0;
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()) {
            System.out.printf("Your weight on %s is %f%n", p, p.surfaceWeight(mass));
        }
    }
}
