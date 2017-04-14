package com.tambo.btc.trading;

public class CalculationHelper {

	private CalculationHelper() {
		// static helper class
	}

	public static double bpsAway(double priceBase, double priceCompare) {
		return Math.abs(priceBase - priceCompare) / priceBase * 10000;
	}

}
