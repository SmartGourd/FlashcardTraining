package cz.cuni.mff.nagylad;

import cz.cuni.mff.nagylad.routing.App;

/**
 * Creator: Ladislav Nagy

 * Programmer Documentation for the Flashcard Training

 **** Application Overview
 * The Flashcard Training is a console-based educational tool designed to help users practice flashcards while earning rewards in the form of gems.
 * These gems can be used to develop a virtual city on a 10x10 grid.
 * The application saves and loads its state via a JSON file (appData.json) and organizes its functionality into distinct pages, each handling a specific feature.

 **** Application Architecture
 * The application has one globally available state which is saved upon exiting and loaded on start
 * The AppState takes care of saving and loading and storing the application state
 * The App class takes care of the main loop that always shows a page to the user
 * The Router class can change what page is shown, the Page classes only get reference to router so they can't change the app and mess something up
 * Application can be exited from anywhere by throwing the CloseApplicationException

 ** Routing package
 * App Class: The main entry point of the application. Manages the state (AppState), navigation (Router), and the main execution loop.
 * Also loads the application state from the appData.json file
 * Router: Handles navigation between pages by setting the page property of app to the desired destination.
 * This should make adding new pages easy just add them to the enum, router and inherit from Page
 * Available pages: Enum of available pages
 * CloseApplicationException: Exception that can be thrown anywhere in the pages to save the state and exit the application

 ** Pages package
 * All pages inherit from an abstract Page class
 * Each page represents a specific feature and is responsible for user interaction in that context.
 * See the user documentation for what each pages provides

 ** Model package
 * AppState: Maintains the global application state, including sets, game progress, and user data.
 * Enables loading the state and saving it to appData.json file
 * Other classes: Data classes that match the data structure, they have no methods only constructors to set up defaults
 * Usually the model state will be loaded from the appData.json file, if not a blank state is initialized

 ** Algorithms package
 * Contains the algorithms that provide term-definition pairs based on user answers, there are two algorithms implemented
 * Both algorithms inherit from an AlgorithmBase this should make adding new algorithms easy

 * Box System:
 * Pairs start with a box number of 1 (minimum).
 * Correct answers increase the box number, reducing pair frequency.
 * Incorrect answers decrease the box number, increasing pair frequency.

 * Weight System:
 * Each pair has a weight.
 * Correct answers reduce the weight by half, lowering selection probability.
 * Incorrect answers increase the weight, raising selection probability.

 * This architecture ensures modularity, enabling easy extension of functionality by adding new Page classes or algorithms.
 * Data persistence through appData.json provides a seamless user experience between sessions.
 */
public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}