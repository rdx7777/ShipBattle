package com.kodilla;

import javafx.scene.control.Button;

public class Hit extends Button {

    public Hit() {
        setStyle("-fx-background-image: url(explosion_square.jpg);");
        setMinSize(27, 27);
        setMaxSize(27, 27);
    }

    public int[] getHitColumnAndRow(Hit hit) {
        int column = (int)((hit.getLocalToParentTransform().getTx()-81)/27);
        int row = (int)((hit.getLocalToParentTransform().getTy()-150)/27);
        int results[] = {-1, column, row}; // -1 indicates hit field
        return results;
    }


}
