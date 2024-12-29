package cz.cuni.mff.nagylad.pages;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.model.Game;
import cz.cuni.mff.nagylad.routing.AvailablePages;
import cz.cuni.mff.nagylad.routing.CloseApplicationException;
import cz.cuni.mff.nagylad.routing.Router;

import java.util.Scanner;

public class GamePage extends Page {
    // Green background for grass
    public final String grass = "\u001B[42m   \u001B[0m";

    // Gray background for roads
    public final String road = "\u001B[47m   \u001B[0m";

    // Brown background for buildings
    public final String building = "\u001B[43m   \u001B[0m";

    // Blue background for water
    public final String water = "\u001B[44m   \u001B[0m";

    // Black background for railways
    public final String railway = "\u001B[40m   \u001B[0m";

    // Purple background for train stations
    public final String trainStation = "\u001B[45m   \u001B[0m";

    // Cyan background for parks
    public final String park = "\u001B[46m   \u001B[0m";

    // Red background for emergency zones
    public final String emergencyZone = "\u001B[41m   \u001B[0m";

    // White background for public squares
    public final String publicSquare = "\u001B[47;1m   \u001B[0m";

    // Yellow background for shoppingMalls
    public final String shoppingMall = "\u001B[43;1m   \u001B[0m";

    public final String cityCode = "city";
    public GamePage (Router router, AppState appState) {
        super(router, appState);
    }
    @Override
    public void load() {
        System.out.println();
        System.out.println("#### GAME PAGE ####");
        System.out.printf("You currently have %s gems%n", appState.state.game.gems);
        System.out.println("Your city is looking great!");
        System.out.println();

        printMap();

        System.out.println();
        System.out.printf("Type '%s' and press enter to improve your city.%n", cityCode);
        System.out.printf("Type '%s' and press enter to see your sets.%n", Router.SETS_CODE);
        System.out.printf("Type '%s' and press enter to save the application state and leave the application.%n", Router.EXIT_CODE);
        System.out.printf("Type '%s' and press enter to return to main menu.%n", Router.MAIN_CODE);

        System.out.print("Type here: ");
        String input = new Scanner(System.in).nextLine();

        switch (input) {
            case Router.EXIT_CODE -> throw new CloseApplicationException("Application closed by exiting from the main page!");
            case Router.SETS_CODE -> router.changePage(AvailablePages.SETS_PAGE);
            case Router.MAIN_CODE -> router.changePage(AvailablePages.MAIN_PAGE);
            case cityCode -> {
                alterCity();
            }
        }
    }

    public void alterCity() {
        Game game = appState.state.game;

        System.out.println("#### ALTER CITY ####");
        System.out.println("Available terrains and their prices:");
        System.out.println("G: Grass (50 gems), R: Road (100 gems), B: Building (200 gems), W: Water (150 gems)");
        System.out.println("T: Railway (180 gems), S: Train Station (250 gems), P: Park (120 gems)");
        System.out.println("E: Emergency Zone (300 gems), Q: Public Square (220 gems), M: Shopping Mall (500 gems)");

        System.out.print("Put in an index on the map you want to change (1 for the first field and each row has 10 fields): ");
        int mapFieldIndex;
        try {
            mapFieldIndex = Integer.parseInt(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid index! Please input a valid number.");
            return;
        }

        mapFieldIndex--;
        if (mapFieldIndex < 0 || mapFieldIndex >= game.city.length) {
            System.out.println("Index out of bounds! Please input a valid index.");
            return;
        }

        System.out.println();
        System.out.print("See the options above and put in what type of field you want there: ");
        String newMapFieldContent = new Scanner(System.in).nextLine().toUpperCase();

        int cost;
        switch (newMapFieldContent) {
            case "G" -> cost = 50;
            case "R" -> cost = 100;
            case "B" -> cost = 200;
            case "W" -> cost = 150;
            case "T" -> cost = 180;
            case "S" -> cost = 250;
            case "P" -> cost = 120;
            case "E" -> cost = 300;
            case "Q" -> cost = 220;
            case "M" -> cost = 500;
            default -> {
                System.out.println("Invalid field type! No changes were made.");
                return;
            }
        }

        if (game.gems < cost) {
            System.out.printf("You need %d gems to alter this field, but you only have %d gems!%n", cost, game.gems);
            return;
        }

        game.city[mapFieldIndex] = newMapFieldContent;
        game.gems -= cost;

        System.out.println("City altered successfully!");
        System.out.printf("New field: %s (cost: %d gems)%n", newMapFieldContent, cost);
        System.out.printf("Gems remaining: %d%n", game.gems);
    }


    public void printMap() {
        int index = 0;
        for (String field : appState.state.game.city) {
            String toPrint = "  ";
            switch (field) {
                case null -> toPrint = grass;
                case "G" -> toPrint = grass;
                case "R" -> toPrint = road;
                case "B" -> toPrint = building;
                case "W" -> toPrint = water;
                case "T" -> toPrint = railway;
                case "S" -> toPrint = trainStation;
                case "P" -> toPrint = park;
                case "E" -> toPrint = emergencyZone;
                case "Q" -> toPrint = publicSquare;
                case "M" -> toPrint = shoppingMall;
                default -> throw new IllegalStateException("Unexpected value: " + field);
            }
            if ((index + 1) % 10 == 0) {
                System.out.println(toPrint);
            } else {
                System.out.print(toPrint);
            }
            index++;
        }
    }
}
