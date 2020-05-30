package sample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;

public class Main extends Application {


    private String pathToFile;
    private int generationsNumber;
    private boolean isPathSet=false;
    private boolean isGenerationNumberSet=false;
    private Button startAnimationButton;
    private Board board;
    private int width = 800;
    private int height = 800;
    private Pane root = new Pane();
    private Text failure = new Text();
    private Stage primaryStage;
    private Scene mainMenuScene;
    private AnimationScene AnimationScene;

    @Override
    public void start(Stage primaryStage) {
        root.setStyle("-fx-background-color: #fbc531;");
        Data.window=primaryStage;
        //this.primaryStage=primaryStage;
        this.mainMenuScene = new Scene(root, width, height);
        primaryStage.setResizable(false);

        /* title */
        Text title = new Text("Wireworld");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 80));
        title.setFill(Color.BLUE);
        title.applyCss();
        final double widthTitle = title.getLayoutBounds().getWidth();
        title.setX(width/2 - widthTitle/2);
        title.setY(height*0.2);



        //Start Animation Button
        this.startAnimationButton=new Button("Start Animation");
        startAnimationButton.setOnAction(e-> {
            Data.window.setScene(this.AnimationScene);
            this.AnimationScene.startAnimation();
        });
        startAnimationButton.applyCss();
        final double widthButton = startAnimationButton.getLayoutBounds().getWidth();
        startAnimationButton.setStyle("-fx-font-size: 2em; ");
        startAnimationButton.setPrefWidth(width*0.3);
        startAnimationButton.setPrefHeight(height*0.1);
        startAnimationButton.setLayoutX((int)width/2 - width*0.3/2);
        startAnimationButton.setLayoutY(height*0.3);
        startAnimationButton.setDisable(true);



        //InputField for Path to File
        TextField pathToFileField = new TextField();
        pathToFileField.setPrefWidth(150);
        Button confirmPathToFileButton=new Button("Confirm Path");
        confirmPathToFileButton.setOnAction(e->{
            setPath(pathToFileField.getText());
            pathToFileField.setText("");
        });
        pathToFileField.applyCss();
        final double widthPath = pathToFileField.getLayoutBounds().getWidth();
        pathToFileField.setLayoutY(height*0.5);
        pathToFileField.setLayoutX(width/4 - 75);
        confirmPathToFileButton.setLayoutY(height*0.5+30);
        confirmPathToFileButton.setLayoutX(width/4 - 75);


        //Input Field for GenerationsNumber
        TextField generationsNumberField = new TextField();
        generationsNumberField.setPrefWidth(150);
        Button confirmGenerationsNumberButton=new Button("Confirm Number");
        confirmGenerationsNumberButton.setOnAction(e->{
            setGenerationsNumber(generationsNumberField.getText());
            generationsNumberField.setText("");
        });
        generationsNumberField.setLayoutY(height*0.5);
        generationsNumberField.setLayoutX(width/2+ width/4 - 75);
        confirmGenerationsNumberButton.setLayoutY(height*0.5+30);
        confirmGenerationsNumberButton.setLayoutX(width/2+ width/4 - 75);


        /* info */

        Text creditails = new Text("Created by Aleksander Lorenc & Adrian Mostowski 2020 for JIMP2 project.");
        creditails.setFont(Font.font("Arial", FontWeight.LIGHT, 10));
        creditails.setFill(Color.GRAY);
        creditails.applyCss();
        final double widthCreditails = creditails.getLayoutBounds().getWidth();
        creditails.setX(width/2 - widthCreditails/2);
        creditails.setY(height*0.9);



        root.getChildren().addAll(title,creditails,startAnimationButton,pathToFileField,confirmPathToFileButton,
                generationsNumberField, confirmGenerationsNumberButton);
        primaryStage.setTitle("WireWorld simulation");
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }


    private void setPath(String path){
        this.pathToFile=path;
        this.isPathSet=true;
        if(isPathSet && isGenerationNumberSet)
            startAnimation();
    }

    private void setGenerationsNumber(String number){
        try{
            generationsNumber=Integer.parseInt(number);
            isGenerationNumberSet=true;
            if(isPathSet && isGenerationNumberSet)
                startAnimation();
        }catch(NumberFormatException e){
            System.out.println(e);
        }
    }

    private Text returnFailure(String text){
        root.getChildren().remove(failure);
        Text failure = new Text(text);
        failure.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        failure.setFill(Color.RED);
        failure.applyCss();
        final double widthCreditails = failure.getLayoutBounds().getWidth();
        failure.setX(this.width/2 - widthCreditails/2);
        failure.setY(this.height*0.7);
        root.getChildren().add(failure);
        return failure;
    }

    private void startAnimation(){
        isPathSet=false;
        isGenerationNumberSet=false;

        try{
            int arr[][] =Utils.readBoard(this.pathToFile);
            System.out.println(arr);
            if(arr == null){
                failure = returnFailure("Niewłaściwy wymiar tablicy.");
            }
            else if(Utils.returnHeight()<5 || Utils.returnWidth()<5){
                failure = returnFailure("Podaj wielkość planszy bądź zwiększ ją(min. 5x5)");
            }else if( Utils.returnHeight() >70 || Utils.returnWidth() >70){
                failure = returnFailure("Podaj mniejszą wielkość planszy(maximum 70x70)");
            }else{
                failure = returnFailure("");

                this.board = new Board(Utils.returnHeight(),Utils.returnWidth());
                this.board.initialState=arr;
                Utils.arrayCopy(this.board.previousState,this.board.initialState);
                Utils.arrayCopy(this.board.actualState,this.board.initialState);

                AnimationScene=new AnimationScene(new Pane(),this.primaryStage,this.mainMenuScene, this.board, generationsNumber);
                startAnimationButton.setDisable(false);
            }

        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}