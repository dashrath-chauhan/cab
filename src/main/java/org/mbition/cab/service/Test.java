package org.mbition.cab.service;

public class Test {

	public static double distance(double lat1, double lat2, double lon1, double lon2) {

		// The math module contains a function named toRadians which converts from degrees to radians.
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// Haversine formula
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956 for miles
		double r = 6371;

		// calculate the result
		return (c * r);
	}

	
	public static void main(String[] args) {
		System.out.println(distance(22.698637, 22.698637, 73.115573, 73.115573) + " K.M");
		System.out.println(distance(22.698637, 22.712172, 73.115573, 73.111302) + " K.M");
		System.out.println(distance(22.698637, 22.708617, 73.115573, 73.115640) + " K.M");
		System.out.println(distance(22.698637, 22.707014, 73.115573, 73.107799) + " K.M");
		System.out.println(distance(22.698637, 22.701072, 73.115573, 73.111302) + " K.M");
		System.out.println(distance(22.698637, 22.698538, 73.115573, 73.224303) + " K.M");
		System.out.println(distance(22.698637, 22.703652, 73.115573, 73.115334) + " K.M");
		System.out.println(distance(22.698637, 22.695707, 73.115573, 73.119411) + " K.M");
		System.out.println(distance(22.698637, 22.726060, 73.115573, 73.119992) + " K.M");
		System.out.println(distance(22.698637, 22.704523, 73.115573, 73.116061) + " K.M");
	}
}
