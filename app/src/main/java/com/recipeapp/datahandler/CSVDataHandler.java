package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {

    String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    // 現在のモードを返します。
    @Override
    public String getMode() {
        return "CSV";
    }

    // recipes.csvからレシピデータを読み込み、Recipeオブジェクトのリストとして返します。
    @Override
    public ArrayList<Recipe> readData() throws IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] array = line.split(",");
            String menuTitle = array[0];
            
            for(int i =){
                String ingredient += ans;

            }
            ArrayList<Ingredient> list = new ArrayList<>();
        }
    }

    // 指定されたRecipeオブジェクトを追加します。
    @Override
    public void writeData(Recipe recepe) throws IOException {

    }

    // 指定されたキーワードでレシピを検索し、一致するRecipeオブジェクトのリストを返します。
    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }

    public void displayRecipes(){
        Recipe recipe = new Recipe(, null)
        System.out.println("Recipes:");
        System.out.println("-----------------------------------");
        System.out.println("Recipe name:" + );
    }

}
