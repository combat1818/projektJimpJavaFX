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
        this.backButton=new Button("Go back");
        this.backButton.setOnAction(e->{
            at.stop();
            buttonState=true;
            stopButton.setText("Stop");
            nextStateButton.setDisable(true);
            Utils.arrayCopy(this.board.actualState,this.board.initialState,70,70);
            Utils.arrayCopy(this.board.actualState,this.board.initialState,70,70);
            for(int i=0;i<70;i++){
                for(int j=0;j<70;j++){
                    rectangles[i][j].setFill(Color.WHITE);
                }
            }
            this.stopButton.setDisable(true);
            this.nextStateButton.setDisable(true);
            Data.window.setScene(sc);
        });
        this.backButton.setLayoutY(750);
        this.backButton.setLayoutX(380);
        this.backButton.setDisable(true);

        ((Pane)getRoot()).getChildren().add(backButton);

        this.stopButton=new Button("Stop");
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
        this.stopButton.setLayoutY(750);
        this.stopButton.setLayoutX(450);

        ((Pane)getRoot()).getChildren().add(stopButton);

        this.nextStateButton=new Button("Next Generation");
        this.nextStateButton.setOnAction(e->{
            Utils.arrayCopy(board.previousState,board.actualState,70,70);
            board.calcNextGeneration(board.previousState,board.actualState,70,70);
            for(int i=0;i<70;i++){
                for(int j=0;j<70;j++){
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
        this.nextStateButton.setLayoutY(750);
        this.nextStateButton.setLayoutX(550);
        this.nextStateButton.setDisable(true);
        ((Pane)getRoot()).getChildren().add(nextStateButton);

        for(int i=0;i<70;i++){
            for(int j=0;j<70;j++){
                rectangles[i][j]=new Rectangle(50+10*i,50+10*j,10,10);
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
                    Utils.arrayCopy(board.previousState,board.actualState,70,70);
                    board.calcNextGeneration(board.previousState,board.actualState,70,70);

                    for(int i=0;i<70;i++){
                        for(int j=0;j<70;j++){
                            rectangles[i][j].setFill(colors[board.actualState[i][j]]);
                        }
                    }
                    numberOfGenerationsLeft--;
                    if(numberOfGenerationsLeft==0){
                        at.stop();
                        Utils.writeToFile(70,70,"out.txt",board.actualState);
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
