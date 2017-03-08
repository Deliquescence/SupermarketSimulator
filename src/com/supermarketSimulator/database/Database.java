package com.supermarketSimulator.database;

import com.supermarketSimulator.items.Category;
import com.supermarketSimulator.items.Item;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class Database {
    
    public static Item[] items;
	
	private final String fileString;
	
	/**
	 * Creates a database from a csv
	 *
	 * @param fileString The name of the csv file as a String
	 */
	public Database(String fileString) {
		this.fileString = fileString;
	}
	
	/**
	 * Reads in lines from a csv to populate the items in the game
	 */
	public void ReadItems() {
		try {
			String line;
			FileInputStream file = new FileInputStream(fileString);
			InputStreamReader reader = new InputStreamReader(file, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(reader);
			items = new Item[Integer.parseInt(br.readLine())];
			System.out.println("Length of the array is " + items.length);
			while ((line = br.readLine()) != null) {
				Item i = Item.itemFromString(line);
				items[i.getID()] = i;
			}
			Category.determineHealthiness();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
