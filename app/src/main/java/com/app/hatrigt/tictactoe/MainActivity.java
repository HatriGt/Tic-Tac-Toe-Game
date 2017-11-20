package com.app.hatrigt.tictactoe;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    // 0= blue, 1 = red

    boolean gameIsActive = true;

    int activePlayer = 0 ;
    // 2 means unplayed

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropIn(View view){
        ImageView counter = (ImageView) view;



        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive ) {

            gameState[tappedCounter]= activePlayer;
            counter.setTranslationY(-1000f);




            if (activePlayer == 0) {


                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;


            }


            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]]== gameState[winningPosition[1]] &&
                   gameState[winningPosition[1]]== gameState[winningPosition[2]] &&
                   gameState[winningPosition[0]] != 2)     {

                    //sm1 has won
                    String winner = "Red";
                    gameIsActive = false;

                    if((gameState[winningPosition[0]]== 0) ){

                        winner = "Blue";

                    }



                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText( winner + " Has Won ");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);


                }else{
                    boolean gameIsOver = true ;
                    for (int counterState : gameState){
                        if(counterState == 2) gameIsOver = false;
                    }
                    if(gameIsOver){

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText( " Its A Draw ");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }


            }


        }
    }

    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);


        activePlayer = 0 ;


        for (int i=0 ; i<gameState.length; i++){

            gameState[i]=2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);

        for(int i=0 ; i<gridLayout.getChildCount();i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
