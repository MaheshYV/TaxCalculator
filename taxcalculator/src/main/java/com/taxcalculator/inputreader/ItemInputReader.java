package com.taxcalculator.inputreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.taxcalculator.domain.Item;
import com.taxcalculator.domain.ItemTaxCalculator;

public class ItemInputReader {

	public List<Item> processInputFile(File file) {

		List<Item> inputList = new ArrayList<>();

		try (Reader fileReader = new FileReader(file); BufferedReader br = new BufferedReader(fileReader);) {

			// skip the header of the input file
			inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());

			br.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return inputList;

	}

	public List<Item> processInputFile(String inputFilePath) {

		List<Item> inputList = new ArrayList<>();
		File file = new File(inputFilePath);

		try (Reader fileReader = new FileReader(file); BufferedReader br = new BufferedReader(fileReader);) {

			// skip the header of the input file
			inputList = br.lines().skip(1).map(mapToItem).map(ItemTaxCalculator::applyTaxes)
					.collect(Collectors.toList());

			br.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return inputList;

	}

	// create a custom functional interface of type Function<String, Long id
	// List<String>, Item>
	private Function<String, Item> mapToItem = (line) -> {

		String[] p = line.split(" ");

		Double quantity = Double.parseDouble(p[0]);

		BigDecimal price = new BigDecimal(p[p.length - 1]);

		StringJoiner strJoiner = new StringJoiner(" ");

		for (int i = 1; i < p.length - 2; i++) {
			strJoiner.add(p[i]);
		}

		String name = strJoiner.toString();

		Item item = new Item();

		item.setName(name);
		item.setDescription(name);
		item.setPrice(price.multiply(new BigDecimal(quantity)));
		item.setQuantity(quantity);

		if (name != null && name.contains("imported")) {
			item.setImported(Boolean.TRUE);
		}

		return item;

	};

}
