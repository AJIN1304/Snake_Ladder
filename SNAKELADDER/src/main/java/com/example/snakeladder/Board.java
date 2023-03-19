package com.example.snakeladder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Pair;

import java.util.ArrayList;

public class Board {

    private ArrayList<Pair<Integer,Integer>>positionCoordinates;
    private ArrayList<Integer>snakeLadder;

    public Board(){
        populatePositionCoordinates();
        setSnakeLadder();
    }

    private void populatePositionCoordinates(){
        positionCoordinates =new ArrayList<>();
        positionCoordinates.add(new Pair<>(0,0));//dummy value

      for(int i=0;i<SNAKELADDER.height;i++)
      {
          for(int j=0;j<SNAKELADDER.width;j++)
          {
              //X axis

              int xCord=0;
              if(i%2==0)
              {
                  xCord=j*SNAKELADDER.tileSize+SNAKELADDER.tileSize/2;
              }
              else
              {
                 xCord=SNAKELADDER.height*SNAKELADDER.tileSize-j*SNAKELADDER.tileSize-SNAKELADDER.tileSize/2;
              }




              //Yaxis
              int yCord=SNAKELADDER.height*SNAKELADDER.tileSize-i*SNAKELADDER.tileSize-SNAKELADDER.tileSize/2;


              positionCoordinates.add(new Pair<>(xCord,yCord));
          }
      }
    }

    public int getXCoordinate(int position)
    {
       if(position>0 && position<=100)
       {
           return positionCoordinates.get(position).getKey();
       }
       return -1;
    }

    private void setSnakeLadder(){

        snakeLadder=new ArrayList<>();
        for(int i=0;i<101;i++)
        {
            snakeLadder.add(i);
        }


        snakeLadder.set(4,14);
        snakeLadder.set(8,30);
        snakeLadder.set(28,74);
        snakeLadder.set(32,10);
        snakeLadder.set(36,6);
        snakeLadder.set(48,26);
        snakeLadder.set(50,67);
        snakeLadder.set(62,18);
        snakeLadder.set(71,92);
        snakeLadder.set(80,99);
        snakeLadder.set(88,24);
        snakeLadder.set(95,56);
        snakeLadder.set(97,78);
        snakeLadder.set(1,38);

    }
    public int getYCoordinate(int position)
    {
        if(position>0 && position<=100)
        {
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }


    public int getSnakeLadder(int position){
       return snakeLadder.get(position);
    }

    public static void main(String[] args) {

        Board board=new Board();
        for(int i=0;i<board.positionCoordinates.size();i++)
        {
            System.out.println(i+" $ X : "+board.positionCoordinates.get(i).getKey()
                    +" Y : "+board.positionCoordinates.get(i).getValue());
        }
    }


}
