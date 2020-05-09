package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {

    public static int[][] readBoard(int width, int height, String fileName) throws IOException{

        File textFile=new File(fileName);

        int [][]tab=new int [height][width];

        Scanner s=new Scanner(textFile);

        for(int i=0;i<width;i++) {
            for(int j=0;j<height;j++) {
                tab[i][j]=Integer.parseInt(s.next());
            }
        }
        s.close();

        return tab;
    }

    public static void writeToFile(int width, int height, String fileName, int[][] t){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    fileWriter.write(t[i][j]+" ");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public static void arrayCopy(int[][] targetArray, int[][] sourceArray, int width, int height) {
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                targetArray[i][j]=sourceArray[i][j];
            }
        }
    }



}