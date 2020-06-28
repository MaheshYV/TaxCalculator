package com.taxcalculator.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

	public static double roundOffTax(double number) {
		return Math.ceil(number * 20) / 20;
	}

	public static double roundOffAmount(double number) {
		return Math.round(number * 100.0) / 100.0;
	}
}
