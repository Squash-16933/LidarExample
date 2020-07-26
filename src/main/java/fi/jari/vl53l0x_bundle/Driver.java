package fi.jari.vl53l0x_bundle;

/**************************************************************************************************************
 * VL53L0X Lidar Demo
 * version version 0.0.6
 * 200725 Initial test for VL53L0X
 * Copyright 2020 Team Squash
 **************************************************************************************************************/
public class Driver {
	public static void main(String[] args) {
		VL53L0XData vl53 = new VL53L0XData();
		Thread sensorThread = new Thread(vl53);
		sensorThread.start();
	}
}
