package com.example.snakesandladders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(int x, int y){
        setWidth(Main.Tile_size);
        setHeight(Main.Tile_size);

        setFill(Color.PINK);
        setStroke(Color.BLACK);
    }
}


