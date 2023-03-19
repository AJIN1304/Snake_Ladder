package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SNAKELADDER extends Application {

    public static final int tileSize=40,width=10,height=10,

        buttonLine=height*tileSize+50,infoLine=height*tileSize+20;

    Player playerFirst,playerSecond;

    boolean firstPlayerTurn=true,gamestart=false;


     int diceValue;

       private Pane createContent()
    {
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+100);


//set 100 tiles
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                tile tile=new tile(tileSize);
                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);
                //anything we wanted to shown on the screen
                root.getChildren().addAll(tile);
            }
        }


        //putting image on the root
        Image img=new Image("C:\\Users\\Admin\\IdeaProjects\\SNAKELADDER\\src\\img.png");
        ImageView boarImage=new ImageView();
        boarImage.setFitWidth(width*tileSize);
        boarImage.setFitHeight(height*tileSize);
        boarImage.setImage(img);
      //  root.getChildren().addAll(boarImage);

        //Button and info
        //button 1
        Button startButton=new Button("START");
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonLine);

        //button 2
        Button playerOneButton =new Button("PLAYER ONE");
        playerOneButton.setTranslateX(10);
        playerOneButton.setTranslateY(buttonLine);

        //button 3
        Button playerTwoButton =new Button("PLAYER TWO");
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);

        Label diceLabel=new Label("START THE GAME");
        diceLabel.setTranslateX(180);
        diceLabel.setTranslateY(infoLine);

        //Players
        playerFirst=new Player("Amit", Color.BLACK,tileSize/2);
        playerSecond=new Player("Ajin", Color.WHITE,tileSize/2-5);

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart)
                {
                    if(firstPlayerTurn)
                    {
                        diceValue=rollDice();
                        diceLabel.setText("Dice :"+diceValue);
                        playerFirst.movePlayer(diceValue);
                        firstPlayerTurn=!firstPlayerTurn;
                        if(playerFirst.checkWinner())
                        {
                            diceLabel.setText("Winner is "+playerFirst.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerTurn=true;
                            gamestart=false;
                        }

                    }
                }


            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart)
                {
                    if(!firstPlayerTurn)
                    {
                        diceValue=rollDice();
                        diceLabel.setText("Dice :"+diceValue);
                        playerSecond.movePlayer(diceValue);
                        firstPlayerTurn=!firstPlayerTurn;
                        if(playerSecond.checkWinner())
                        {
                            diceLabel.setText("Winner is "+playerSecond.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerTurn=true;
                            gamestart=false;
                        }
                    }
                }


            }
        });


        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gamestart=true;
                startButton.setDisable(true);
                playerFirst.setStart();
                playerSecond.setStart();

            }
        });


        root.getChildren().addAll(boarImage,startButton,playerOneButton,playerTwoButton,
         diceLabel,playerFirst.getCoin(), playerSecond.getCoin()
        );

        return root;
    }

    private int rollDice(){
           return (int) (Math.random()*6+1);
    }




    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}