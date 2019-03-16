package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Hit extends Button {

    public Hit() {
        setStyle("-fx-background-image: url(explosion_square.jpg);");
        setMinSize(27, 27);
        setMaxSize(27, 27);
    }

//    public int[] getHitColumnAndRow(Hit hit) {
//        int column = (int)((hit.getLocalToParentTransform().getTx()-81)/27);
//        int row = (int)((hit.getLocalToParentTransform().getTy()-150)/27);
//        int results[] = {-1, column, row}; // -1 indicates hit field
//        return results;
//    }

    public static void getPositionAndSetActionOfHit(GridPane grid) {
        Hit hit = new Hit();
        ObservableList<Node> childrenOfHits = grid.getChildren();
        for(Node node : childrenOfHits) {
            if(node.getClass() == hit.getClass()) {
                Hit button = (Hit) node;
                button.setOnAction(event -> {
                    int column = (int)((button.getLocalToParentTransform().getTx()-81)/27);
                    int row = (int)((button.getLocalToParentTransform().getTy()-150)/27);
                    ShipBattle.example(-1, column, row);
                });
            }
        }

    }


}
