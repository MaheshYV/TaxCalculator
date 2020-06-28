package com.taxcalculator.test;

import java.util.List;
import java.util.stream.IntStream;

import com.taxcalculator.TaxCalculatorApplication;
import com.taxcalculator.receipt.Receipt;

public class TaxCalculatorApplicationTest {

	private static final String INPUT_TEST_CASES_SUB_FOLDER_PATH = "src/test/resources/inputtestcases";

	public static void main(String[] args) {
		TaxCalculatorApplicationTest taxCalculatorApplicationTest = new TaxCalculatorApplicationTest();
		taxCalculatorApplicationTest.testTaxCalculatorApplication(INPUT_TEST_CASES_SUB_FOLDER_PATH);
	}

	private void testTaxCalculatorApplication(String inputTestCasesSubFolderPath) {

		TaxCalculatorApplication taxCalculatorApplication = new TaxCalculatorApplication();
		List<Receipt> receiptList = taxCalculatorApplication.createReceiptList(INPUT_TEST_CASES_SUB_FOLDER_PATH);
		System.out.println("Receipts : \n");

		System.out.println("OUTPUT \n");

		IntStream.range(0, receiptList.size()).forEach(i -> {

			System.out.println("Output " + (i + 1) + ": ");
			System.out.println(receiptList.get(i).print());
			System.out.println();

		});

	}

}