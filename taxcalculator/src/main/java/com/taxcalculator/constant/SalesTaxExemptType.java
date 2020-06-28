package com.taxcalculator.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum SalesTaxExemptType {

	BOOK(100, "Book", "Book"), FOOD(101, "Food", "Food"), MEDICAL(102, "Medical", "Medical");

	private Integer id;
	private String name;
	private String description;

	private SalesTaxExemptType(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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

	public static Stream<SalesTaxExemptType> stream() {
		return Stream.of(SalesTaxExemptType.values());
	}

	public static List<String> getAllTaxEmemptItemList() {

		List<String> taxEmemptItemList = new ArrayList<>();
		SalesTaxExemptType.stream().forEach(
				salesTaxExemptType -> taxEmemptItemList.addAll(getTaxEmemptItemListByType(salesTaxExemptType)));

		return taxEmemptItemList;

	}

	public static List<String> getTaxEmemptItemListByType(SalesTaxExemptType salesTaxExemptType) {

		List<String> taxEmemptItemList = new ArrayList<>();

		switch (salesTaxExemptType) {
		case BOOK:
			taxEmemptItemList = Arrays.asList(new String[] { "Book" });
			break;
		case FOOD:
			taxEmemptItemList = Arrays.asList(new String[] { "chocolates", "chocolate bar" });
			break;
		case MEDICAL:
			taxEmemptItemList = Arrays.asList(new String[] { "headache pills", "pills" });
			break;
		default:
			break;
		}

		return taxEmemptItemList;

	}

	public SalesTaxExemptType getSalesTaxExemptTypeById(Integer id) {

		SalesTaxExemptType salesTaxExemptTypeToFind = SalesTaxExemptType.stream()
				.filter(salesTaxType -> salesTaxType != null && salesTaxType.getId() != null && id != null
						&& id.intValue() == salesTaxType.getId().intValue())
				.findAny().get();

		return salesTaxExemptTypeToFind;

	}

	public SalesTaxExemptType getSalesTaxExemptTypeByName(String name) {

		SalesTaxExemptType salesTaxExemptTypeToFind = SalesTaxExemptType.stream()
				.filter(salesTaxType -> salesTaxType != null && salesTaxType.getName() != null && name != null
						&& name.equals(salesTaxType.getName()))
				.findAny().get();

		return salesTaxExemptTypeToFind;

	}

}