/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mixologyinventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author charl
 */
public class Drink {
    public HashMap<String, Double> ingredients;
    private String filePath;
    private String name;
    
    public Drink(File drinkFile) throws FileNotFoundException{
        this.ingredients = new HashMap<String, Double>();
        filePath = drinkFile.getAbsolutePath();
        name = drinkFile.getName().replace(".txt", "");
        Scanner input = new Scanner(drinkFile);
        while(input.hasNextLine())
        {
            String ingredientString = input.nextLine();
            if (!ingredientString.isEmpty()){
            String[] ingredientLine = ingredientString.split(":");
            String ingredientName = ingredientLine[0].trim();
            Double ingredientAmount = Double.valueOf(ingredientLine[1].trim());
            ingredients.put(ingredientName, ingredientAmount);
            }
        }
    }
    
}
