package com.vgaw.test;

/**
 * Created by Administrator on 2015/8/21.
 */
public class PlanetEnum {
    //when the constant have the value you defined,you should create a constructor with all of its values.
    public enum Planet{
        //Java requires that the constants be defined first, prior to any fields or methods.
        //that is to say,when you put the constants defined below behind the constructor, it calls error.
        MERCURY (3.303e+23, 2.4397e6),
        VENUS   (4.869e+24, 6.0518e6),
        EARTH   (5.976e+24, 6.37814e6),
        MARS    (6.421e+23, 3.3972e6),
        JUPITER (1.9e+27,   7.1492e7),
        SATURN  (5.688e+26, 6.0268e7),
        URANUS  (8.686e+25, 2.5559e7),
        NEPTUNE (1.024e+26, 2.4746e7);

        private final double mass;  //in kilograms
        private final double radius;//in meters

        //The constructor for an enum type must be package-private or private
        Planet(double mass, double radius) {
            this.mass = mass;
            this.radius = radius;
        }

        //universal gravitational constant (m3 kg-1 s-2)
        public static final double G = 6.67300E-11;

        public double surfaceGravity() {
            return G * mass / (radius * radius);
        }

        public double surfaceWeight(double otherMass) {
            return otherMass * surfaceGravity();
        }
    }
}
