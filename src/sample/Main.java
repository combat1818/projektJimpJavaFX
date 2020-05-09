package sample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {


    private String pathToFile;
    private int generationsNumber;
    private boolean isPathSet=false;
    private boolean isGenerationNumberSet=false;


    private Button startAnimationButton;
    private Board board=new Board();



    private Stage primaryStage;
    private Scene mainMenuScene;
    private AnimationScene AnimationScene;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        Data.window=primaryStage;
        //this.primaryStage=primaryStage;
        this.mainMenuScene = new Scene(root, 800, 800);

        //Hello World Button
        Button helloWorldBtn = new Button("Hello world");
        helloWorldBtn.setOnAction(e-> {Data.n++; System.out.println("Helo World"+Data.n);});

        //Start Animation Button
        this.startAnimationButton=new Button("Start Animation");
        startAnimationButton.setOnAction(e-> {
            Data.window.setScene(this.AnimationScene);
            this.AnimationScene.startAnimation();
        });
        startAnimationButton.setLayoutX(100);
        startAnimationButton.setDisable(true);



        //InputField for Path to File
        TextField pathToFileField = new TextField();
        pathToFileField.setLayoutY(100);
        Button confirmPathToFileButton=new Button("Confirm Path");
        confirmPathToFileButton.setOnAction(e->{
            setPath(pathToFileField.getText());
            pathToFileField.setText("");
        });
        confirmPathToFileButton.setLayoutY(130);


        //Input Field for GenerationsNumber
        TextField generationsNumberField = new TextField();
        generationsNumberField.setLayoutY(200);
        Button confirmGenerationsNumberButton=new Button("Confirm Number");
        confirmGenerationsNumberButton.setOnAction(e->{
            setGenerationsNumber(generationsNumberField.getText());
            generationsNumberField.setText("");
        });
        confirmGenerationsNumberButton.setLayoutY(230);



        root.getChildren().addAll(helloWorldBtn,startAnimationButton,pathToFileField,confirmPathToFileButton,
                generationsNumberField, confirmGenerationsNumberButton);

        primaryStage.setTitle("Hello World!");
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

    private void startAnimation(){
        isPathSet=false;
        isGenerationNumberSet=false;
        try{
            this.board.initialState=Utils.readBoard(70,70,this.pathToFile);
            Utils.arrayCopy(this.board.previousState,this.board.initialState,70,70);
            Utils.arrayCopy(this.board.actualState,this.board.initialState,70,70);

            AnimationScene=new AnimationScene(new Pane(),this.primaryStage,this.mainMenuScene, this.board, generationsNumber);
            startAnimationButton.setDisable(false);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}