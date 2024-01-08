/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.mixologyinventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;


public class MixologyInventory {
    static private final String PATH = "C:\\Users\\charl\\OneDrive\\Documents\\NetBeansProjects\\MixologyInventory\\src\\mixologyinventory\\Drinks\\Vodka au miel.txt";
    static Drink[] drinkArray = new Drink[64];
    public static void main(String[] args) throws URISyntaxException {
        /*try {
            //String jarpath = MixologyInventory.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            String jarpath = "C:\\Users\\charl\\Downloads\\TestMixology";
            System.out.println(jarpath);
            //File testDrink = new File(PATH);
            //Drink vodkatest = new Drink(testDrink);
            //System.out.println(vodkatest.ingredients.get("miel"));
            //String[] ingTypeArray = ingredientsListFromConfig(jarpath+"\\config.txt");
            //Drink[] drinkArray = getAllDrinks(jarpath+"\\Drinks");
            for(int i = 0; i<ingTypeArray.length;i++){
                System.out.println(ingTypeArray[i]);
            }
            for (int i = 0; i<drinkArray.length;i++){
                System.out.println(drinkArray[i].ingredients);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }*/
        
    }
    
    public static String[] ingredientsListFromConfig(String configFilePath) throws FileNotFoundException{
        ArrayList<String> ingredientsTypeList = new ArrayList<String>();
        File configFile = new File(configFilePath);
        Scanner configInput = new Scanner(configFile);
        while(configInput.hasNextLine()){
            String ingredientTypeLine = configInput.nextLine();
            if (!ingredientTypeLine.isEmpty()){
                ingredientsTypeList.add(ingredientTypeLine.trim());
            }
        }
        String[] ingredientsTypeArray = new String[ingredientsTypeList.size()];
        for (int i = 0; i<ingredientsTypeArray.length; i++){
            ingredientsTypeArray[i]=ingredientsTypeList.get(i);
        }
        return ingredientsTypeArray;
    }
    
    /*public static Drink[] getAllDrinks(String directoryPath) throws FileNotFoundException{
        File directory = new File(directoryPath);
        String[] drinkNames = directory.list();
        Drink[] drinkList = new Drink[drinkNames.length];
        for (int i = 0; i<drinkNames.length;i++){
            File currentDrink = new File(directoryPath+"\\"+drinkNames[i]);
            drinkList[i] = new Drink(currentDrink);
        }
        return drinkList;
    }
    
    public static int[] getTotalIngredient(String[] ingredientsType){
        int[] quantity = new int[ingredientsType.length];
        return quantity;
    }*/
    
}
