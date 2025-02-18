package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {

        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            System.out.println("Recipes:");
            for (Recipe recipe : recipes) {
                System.out.println("-----------------------------------");
                System.out.println("Recipe name:" + recipe.getName());
                System.out.print("Main Ingredients:");

                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                    System.out.print(recipe.getIngredients().get(i).getName());
                    // もし最後の周回じゃない場合,","を付け加える
                    if (i != recipe.getIngredients().size() - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage() + "例外のメッセージ");
            ;
        }
    }

    private void addNewRecipe() {
        try {
            System.out.print("Adding a new recipe.\r\n" + //
                    "Enter recipe name:");
            String recipeName = reader.readLine();
            
            ArrayList<Ingredient> ingredients = new ArrayList<>();
         
            System.out.println("Enter ingredients (type 'done' when finished):");

            String select = "";
            do {
                System.out.print("Ingredient:");
                select = reader.readLine();
                //もしdoneが記述されたら、材料として追記しないようにする
                if(!select.equals("done")){
                    Ingredient ingredient = new Ingredient(select);
                    ingredients.add(ingredient);
                }
            } while (!select.equals("done"));
            System.out.println("Recipe added successfully.");
            Recipe recipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(recipe);
        } catch (Exception e) {
            System.out.println("Failed to add new recipe: 例外のメッセージ");
        }
    }
}
