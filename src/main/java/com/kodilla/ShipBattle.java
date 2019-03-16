package com.kodilla;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static com.kodilla.ControlSquare.getPositionAndSetActionOfControlSquare;
import static com.kodilla.Hit.getPositionAndSetActionOfHit;
import static com.kodilla.ShipMast.getPositionAndSetActionOfShipMast;

public class ShipBattle extends Application implements EventHandler<ActionEvent>{

    private Image imageBack = new Image("ocean_ready_with_grid.jpg");

    public static void main(String[] args) {
        launch(args);
    }

    public static void example(int type, int column, int row) {
        String typeString = "";
        if (type == 0) {
            typeString = "empty";
        }
        if (type == 1) {
            typeString = "mast";
        }
        if (type == -1) {
            typeString = "hit";
        }
        System.out.println("Clicked on " + typeString + " field in column " + column + " and in row " + row);
    }

    @Override
    public void handle(ActionEvent event) {
        ///
    }

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

        // setting size of columns in the grid
        int columnSizes[] = {27, 27, 27, 27, 27, 27, 27, 27, 27, 27,
                81, 27,
                27, 27, 27, 27, 27, 27, 27, 27, 27, 27};
        for(int i = 0; i<columnSizes.length; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(columnSizes[i]));
        }

        // setting size of rows in the grid
        int rowSizes[] = {54, 14, 40, 14, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 40, 40};
        for(int i = 0; i<rowSizes.length; i++) {
            grid.getRowConstraints().add(new RowConstraints(rowSizes[i]));
        }

        // creating top labels and adding to the grid
        GameLabel userInterfaceLabel = new GameLabel(378, 54, "user-interface_background.jpg");
        userInterfaceLabel.setText("Hello! Here a player can read some news, e.g. instruction \n" +
                "We well see how text will fill this label. \n" + "3rd line");
        userInterfaceLabel.setAlignment(Pos.TOP_LEFT);
        userInterfaceLabel.setFont(Font.font("Verdana", FontWeight.MEDIUM, 12));
        userInterfaceLabel.setPadding(new Insets(5, 0, 0, 5));

        GameLabel playerScoreLabel = new GameLabel(54, 40, "score_background.jpg");
        playerScoreLabel.setText("0");
        playerScoreLabel.setAlignment(Pos.CENTER);
        playerScoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        GameLabel computerScoreLabel = new GameLabel(54, 40, "score_background.jpg");
        computerScoreLabel.setText("1");
        computerScoreLabel.setAlignment(Pos.CENTER);
        computerScoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        GameLabel playerNameLabel = new GameLabel(135, 40, "name_background.jpg");
        playerNameLabel.setText("Player Name");
        playerNameLabel.setAlignment(Pos.CENTER);
        playerNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        GameLabel computerNameLabel = new GameLabel(135, 40, "name_background.jpg");
        computerNameLabel.setText("Computer");
        computerNameLabel.setAlignment(Pos.CENTER);
        computerNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        grid.add(playerScoreLabel, 8, 2);
        grid.add(computerScoreLabel, 12, 2);
        grid.add(playerNameLabel, 0, 2);
        grid.add(computerNameLabel, 17, 2);
        grid.add(userInterfaceLabel, 5, 0);

        // creating bottom buttons and adding to the grid
        GameButton newGameButton = new GameButton(108, 40, "New game");
        newGameButton.setOnAction(event -> System.out.println("New game will start here."));

        GameButton setShipButton = new GameButton(108, 40, "Set ship");
        setShipButton.setOnAction(event -> System.out.println("Player accepts their ship built on the board"));

        GameButton startGameButton = new GameButton(108, 40, "Start");
        startGameButton.setOnAction(event -> System.out.println("Starting game"));

        GameButton helpButton = new GameButton(108, 40, "Help");
        helpButton.setOnAction(event -> System.out.println("Displaying instruction for player"));

        GameButton exitGameButton = new GameButton(108, 40, "Exit");
        exitGameButton.setOnAction(event -> System.out.println("Exit game"));

        grid.add(newGameButton, 0, 15);
        grid.add(setShipButton, 5, 15);
        grid.add(startGameButton, 10, 15);
        grid.add(helpButton, 13, 15);
        grid.add(exitGameButton, 18, 15);

        // temporary creating fields for the board and adding to the grid
        ControlSquare emptyField0 = new ControlSquare();
        ControlSquare emptyField1 = new ControlSquare();
        ControlSquare emptyField2 = new ControlSquare();

        ShipMast shipMast0 = new ShipMast();
        ShipMast shipMast1 = new ShipMast();
        ShipMast shipMast2 = new ShipMast();
        ShipMast shipMast3 = new ShipMast();
        ShipMast shipMast4 = new ShipMast();
        ShipMast shipMast5 = new ShipMast();
        ShipMast shipMast6 = new ShipMast();
        ShipMast shipMast7 = new ShipMast();
        ShipMast shipMast8 = new ShipMast();

        Hit hit0 = new Hit();
        Hit hit1 = new Hit();
        Hit hit2 = new Hit();

        grid.add(emptyField0, 0, 5);
        grid.add(emptyField1, 0, 6);
        grid.add(emptyField2, 0, 7);

        grid.add(shipMast0, 5, 7);
        grid.add(shipMast1, 6, 7);
        grid.add(shipMast2, 7, 7);
        grid.add(shipMast3, 2, 4);
        grid.add(shipMast4, 2, 5);
        grid.add(shipMast5, 2, 6);
        grid.add(shipMast6, 2, 7);
        grid.add(shipMast7, 8, 13);
        grid.add(shipMast8, 9, 13);

        grid.add(hit0, 0, 4);
        grid.add(hit1, 1, 5);
        grid.add(hit2, 1, 7);

        // setting actions, getting position and calling specific methods for clicked mouse events
        getPositionAndSetActionOfControlSquare(grid);
        getPositionAndSetActionOfShipMast(grid);
        getPositionAndSetActionOfHit(grid);

        // ********************* temporary place *********************
        // setting actions for clicked mouse events

//        ControlSquare controlSquare = new ControlSquare();
//        ObservableList<Node> childrenOfControlSquares = grid.getChildren();
//        for(Node node : childrenOfControlSquares) {
//            if(node.getClass() == controlSquare.getClass()) {
//                ControlSquare button = (ControlSquare) node;
//                button.setOnAction(event -> {
//                    int pos[] = button.getControlSquareColumnAndRow(button);
//                    int type = pos[0];
//                    int x = pos[1];
//                    int y = pos[2];
//                    example(type, x, y);
//                });
//            }
//        }

//        ShipMast shipMast = new ShipMast();
//        ObservableList<Node> childrenOfShipMasts = grid.getChildren();
//        for(Node node : childrenOfShipMasts) {
//            if(node.getClass() == shipMast.getClass()) { // if(node.getClass().isInstance(shipMast))
//                ShipMast button = (ShipMast) node;
//                button.setOnAction(event -> {
//                    int pos[] = button.getShipMastColumnAndRow(button);
//                    int type = pos[0];
//                    int x = pos[1];
//                    int y = pos[2];
//                    example(type, x, y);
//                });
//            }
//        }

//        Hit hit = new Hit();
//        ObservableList<Node> childrenOfHits = grid.getChildren();
//        for(Node node : childrenOfHits) {
//            if(node.getClass() == hit.getClass()) {
//                Hit button = (Hit) node;
//                button.setOnAction(event -> {
//                    int pos[] = button.getHitColumnAndRow(button);
//                    int type = pos[0];
//                    int x = pos[1];
//                    int y = pos[2];
//                    example(type, x, y);
//                });
//            }
//        }

        // ***********************************************************

        Scene scene = new Scene(grid, 810, 540, Color.BLACK);

        primaryStage.setTitle("Ship Battle");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

}

