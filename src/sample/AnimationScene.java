package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import jdk.jshell.execution.Util;
import org.w3c.dom.css.Rect;

public class AnimationScene extends Scene {

    private Scene sc;
    private Button backButton, stopButton, nextStateButton;

    private AnimationTimer timer;
    private int numberOfGenerationsLeft,i=0;
    private int initialNumberOfGenerations;

    private Color[] colors= {Color.BLACK,Color.BLUE,Color.RED,Color.YELLOW};
    private int seconds=0;
    private AnimationTimer at;
    private Rectangle[][] rectangles=new Rectangle[70][70];
    private Board board;
    private boolean buttonState;


    public AnimationScene(Pane pane, Stage st, Scene sc, Board b, int number){
        super(pane,800,800);
        this.sc=sc;
        this.board=b;
        this.initialNumberOfGenerations=number;
        this.buttonState=true;
        this.setLayout();
    }

    public void setLayout(){
        int width = 800;
        int height = 800;
        this.backButton=new Button("Go back");
        this.backButton.setPrefWidth(80);
        this.backButton.setPrefHeight(30);
        this.backButton.setOnAction(e->{
            at.stop();
            buttonState=true;
            stopButton.setText("Stop");
            nextStateButton.setDisable(true);
            Utils.arrayCopy(this.board.actualState,this.board.initialState);
            Utils.arrayCopy(this.board.actualState,this.board.initialState);
            for(int i=0;i<Utils.returnHeight();i++){
                for(int j=0;j<Utils.returnWidth();j++){
                    rectangles[i][j].setFill(Color.WHITE);
                }
            }
            this.stopButton.setDisable(true);
            this.nextStateButton.setDisable(true);
            Data.window.setScene(sc);
        });
        this.backButton.setLayoutY(752);
        this.backButton.setLayoutX(50);
        this.backButton.setDisable(true);

        ((Pane)getRoot()).getChildren().add(backButton);

        this.stopButton=new Button("Stop");
        this.stopButton.setPrefWidth(150);
        this.stopButton.setPrefHeight(30);
        this.stopButton.setOnAction(e->{
            if(buttonState) {
                at.stop();
                buttonState=false;
                stopButton.setText("Start");
                nextStateButton.setDisable(false);
            }else if(!buttonState){
                at.start();
                buttonState=true;
                stopButton.setText("Stop");
                nextStateButton.setDisable(true);
            }
        });
        this.stopButton.setLayoutY(752);
        this.stopButton.setLayoutX(235);

        ((Pane)getRoot()).getChildren().add(stopButton);

        this.nextStateButton=new Button("Next Generation");
        this.nextStateButton.setPrefWidth(150);
        this.nextStateButton.setPrefHeight(30);
        this.nextStateButton.setOnAction(e->{
            Utils.arrayCopy(board.previousState,board.actualState);
            board.calcNextGeneration(board.previousState,board.actualState, Utils.returnWidth(),Utils.returnHeight());
            for(int i=0;i<Utils.returnHeight();i++){
                for(int j=0;j<Utils.returnWidth();j++){
                    rectangles[i][j].setFill(colors[board.actualState[i][j]]);
                }
            }
            numberOfGenerationsLeft--;
            if(numberOfGenerationsLeft==0){
                at.stop();
                this.backButton.setDisable(false);
                this.stopButton.setDisable(true);
                this.nextStateButton.setDisable(true);
                //Data.window.setScene(sc);
            }
        });
        this.nextStateButton.setLayoutY(752);
        this.nextStateButton.setLayoutX(415);
        this.nextStateButton.setDisable(true);
        ((Pane)getRoot()).getChildren().add(nextStateButton);

        for(int i=0;i<Utils.returnHeight();i++){
            for(int j=0;j<Utils.returnWidth();j++){
                rectangles[i][j]=new Rectangle((width-Utils.returnWidth()*10)/2+10*i,(height-Utils.returnHeight()*10)/2+10*j, 10,10);
                rectangles[i][j].setFill(Color.WHITE);
                ((Pane)getRoot()).getChildren().add(rectangles[i][j]);
            }
        }

    }

    public void startAnimation(){
        System.out.println("HALO KARTHUIS");
        stopButton.setDisable(false);
        nextStateButton.setDisable(false);
        numberOfGenerationsLeft=initialNumberOfGenerations;
        at= new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(i==30) {
                    System.out.println(seconds);
                    i=0;
                    seconds++;
                    Utils.arrayCopy(board.previousState,board.actualState);
                    board.calcNextGeneration(board.previousState,board.actualState, Utils.returnWidth(),Utils.returnHeight());

                    for(int i=0;i<Utils.returnHeight();i++){
                        for(int j=0;j<Utils.returnWidth();j++){
                            rectangles[i][j].setFill(colors[board.actualState[i][j]]);
                        }
                    }
                    numberOfGenerationsLeft--;
                    if(numberOfGenerationsLeft==0){
                        at.stop();
                        Utils.writeToFile("out-new.txt",board.actualState);
                        backButton.setDisable(false);
                        stopButton.setDisable(true);
                        nextStateButton.setDisable(true);
                        //Data.window.setScene(sc);
                    }
                }else
                    i++;

            }

        };
        at.start();
    }

    public void reset(){

    }


}
