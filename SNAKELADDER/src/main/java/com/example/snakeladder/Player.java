package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private String name;

    private int position;

    private Circle coin;

    private static Board gameBoard=new Board();




    public Player(String name, Color coinColor,int coinSize)
    {

        this.name=name;

        position =0;

        coin=new Circle(coinSize);
        coin.setFill(coinColor);

        movePlayer(1);

    }


    public void setStart()
    {
        position=0;
        movePlayer(1);
    }

    public void movePlayer(int diceValue)
    {

        if(position+diceValue<=100)
        {
            position+=diceValue;
            TranslateTransition secondMove=null ,firstMove= translatePlayer();

            int newPos= gameBoard.getSnakeLadder(position);
            if(newPos!=position)
            {

                position=newPos;
               secondMove=translatePlayer();

            }
           // coin.setTranslateX(gameBoard.getXCoordinate(position));
          //  coin.setTranslateY(gameBoard.getYCoordinate(position));

            if(secondMove==null)
            {
                firstMove.play();
            }
            else
            {
                SequentialTransition seq=new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(500)),secondMove );
                seq.play();
            }
        }
    }

    private TranslateTransition translatePlayer(){

        TranslateTransition move =new TranslateTransition(Duration.millis(1000),coin);

        move.setToX(gameBoard.getXCoordinate(position));
        move.setToY(gameBoard.getYCoordinate(position));
        move.setAutoReverse(false);
        return move;
    }



public boolean checkWinner()
     {

      if(position==100)

         return true;
         return false;


     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Circle getCoin() {
        return coin;
    }

    public void setCoin(Circle coin) {
        this.coin = coin;
    }
}
