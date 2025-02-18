package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
        // 返す値であるRecipe型のArrayListを生成
        ArrayList<Recipe> recipes = new ArrayList<>();

        // ファイルを読み込むテンプレを用意（普段はtry-catchを使用するが、throwsがついているので付けなくてok）
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line;

        // ファイル内が空出会った時はエラーメッセージを表示
        if (reader.readLine() == null) {
            System.out.println("No recipes available.");

        }
        while ((line = reader.readLine()) != null) {
            // ここから、ファイル内の","を取り除き配列に格納する
            String[] array = line.split(",");
            String menuTitle = array[0];// 要素番号0は必ずタイトルになるはずなので0をタイトルとして分けておく

            // 材料を表すIngredient型のリストを生成する
            ArrayList<Ingredient> list = new ArrayList<>();

            for (int i = 1; i <= array.length - 1; i++) {
                Ingredient ingredientList = new Ingredient(array[i]);// String型だった材料をIngredient型に変換（のような処理）

                list.add(ingredientList);// リストに追加
            }
            Recipe recipe = new Recipe(menuTitle, list);// レシピ型のインスタンスとして情報が完成する

            recipes.add(recipe);// ループするたびにRecipeリストに突っ込んでいく
        }
        return recipes;
    }

    // 指定されたRecipeオブジェクトを追加します。
    @Override
    public void writeData(Recipe recepe) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(recepe.getName() + ",");
            for (int i = 0; i < recepe.getIngredients().size(); i++) {
                writer.write(recepe.getIngredients().get(i).getName());

                //最後の行でなければ","をつける
                if(i != recepe.getIngredients().size() - 1){
                   writer.write(",");
                }
            }
            writer.newLine();
        }
    }

    // 指定されたキーワードでレシピを検索し、一致するRecipeオブジェクトのリストを返します。
    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }

}
