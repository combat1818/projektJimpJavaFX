package sample;

public class Board {
    public int [][] initialState;
    public int [][] previousState;
    public int [][] actualState;

    public Board(int height,int width){
        this.initialState=new int [height][width];
        this.previousState=new int [height][width];
        this.actualState=new int [height][width];
    }

    public void calcNextGeneration(int[][] previousState, int[][] actualState, int width, int height) {

        int count;
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                count=0;

                if(i==0 && j==0) {

                    if(previousState[i][j+1]==1) count++;
                    if(previousState[i+1][j]==1) count++;
                    if(previousState[i+1][j+1]==1) count++;
                }else if(i==height-1 && j==width-1) {
                    if(previousState[i-1][j-1]==1) count++;
                    if(previousState[i-1][j]==1) count++;
                    if(previousState[i][j-1]==1) count++;
                }else if(i==0 && j==width-1) {
                    if(previousState[i][j-1]==1) count++;
                    if(previousState[i+1][j-1]==1) count++;
                    if(previousState[i+1][j]==1) count++;
                }else if(i==height-1 && j==0) {
                    if(previousState[i][j+1]==1) count++;
                    if(previousState[i-1][j]==1) count++;
                    if(previousState[i][j+1]==1) count++;
                }else if(i>0 && j>0 && i<height-1 && j<width-1) {

                    if(previousState[i-1][j-1]==1) count++;
                    if(previousState[i-1][j]==1) count++;
                    if(previousState[i-1][j+1]==1) count++;

                    if(previousState[i][j-1]==1) count++;
                    if(previousState[i][j+1]==1) count++;


                    if(previousState[i+1][j-1]==1) count++;
                    if(previousState[i+1][j]==1) count++;
                    if(previousState[i+1][j+1]==1) count++;
                }else if(i==0 && j>0) {

                    if(previousState[i][j-1]==1) count++;
                    if(previousState[i][j+1]==1) count++;

                    if(previousState[i+1][j-1]==1) count++;
                    if(previousState[i+1][j]==1) count++;
                    if(previousState[i+1][j+1]==1) count++;
                }else if(i==height-1 && j>0) {

                    if(previousState[i-1][j-1]==1) count++;
                    if(previousState[i-1][j]==1) count++;
                    if(previousState[i-1][j+1]==1) count++;

                    if(previousState[i][j-1]==1) count++;
                    if(previousState[i][j+1]==1) count++;
                }else if(i>0 && j==0) {
                    if(previousState[i-1][j+1]==1) count++;
                    if(previousState[i][j+1]==1) count++;
                    if(previousState[i+1][j+1]==1) count++;

                    if(previousState[i-1][j]==1) count++;
                    if(previousState[i+1][j]==1) count++;
                }else if(i>0 && j==width-1) {
                    if(previousState[i-1][j-1]==1) count++;
                    if(previousState[i][j-1]==1) count++;
                    if(previousState[i+1][j-1]==1) count++;

                    if(previousState[i-1][j]==1) count++;
                    if(previousState[i+1][j]==1) count++;
                }


                if(previousState[i][j]==0) actualState[i][j]=0;
                else if(previousState[i][j]==1) actualState[i][j]=2;
                else if(previousState[i][j]==2) actualState[i][j]=3;
                else if(count==1 || count==2) actualState[i][j]=1;
                else actualState[i][j]=3;
    				/*
						Pusta -0 czarny,
						G³owa elektronu -1 niebkieski,
						Ogon elektrony- 2 czerwony,
						Przewodnik- 3 ¿ó³ty.


						Komórka pozostaje Pusta, jeœli by³a Pusta.
						Komórka staje siê Ogonem elektronu, jeœli by³a G³ow¹ elektronu.
						Komórka staje siê Przewodnikiem, jeœli by³a Ogonem elektronu.
						Komórka staje siê G³ow¹ elektronu tylko wtedy, gdy dok³adnie 1 lub 2 s¹siaduj¹ce komórki s¹ G³owami Elektronu.
						Komórka staje siê Przewodnikiem w ka¿dym innym wypadku.
    				 */
            }
        }
     //   return actualState;
    }

}
