package com.kodilla;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;

public class ShipBattle extends Application {

    private Image imageBack = new Image("ocean_ready_with_grid.jpg");

    public static void main(String[] args) {
        launch(args);
    }

    public static void example(int type, int column, int row) {
        String typeString = "";
        if (type == 0) {
            typeString = "EMPTY";
        }
        if (type == 1) {
            typeString = "MAST";
        }
        if (type == -1) {
            typeString = "HIT";
        }
        System.out.println("Clicked on " + typeString + " field in column " + column + " and in row " + row);
    }

    // creating top labels
    GameLabel userInterfaceLabel = new GameLabel(378, 54, "user-interface_background.jpg", "User Interface");
    GameLabel playerScoreLabel = new GameLabel(54, 40, "score_background.jpg", "Player Score");
    GameLabel computerScoreLabel = new GameLabel(54, 40, "score_background.jpg", "Computer Score");
    GameLabel playerNameLabel = new GameLabel(135, 40, "name_background.jpg", "Player Name");
    GameLabel computerNameLabel = new GameLabel(135, 40, "name_background.jpg", "Computer Name");

    // creating bottom buttons
    GameButton newGameButton = new GameButton(108, 40, "New game");
    GameButton resetButton = new GameButton(108, 40, "Reset");
    GameButton startButton = new GameButton(108, 40, "Start");
    GameButton helpButton = new GameButton(108, 40, "Help");
    GameButton exitButton = new GameButton(108, 40, "Exit");
    GameButton areYouSureExitButton = new GameButton(135, 40, "Are you sure?");
    GameButton cancelExitButton = new GameButton(135, 40, "Cancel");
    GameButton areYouSureResetButton = new GameButton(135, 40, "Are you sure?");
    GameButton cancelResetButton = new GameButton(135, 40, "Cancel");

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        Insets insets = new Insets(28,81,40, 81);
        grid.setPadding(insets);
        grid.setBackground(background);

//        Insets insetsForGrids = new Insets(0, 0, 0, 0);

        GridPane gridPlayer = new GridPane();
        gridPlayer.setAlignment(grid.getAlignment());
//        gridPlayer.setPadding(insetsForGrids);

        GridPane gridComputer = new GridPane();
        gridComputer.setAlignment(Pos.TOP_LEFT);
//        gridComputer.setPadding(insetsForGrids);

        grid.add(gridPlayer, 0, 4, 10, 10);
        grid.add(gridComputer, 12, 4, 10, 10);

        // setting size of columns in the grids
        int columnSizes[] = {27, 27, 27, 27, 27, 27, 27, 27, 27, 27,
                81, 27,
                27, 27, 27, 27, 27, 27, 27, 27, 27, 27};
        for(int i = 0; i<columnSizes.length; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(columnSizes[i])); }

        // setting size of rows in the grid
        int rowSizes[] = {54, 14, 40, 14, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 40, 40};
        for(int i = 0; i<rowSizes.length; i++) {
            grid.getRowConstraints().add(new RowConstraints(rowSizes[i]));

        }

        int columnSizesForGrids[] = {27, 27, 27, 27, 27, 27, 27, 27, 27, 27};
        for(int i = 0; i<columnSizesForGrids.length; i++) {
            gridPlayer.getColumnConstraints().add(new ColumnConstraints(columnSizesForGrids[i]));
            gridComputer.getColumnConstraints().add(new ColumnConstraints(columnSizesForGrids[i]));
        }

        int rowSizesForGrids[] = {27, 27, 27, 27, 27, 27, 27, 27, 27, 27};
        for(int i = 0; i<rowSizesForGrids.length; i++) {
            gridPlayer.getRowConstraints().add(new RowConstraints(rowSizesForGrids[i]));
            gridComputer.getRowConstraints().add(new RowConstraints(rowSizesForGrids[i]));

        }

        // setting top labels and adding to the grid
        userInterfaceLabel.setAlignment(Pos.CENTER);
        userInterfaceLabel.setPadding(new Insets(5, 0, 0, 5));
        userInterfaceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        userInterfaceLabel.setText("Hello! You are welcome!");

        playerScoreLabel.setAlignment(Pos.CENTER);
        playerScoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        playerScoreLabel.setText("0");

        computerScoreLabel.setAlignment(Pos.CENTER);
        computerScoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        computerScoreLabel.setText("0");

        playerNameLabel.setAlignment(Pos.CENTER);
        playerNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        playerNameLabel.setText("Player");

        computerNameLabel.setAlignment(Pos.CENTER);
        computerNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        computerNameLabel.setText("Computer");

        grid.add(playerScoreLabel, 8, 2);
        grid.add(computerScoreLabel, 12, 2);
        grid.add(playerNameLabel, 0, 2);
        grid.add(computerNameLabel, 17, 2);
        grid.add(userInterfaceLabel, 5, 0);

        // creating main objects
        ShipsContainer shipsContainer = new ShipsContainer();
        Scores scores = new Scores();
        Player player = new Player(grid, gridPlayer, gridComputer, shipsContainer, scores);
        Computer computer = new Computer(shipsContainer, player);

        // setting buttons to handle choices for "Exit"
        areYouSureResetButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        areYouSureResetButton.setTextFill(Color.RED);
        cancelResetButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        // setting buttons to handle choices for "Exit"
        areYouSureExitButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        areYouSureExitButton.setTextFill(Color.RED);
        cancelExitButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        // setting actions for "New game" button
        newGameButton.setOnAction(event -> {

            System.out.println("New game starts here."); // ******************* TEST ONLY ******************************

            userInterfaceLabel.setAlignment(Pos.TOP_LEFT);
            userInterfaceLabel.setPadding(new Insets(5, 0, 0, 5));
            userInterfaceLabel.setFont(Font.font("Verdana", FontWeight.MEDIUM, 12));
            userInterfaceLabel.setText("Now build your ships. \n" +
                    "FIRST build one 4-masts ship, then two 3-masts ships, \n" +
                    "then three 2-masts ships and finally four 1-mast ships.");

            player.resetAllForNewGame();
            player.createBoard(gridPlayer, shipsContainer.getSetOfControlSquares());
            player.setEmptyPlayerBoard();
            player.createShipObjectsAndAddingToContainer();
//            player.blockActionOnBoard(gridPlayer, false); // --- JEST JUŻ W metodzie reset...
            player.setShipMastOnControlSquareField();

            newGameButton.setDisable(true);
            resetButton.setDisable(false);

            helpButton.setOnAction(event1 -> {
                System.out.println("Help when player has started new game.");
                userInterfaceLabel.setAlignment(Pos.TOP_LEFT);
                userInterfaceLabel.setPadding(new Insets(5, 0, 0, 5));
                userInterfaceLabel.setFont(Font.font("Verdana", FontWeight.MEDIUM, 12));
                userInterfaceLabel.setText("To build your ship click on any field on Player board.\n" +
                        "When ship is built, build next ship until all ships are built.\n" +
                        "Then press \"Start\" to start your game.");
                helpButton.setDisable(true);
            });

        });

        resetButton.setDisable(true);

        // setting actions for "Reset" button
        resetButton.setOnAction(event -> {
            System.out.println("Reset game");
            grid.add(areYouSureResetButton, 5, 8);
            grid.add(cancelResetButton, 12, 8);
            resetButton.setDisable(true);
        });

        // setting actions for "Are you sure?" button for "Reset" choice
        areYouSureResetButton.setOnAction(event -> {
//            player.resetAllForNewGame();
            grid.getChildren().remove(areYouSureResetButton);
            grid.getChildren().remove(cancelResetButton);
            player.blockActionOnBoard(gridPlayer, true);
            player.blockActionOnBoard(gridComputer, true);
            newGameButton.setDisable(false);
        });

        // setting actions for "Cancel" button for "Reset" choice
        cancelResetButton.setOnAction(event -> {
            grid.getChildren().remove(areYouSureResetButton);
            grid.getChildren().remove(cancelResetButton);
            resetButton.setDisable(false);
        });

        // setting actions for "Start game" button
        startButton.setDisable(true); // ************************ czy to jest potrzebne? *******************************

        startButton.setOnAction(event -> {
            player.blockActionOnBoard(gridPlayer, true);
            player.blockActionOnBoard(gridComputer, false);
            startButton.setDisable(true);
            System.out.println("Starting game");
//            Random random = new Random();
//            int randomParameter = random.nextInt(5);
            player.createBoard(gridComputer, shipsContainer.getSetOfComputerControlSquares());
            player.setEmptyComputerBoard();
// *********************************************************************************************************************
            player.createComputerShipObjectsAndAddingToContainer();
            player.buildShipsOnComputerBoard();

// *********************************************************************************************************************
//            computer.createShipsOnComputerBoard(randomParameter);
//            computer.createShipObjectsAndShipsCoordinates(randomParameter);
//            computer.protectAllComputerShipsPositions();
// *********************************************************************************************************************
            player.shootOnComputerBoard();

        });


        // setting actions for "Help" button
        helpButton.setOnAction(event -> {
            System.out.println("Help for player wanted to start new game.");
            userInterfaceLabel.setAlignment(Pos.CENTER_LEFT);
            userInterfaceLabel.setPadding(new Insets(7, 0, 8, 5));
            userInterfaceLabel.setFont(Font.font("Verdana", FontWeight.MEDIUM, 12));
            userInterfaceLabel.setText("Press \"New game\" button to start a new game.\n" +
                    "Press \"Exit\" button to end the game.");
        });

        // setting actions for "Exit" button
        exitButton.setOnAction(event -> {
            System.out.println("Exit game");
            grid.add(areYouSureExitButton, 5, 8);
            grid.add(cancelExitButton, 12, 8);
            exitButton.setDisable(true);
        });

        // setting actions for "Are you sure?" button for "Exit" choice
        areYouSureExitButton.setOnAction(event -> {
            System.out.println("Player confirmed their decision about exit game.");
            Platform.exit();
        });

        // setting actions for "Cancel" button for "Exit" choice
        cancelExitButton.setOnAction(event -> {
            System.out.println("Player's changed their mind about exit game.");
            grid.getChildren().remove(areYouSureExitButton);
            grid.getChildren().remove(cancelExitButton);
            exitButton.setDisable(false);
        });

        // adding bottom buttons to grid
        grid.add(newGameButton, 0, 15);
        grid.add(resetButton, 5, 15);
        grid.add(startButton, 10, 15);
        grid.add(helpButton, 13, 15);
        grid.add(exitButton, 18, 15);

        Scene scene = new Scene(grid, 810, 540, Color.BLACK);

        primaryStage.setTitle("Ship Battle");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

}

