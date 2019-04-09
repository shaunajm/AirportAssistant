package com.example.shaum.airportassistant.util;

public class ARUtil {

    public ARUtil() { }

    public double getCubeVolume(double d1, double d2, double d3) {
        return d1 * d2 * d3;
    }

    public double getCylinderVolume(double d1, double d2) {
        double radius = d1/2;
        double radiussquared = Math.pow(radius, 2);
        return (3.14159*radiussquared*d2);
    }

    public boolean passFailResult(double volume){
        if (volume <= 200) {
            return true;
        } else {
            return false;
        }
    }

    public boolean luggageResult(double userd1, double userd2, double userd3, double airlined1, double airlined2, double airlined3){

        if(userd1 <= airlined1 && userd2 <= airlined2 && userd3 <= airlined3) {
            return true;
        }
        else{
            return false;
        }
    }
}
