package com.taxcalculator.constant;

import java.util.stream.Stream;

public enum SalesTaxType {

	BASIC_SALES_TAX(1, "Basic", "Basic Sales Tax", 10.00),

	IMPORT_DUTY_TAX(2, "ImportDuty", "Import Duty Sales Tax", 5.00);

	private Integer id;
	private String name;
	private String description;
	private Double salesTaxPercent;

	private SalesTaxType(Integer id, String name, String description, Double salesTaxPercent) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.salesTaxPercent = salesTaxPercent;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getSalesTaxPercent() {
		return salesTaxPercent;
	}

	public static Stream<SalesTaxType> stream() {
		return Stream.of(SalesTaxType.values());
	}

	public SalesTaxType getSalesTaxTypeById(Integer id) {

		SalesTaxType salesTaxTypeToFind = SalesTaxType.stream().filter(salesTaxType -> salesTaxType != null
				&& salesTaxType.getId() != null && id != null && id.intValue() == salesTaxType.getId().intValue())
				.findAny().get();

		return salesTaxTypeToFind;

	}

	public SalesTaxType getSalesTaxTypeByName(String name) {

		SalesTaxType salesTaxTypeToFind = SalesTaxType.stream().filter(salesTaxType -> salesTaxType != null
				&& salesTaxType.getName() != null && name != null && name.equals(salesTaxType.getName())).findAny()
				.get();

		return salesTaxTypeToFind;

	}
}