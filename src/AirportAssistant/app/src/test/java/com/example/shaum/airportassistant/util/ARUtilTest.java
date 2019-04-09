package com.example.shaum.airportassistant.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ARUtilTest {

    ARUtil arHelper = new ARUtil();

    @Test
    public void getCubeVolume() {
        assertEquals(1.0, arHelper.getCubeVolume(1.0, 1.0, 1.0), 0.01);
        assertEquals(24.0, arHelper.getCubeVolume(2.0, 3.0, 4.0), 0.01);
        assertNotEquals(24.0, arHelper.getCubeVolume(-2.0, 3.0, 4.0));
    }
    @Test
    public void getCylinderVolume(){
        assertEquals(12.57, arHelper.getCylinderVolume(4.0, 1.0), 0.5);
        assertEquals(307.88, arHelper.getCylinderVolume(14.0, 2.0), 0.5);
        assertNotEquals(21.5, arHelper.getCylinderVolume(2.0, 9.3));
    }


    @Test
    public void passFailResult() {
        assertTrue(arHelper.passFailResult(100));
        assertFalse(arHelper.passFailResult(250));
    }

    @Test
    public void luggageResult(){
        assertTrue(arHelper.luggageResult(30, 20, 10, 40, 30, 20));
        assertFalse(arHelper.luggageResult(55, 40, 30, 20, 10,5));
    }
}