package com.taxcalculator.receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.taxcalculator.domain.Item;
import com.taxcalculator.util.MathUtil;

public class Receipt {

	private BigDecimal totalSalesTax;
	private BigDecimal totalAmount;
	private String itemDetails;

	public Receipt() {

	}

	public String createReceipt(List<Item> itemList) {

		StringBuilder itemDetailsBuilder = new StringBuilder();

		for (Item item : itemList) {

			itemDetailsBuilder.append(item.toString());
			itemDetailsBuilder.append("\n");
		}

		setItemDetails(itemDetailsBuilder.toString());

		BigDecimal totalSalesTaxOfItemList = itemList.stream()
				.map(item -> item.getSalesTax().add(item.getImportDutyTax())).reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal totalPriceOfItemList = itemList.stream().map(item -> item.getPrice()).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		totalPriceOfItemList = totalPriceOfItemList.add(totalSalesTaxOfItemList);
		totalPriceOfItemList = new BigDecimal(MathUtil.roundOffAmount(totalPriceOfItemList.doubleValue())).setScale(2,
				RoundingMode.HALF_UP);

		totalSalesTaxOfItemList = new BigDecimal(MathUtil.roundOffAmount(totalSalesTaxOfItemList.doubleValue()))
				.setScale(2, RoundingMode.HALF_UP);

		setItemDetails(itemDetails);

		setTotalAmount(totalPriceOfItemList);
		setTotalSalesTax(totalSalesTaxOfItemList);

		return this.toString();
	}

	public String getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(String itemDetails) {
		this.itemDetails = itemDetails;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalSalesTax() {
		return totalSalesTax;
	}

	public void setTotalSalesTax(BigDecimal totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}

	public String print() {
		return this.toString();
	}

	@Override
	public String toString() {
		return itemDetails + "Sales Taxes: " + totalSalesTax + "\n" + "Total: " + totalAmount;
	}
}