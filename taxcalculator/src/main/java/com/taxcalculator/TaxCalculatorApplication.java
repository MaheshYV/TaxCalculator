package com.taxcalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.taxcalculator.domain.Item;
import com.taxcalculator.inputreader.ItemInputReader;
import com.taxcalculator.receipt.Receipt;
import com.taxcalculator.receipt.processor.ReceiptProcessor;

public class TaxCalculatorApplication {

	private static final String INPUT_TEST_CASES_SUB_FOLDER_PATH = "src/test/resources/inputtestcases";
//
//	public static void main(String[] args) {
//		TaxCalculatorApplication taxCalculatorApplication = new TaxCalculatorApplication();
//		List<Receipt> receiptList = taxCalculatorApplication.createReceiptList(INPUT_TEST_CASES_SUB_FOLDER_PATH);
//		System.out.println("OUTPUT \n");
//
//		IntStream.range(0, receiptList.size()).forEach(i -> {
//
//			System.out.println("Output " + (i + 1) + ": ");
//			System.out.println(receiptList.get(i).print());
//			System.out.println();
//
//		});
//	}

	public List<Receipt> createReceiptList(String inputTestCasesSubFolderPath) {
		List<String> inputFileNameList = getInputFileNameList(inputTestCasesSubFolderPath);

		String projectFolderPath = Paths.get("").toAbsolutePath().toString();

		List<Receipt> receiptList = inputFileNameList.stream().map(inputFileName -> {
			List<Item> itemList = readItemsFromInputFile(
					projectFolderPath + "/" + inputTestCasesSubFolderPath + "/" + inputFileName);

			ReceiptProcessor receiptProcessor = new ReceiptProcessor();

			Receipt receipt = receiptProcessor.createReceipt(itemList);

			return receipt;
		}).collect(Collectors.toList());

		return receiptList;

	} //

	public List<String> getInputFileNameList(String inputTestCasesSubFolderPath) {

		List<String> inputFileNameList = new ArrayList<>();

		try {
			inputFileNameList = Files.list(Paths.get(inputTestCasesSubFolderPath)).filter(Files::isRegularFile)
					.map(f -> f.getFileName().toString()).filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
		} catch (IOException ex) {
			throw new Error(ex);
		}

		return inputFileNameList;
	}

	public List<Item> readItemsFromInputFile(String file) {

		ItemInputReader itemInputReader = new ItemInputReader();

		List<Item> itemList = itemInputReader.processInputFile(file);

		return itemList;
	}
}
