package com.supermarketSimulator.database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import com.supermarketSimulator.items.Item;

/**
 * Created by Justin Kur on 2/8/2017.
 */
public class Database {

    private final String fileString;

    /**
     * Creates a database from a csv
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
            while((line = br.readLine()) != null) {
                Item.itemFromString(line);
            }
        }
        catch(Exception e) {
            //TODO log this.
        }
    }
}
