package com.taxcalculator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import com.taxcalculator.receipt.Receipt;

/**
 * Test class for {@link TaxCalculatorApplication}
 *
 */
public class TaxCalculatorApplicationTest {
	private static final String INPUT_TEST_CASES_SUB_FOLDER_PATH = "src/test/resources/inputtestcases";

	private TaxCalculatorApplication taxCalculatorApplication = new TaxCalculatorApplication();

	@Test
	public void testTaxCalculatorApplication() {

		List<Receipt> receiptList = taxCalculatorApplication.createReceiptList(INPUT_TEST_CASES_SUB_FOLDER_PATH);

		System.out.println("Receipts : \n");

		System.out.println("OUTPUT\n");

		IntStream.range(0, receiptList.size()).forEach(i -> {

			System.out.println("Output " + (i + 1) + ": ");
			System.out.println(receiptList.get(i).print());
			System.out.println();

		});

		assertEquals(new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP), receiptList.get(0).getTotalSalesTax());
		assertEquals(new BigDecimal(29.83).setScale(2, RoundingMode.HALF_UP), receiptList.get(0).getTotalAmount());

		assertEquals(new BigDecimal(7.65).setScale(2, RoundingMode.HALF_UP), receiptList.get(1).getTotalSalesTax());
		assertEquals(new BigDecimal(65.15).setScale(2, RoundingMode.HALF_UP), receiptList.get(1).getTotalAmount());

		assertEquals(new BigDecimal(6.70).setScale(2, RoundingMode.HALF_UP), receiptList.get(2).getTotalSalesTax());
		assertEquals(new BigDecimal(74.68).setScale(2, RoundingMode.HALF_UP), receiptList.get(2).getTotalAmount());

	}
}
