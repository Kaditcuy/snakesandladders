package com.example.snakesandladders;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    public int rand;
    public Label randResult;

    public int circlePos[][] = new int[10][10];
    public int ladderPos9[][] = new int[6][3];

    public static final int Tile_size = 80;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public int playerPosition1 = 1;
    public int playerPosition2 = 1;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public static int player1XPos = 40;
    public static int player1YPos = 760;

    public static int player2XPos = 40;
    public static int player2YPos = 760;

    public int posCir1 = 1;
    public int posCir2 = 1;

    public boolean gameStart = false;
    public Button gameButton;



    private Group tileGroup = new Group();

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*Tile_size, (height * Tile_size) + 80);
        root.getChildren().addAll(tileGroup);

            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++){
                    Tile tile = new Tile(Tile_size,Tile_size);
                    tile.setTranslateX(j * Tile_size);
                    tile.setTranslateY(i * Tile_size);
                    tileGroup.getChildren().add(tile);

                    circlePos[i][j] = j*(Tile_size - 40);
                }
            }

            for (int i = 0; i < height; i++){
                for (int j = 0; j< width; j++){
                   System.out.print(circlePos[i][j]+ " ");
                }
                System.out.println();
            }
        player1 = new Circle(40);
        player1.setId("player1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);


        player2 = new Circle(40);
        player2.setId("player2");
        player2.setFill(Color.CHOCOLATE);
        player2.getStyleClass().add("style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);

        Button button1 = new Button("Player1");
        button1.setTranslateX(700);
        button1.setTranslateY(820);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart){
                    if (player1Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        move1Player();
                        translatePlayer(player1XPos,player1YPos,player1);
                        player1Turn = false;
                        player2Turn = true;
                        playerPosition1+=rand;

                        if (player1XPos == 200 && player1YPos == 760){
                            translatePlayer(player1XPos = 80, player1YPos = 520, player1);
                        }

                    }
                }
            }
        });

        Button button2 = new Button("Player2");
        button2.setTranslateX(80);
        button2.setTranslateY(800);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart){
                    if (player2Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        move2Player();
                        translatePlayer(player2XPos,player2YPos,player2);
                        player2Turn = false;
                        player1Turn = true;
                        playerPosition2+=rand;

                        if (player1XPos == 200 && player1YPos == 760){
                            translatePlayer(player1XPos = 100, player1YPos = 520, player1);
                        }
                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(380);
        gameButton.setTranslateY(820);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameButton.setText("Game Started");
                player1XPos = 40;
                player1YPos =  760;

                player2XPos = 40;
                player2YPos = 760;

                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);
                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);
                gameStart = true;

            }
        });

        randResult = new Label("0");
        randResult.setTranslateX(300);
        randResult.setTranslateY(820);

        Image img = new Image("file:src/snakebg.jpg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(800);
        bgImage.setFitWidth(800);


        tileGroup.getChildren().addAll(bgImage,player1,player2,button1,button2,gameButton,randResult);
        return root;
    }


    private void getDiceValue(){
        rand = (int)(Math.random()*6+1);
    }

    private void move1Player(){
        for (int i = 0; i < rand; i++){
            if (posCir1 % 2 == 1){
                player1XPos+=80;
            }
            if (posCir1 % 2 == 0){
                player1XPos-=80;
            }
            if (player1XPos > 760){
                player1YPos-=80;
                player1XPos-=80;
                posCir1++;
            }
            if (player1XPos < 40){
                    player1YPos-=80;
                    player1XPos+=80;
                    posCir1++;
            }


            if (player1XPos < 30 || player1YPos < 30){
                player1XPos = 40;
                player1YPos = 40;
                randResult.setText("Player one won");
                gameButton.setText("Start Again");
            }
        }

    }

    private void move2Player(){
        for (int i = 0; i < rand; i++){
            if (posCir2 % 2 == 1){
                player2XPos+=80;
            }
            if (posCir1 % 2 == 0){
                player2XPos-=80;
            }
            if (player2XPos > 760){
                player2YPos-=80;
                player2XPos-=80;
                posCir2++;
            }
            if (player2XPos < 40){
                player2YPos-=80;
                player2XPos+=80;
                posCir2++;
            }


            if (player2XPos < 30 || player2YPos < 30){
                player2XPos = 40;
                player2YPos = 40;
                randResult.setText("Player two won");
                gameButton.setText("Start Again");
            }
        }

    }


    private void translatePlayer(int x, int y, Circle b){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();

    }



    @Override
    public void start(Stage primarystage) throws Exception {
        Scene scene = new Scene(createContent());

        primarystage.setTitle("Snake and Ladder");
        primarystage.setScene(scene);
        primarystage.show();

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//
//            }
//        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}