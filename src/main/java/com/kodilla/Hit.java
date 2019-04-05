package com.kodilla;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class Hit extends Button {

    private Pair<Integer, Integer> hitCoordinates;

    public Hit(Pair<Integer, Integer> hitCoordinates) {

        this.hitCoordinates = hitCoordinates;

        setStyle("-fx-background-image: url(explosion_square.jpg);");
        setMinSize(27, 27);
        setMaxSize(27, 27);

    }

// method used only for testing in the beginning of writing this program

//    public static void getPositionAndSetActionOfHit(GridPane grid) {
//        Hit hit = new Hit();
//        ObservableList<Node> childrenOfHits = grid.getChildren();
//        for(Node node : childrenOfHits) {
//            if(node.getClass() == hit.getClass()) {
//                Hit button = (Hit) node;
//                button.setOnAction(event -> {
//                    int column = (int)((button.getLocalToParentTransform().getTx())/27);
//                    int row = (int)((button.getLocalToParentTransform().getTy())/27);
//                    ShipBattle.example(-1, column, row);
//                });
//            }
//        }
//    }

}
