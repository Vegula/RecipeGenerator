import javax.swing.*;
import java.io.*;

class Recipe {
    String name;
    String[] ingredients; // Changed to array
    String[] instructions;

    public Recipe(String name, String[] ingredients, String[] instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}

public class RecipeManager {
    public static void main(String[] args) {
        // Step 1: Input UI
        String name = JOptionPane.showInputDialog(null, "Enter Recipe Name:", "New Recipe", JOptionPane.QUESTION_MESSAGE);
        if (name == null || name.isEmpty()) return;

        // Input Ingredients as comma-separated
        String rawIngredients = JOptionPane.showInputDialog(null, "Enter Ingredients (separate with commas):", "Ingredients", JOptionPane.QUESTION_MESSAGE);
        String[] ingredientArray = rawIngredients.split(",");
        
        // Input Instructions as comma-separated
        String rawInstructions = JOptionPane.showInputDialog(null, "Enter Steps (separate with commas):", "Instructions", JOptionPane.QUESTION_MESSAGE);
        String[] instructionArray = rawInstructions.split(",");

        // Step 2: Create Object
        Recipe currentRecipe = new Recipe(name, ingredientArray, instructionArray);

        // Step 3: Persistence
        saveRecipe(currentRecipe);

        // Step 4: Build the Numbered Lists for Display
        StringBuilder finalOutput = new StringBuilder();
        finalOutput.append("--- ").append(currentRecipe.name.toUpperCase()).append(" ---\n\n");

        finalOutput.append("INGREDIENTS:\n");
        for (int i = 0; i < currentRecipe.ingredients.length; i++) {
            finalOutput.append((i + 1)).append(". ").append(currentRecipe.ingredients[i].trim()).append("\n");
        }

        finalOutput.append("\nDIRECTIONS:\n");
        for (int i = 0; i < currentRecipe.instructions.length; i++) {
            finalOutput.append((i + 1)).append(". ").append(currentRecipe.instructions[i].trim()).append("\n");
        }

        finalOutput.append("\nResult: Saved to recipe_database.txt");

        // Step 5: Final GUI Output
        JOptionPane.showMessageDialog(null, finalOutput.toString(), "Final Recipe Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void saveRecipe(Recipe r) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("recipe_database.txt", true))) {
            writer.println("RECIPE: " + r.name);
            
            writer.println("INGREDIENTS:");
            for (int i = 0; i < r.ingredients.length; i++) {
                writer.println((i + 1) + ". " + r.ingredients[i].trim());
            }

            writer.println("DIRECTIONS:");
            for (int i = 0; i < r.instructions.length; i++) {
                writer.println((i + 1) + ". " + r.instructions[i].trim());
            }
            writer.println("--------------------------------\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to file.", "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}