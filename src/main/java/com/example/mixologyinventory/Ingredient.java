package com.example.mixologyinventory;

public class Ingredient {
    private String name;
    private Double price;
    private Double quantityForPrice;

    public Ingredient(String ingredientLine){
        String[] ingredientSplit = ingredientLine.split(" ");
        this.name = ingredientSplit[0].trim();
        String[] ingredientPriceTag = ingredientSplit[1].split(":");
        this.quantityForPrice = Double.valueOf(ingredientPriceTag[0]);
        this.price = Double.valueOf(ingredientPriceTag[1]);

    }
    public String getName(){
        return this.name;
    }
    public Double getPrice(){
        return this.price;
    }
    public Double getQuantityForPrice(){
        return this.quantityForPrice;
    }

}
