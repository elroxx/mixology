/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mixologyinventory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author charl
 */
public class FXMLControllerMix implements Initializable{
    
    static class StorageClass{TextField[] drinkTFArray; Ingredient[] ingredientsTypeArray; Drink[] drinksArray; CheckBox[] cbArray;}
    final StorageClass storage = new StorageClass();
    @FXML public GridPane mainGrid;
    @Override
        public void initialize(URL url, ResourceBundle rb){
    
    }
    public void load() throws ClassNotFoundException, URISyntaxException{
        String jarpath = MixologyInventory.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        jarpath = jarpath.replace("MixologyInventory.jar", "");
        //String jarpath = "C:\\Users\\charl\\Downloads\\TestMixology\\";
        System.out.println(jarpath);
        
        try {
            Ingredient[] ingTypeArray = App.ingredientsListFromConfig(jarpath+"config.txt");
            Drink[] drinkArray = getAllDrinks(jarpath+"Drinks");
            storage.ingredientsTypeArray = ingTypeArray;
            storage.drinksArray = drinkArray;
            
            
            
            
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FXMLControllerMix.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
        public Drink[] getAllDrinks(String directoryPath) throws FileNotFoundException{
        File directory = new File(directoryPath);
        String[] drinkNames = directory.list();
        int gridrows = (drinkNames.length/2)+1;
        mainGrid.setAlignment(Pos.CENTER_RIGHT);
        Text[] drinkLabel = new Text[drinkNames.length];
        TextField[] drinkTf = new TextField[drinkNames.length];
        HBox[] drinkBox = new HBox[drinkNames.length];
        Drink[] drinkList = new Drink[drinkNames.length];
        for (int i = 0; i<drinkNames.length;i++){
            File currentDrink = new File(directoryPath+"\\"+drinkNames[i]);
            drinkList[i] = new Drink(currentDrink);
            drinkLabel[i] = new Text(drinkNames[i].replace(".txt", " "));
            drinkTf[i] = new TextField();
            drinkBox[i] = new HBox(drinkLabel[i], drinkTf[i]);
            drinkBox[i].setAlignment(Pos.CENTER_RIGHT);
        }
        storage.drinkTFArray = drinkTf;
        int overallCounter = 0;
        for (int i =0; i<2;i++){
            for (int j = 0; j<gridrows && overallCounter<drinkBox.length; j++, overallCounter++){
                mainGrid.add(drinkBox[overallCounter], i, j);
            }
        }

        return drinkList;
    }
    

    
    public void submit(){
        double[] quantity = new double[storage.ingredientsTypeArray.length];

        //CHECKBOXES FOR CASHMONEY
        CheckBox[] checkboxArrayTemp = new CheckBox[storage.ingredientsTypeArray.length];
        for (int i=0; i<checkboxArrayTemp.length;i++){
            checkboxArrayTemp[i]=new CheckBox();
            checkboxArrayTemp[i].setSelected(true);
        }
        storage.cbArray=checkboxArrayTemp;
        //RESET QUANTITY ARRAY
        for (int i = 0; i<quantity.length;i++){
            quantity[i]=0;
        }
        for (int i = 0; i<storage.drinksArray.length; i++){
            int multiplier;
            //IF INGREDIENT IS NOT NAMED
            if (storage.drinkTFArray[i].getText().isEmpty() || storage.drinkTFArray[i].getText()==null || storage.drinkTFArray[i].getText()==""){
                multiplier = 0;
            }
            else{
                multiplier = Integer.valueOf(storage.drinkTFArray[i].getText());
            }
            //GETS THE QUANTITY OF THE INGREDIENT
            for (int j = 0; j<storage.ingredientsTypeArray.length; j++){
                if (storage.drinksArray[i].ingredients.containsKey(storage.ingredientsTypeArray[j].getName())){
                    quantity[j]=quantity[j]+storage.drinksArray[i].ingredients.get(storage.ingredientsTypeArray[j].getName())*multiplier;
                }
            }
        }
        VBox resultBox = new VBox();
        resultBox.setSpacing(5);
        Text[] resultText = new Text[storage.ingredientsTypeArray.length];
        for (int i = 0; i<resultText.length;i++){
            resultText[i] = new Text(storage.ingredientsTypeArray[i].getName()+" : "+quantity[i]);
            if (quantity[i]!=0){
                HBox lineBox = new HBox();
                lineBox.getChildren().add(resultText[i]);
                lineBox.getChildren().add(storage.cbArray[i]);
                lineBox.setSpacing(10);
                lineBox.setAlignment(Pos.CENTER_RIGHT);
                resultBox.getChildren().add(lineBox);
            }
        }

        Text totalLabel = new Text();
        String totalStringDefault = "Prix Total: ";
        totalLabel.setText(totalStringDefault);
        //TOTAL BUTTON
        Button totalButton = new Button("Total");
        totalButton.setDefaultButton(true);
        totalButton.setOnAction(e ->{
            //WHEN CLICKING THE TOTAL BUTTON
            double totalPrice = 0;
            for (int i=0;i<storage.ingredientsTypeArray.length; i++)
            {
                if (quantity[i]!=0 && storage.cbArray[i].isSelected()){
                    double priceForIngredient=0;
                    double numberOfBottles= Math.ceil(quantity[i]/storage.ingredientsTypeArray[i].getQuantityForPrice());
                    priceForIngredient= numberOfBottles*storage.ingredientsTypeArray[i].getPrice();
                    totalPrice = totalPrice+priceForIngredient;
                }
            }
            totalLabel.setText(totalStringDefault+totalPrice+"$");
        });
        HBox totalLine = new HBox();
        totalLine.setSpacing(10);
        totalLine.getChildren().add(totalButton);
        totalLine.getChildren().add(totalLabel);
        resultBox.getChildren().add(totalLine);



        HBox resultPane = new HBox(resultBox);
        resultPane.setAlignment(Pos.CENTER);
        Scene resultScene = new Scene(resultPane, 400, 600);
        Stage resultStage = new Stage();
        resultStage.setScene(resultScene);
        resultStage.show();

        
    }


}


