package com.taxcalculator.receipt.processor;

import java.util.List;

import com.taxcalculator.domain.Item;
import com.taxcalculator.receipt.Receipt;

public class ReceiptProcessor {

	public Receipt createReceipt(List<Item> itemList) {
		Receipt receipt = new Receipt();
		receipt.createReceipt(itemList);

		return receipt;
	}
}