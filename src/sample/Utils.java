package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {

    private static int width;
    private static int height;

    public static int[][] readBoard( String fileName) throws IOException{

        File textFile=new File(fileName);

        Scanner s=new Scanner(textFile);

        width = Integer.parseInt(s.next());
        height = Integer.parseInt(s.next());



        System.out.println(height + " " + width);

        int [][]tab=new int [height][width];


        for(int i=0;i<width;i++) {
            for(int j=0;j<height;j++) {
                tab[i][j]=Integer.parseInt(s.next());
            }
        }
        s.close();

        return tab;
    }

    public static void writeToFile( String fileName, int[][] t){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(width+" ");
            fileWriter.write(height+"\n");
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


    public static void arrayCopy(int[][] targetArray, int[][] sourceArray) {
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                targetArray[i][j]=sourceArray[i][j];
            }
        }
    }

    public static int returnWidth() {
        return width;
    }

    public static int returnHeight(){
        return height;
    }



}