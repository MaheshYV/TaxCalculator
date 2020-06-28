package com.taxcalculator.domain;

import java.math.BigDecimal;
import java.util.List;

import com.taxcalculator.constant.SalesTaxExemptType;
import com.taxcalculator.constant.SalesTaxType;
import com.taxcalculator.util.MathUtil;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemTaxCalculator {

	public static Item applyTaxes(Item item) {

		List<String> salesTaxExemptItemList = SalesTaxExemptType.getAllTaxEmemptItemList();

		String name = item.getName();

		if (name != null && salesTaxExemptItemList != null && salesTaxExemptItemList.stream()
				.anyMatch(s -> name != null && s != null && name.equalsIgnoreCase(s) || name.contains(s))) {
			item.setExemptFromSalesTax(Boolean.TRUE);
		}

		if (item != null && (item.isExemptFromSalesTax() == null
				|| (item.isExemptFromSalesTax() != null && !item.isExemptFromSalesTax()))) {
			Double salesTax = calculateSalesTax(item, SalesTaxType.BASIC_SALES_TAX);
			item.setSalesTax(new BigDecimal(salesTax));
		}

		if (item != null && (item.isImported() == null || (item.isImported() != null && item.isImported()))) {
			Double importDutyTax = calculateSalesTax(item, SalesTaxType.IMPORT_DUTY_TAX);
			item.setImportDutyTax(new BigDecimal(MathUtil.roundOffTax(importDutyTax)));
		}

		return item;
	}

	private static Double calculateSalesTax(Item item, SalesTaxType salesTaxType) {

		Double salesTaxPercent = salesTaxType.getSalesTaxPercent();

		Double salesTax = (item.getQuantity() * item.getPrice().doubleValue()) * (salesTaxPercent / 100);

		return salesTax;
	}
}
