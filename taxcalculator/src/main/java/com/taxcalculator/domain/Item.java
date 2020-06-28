package com.taxcalculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.taxcalculator.util.MathUtil;

public class Item {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Double quantity;

	private Boolean exemptFromSalesTax = Boolean.FALSE;
	private Boolean imported = Boolean.FALSE;;

	private BigDecimal salesTax = new BigDecimal(0.00);;

	private BigDecimal importDutyTax = new BigDecimal(0.00);

	private BigDecimal totalPrice;

	private BigDecimal taxAmount = new BigDecimal(0.00); // salesTax + importDutyTax

	public Item() {

	}

	public Item(String name, Double quantity, BigDecimal price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public BigDecimal getImportDutyTax() {
		return importDutyTax;
	}

	public void setImportDutyTax(BigDecimal importDutyTax) {
		this.importDutyTax = importDutyTax;
	}

	public Boolean isExemptFromSalesTax() {
		return exemptFromSalesTax;
	}

	public void setExemptFromSalesTax(Boolean exemptFromSalesTax) {
		this.exemptFromSalesTax = exemptFromSalesTax;
	}

	public Boolean isImported() {
		return imported;
	}

	public void setImported(Boolean imported) {
		this.imported = imported;
	}

	public BigDecimal getTotalPrice() {

		if (getPrice() != null) {
			totalPrice = getPrice().add(getSalesTax()).add(getImportDutyTax());

			setTotalPrice(new BigDecimal(MathUtil.roundOffAmount(totalPrice.doubleValue())).setScale(2,
					RoundingMode.HALF_UP));

		}
		
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxAmount() {

		return new BigDecimal(quantity * (taxAmount.add(importDutyTax).doubleValue()));
	}

	@Override
	public String toString() {
		return String.valueOf(quantity.intValue()) + " " + name.trim() + ": " + getTotalPrice();
	}

}