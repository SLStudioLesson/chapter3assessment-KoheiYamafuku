import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            // 選ばれた数字で出力するモードを判定
            if (choice.equals("2")) {
                JSONDataHandler jsonHandler = new JSONDataHandler();
                System.out.println("Current mode:" + jsonHandler.getMode());
            } else {// 数字が2以外であれば
                CSVDataHandler csvHandler = new CSVDataHandler();
                // メインメニューを表示
                RecipeUI ui = new RecipeUI(csvHandler);
                ui.displayMenu();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}