package com.nithya.tictactoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    int activePlayer = 0;

    public void dropIn(View view) {
        ImageView button = (ImageView) view;
        int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        int tappedButton = Integer.parseInt(button.getTag().toString());

        if (gameState[tappedButton] == -1) {
            gameState[tappedButton] = activePlayer;
            if (activePlayer == 0) {
                button.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                button.setImageResource(R.drawable.blue);
                activePlayer = 0;
            }

            button.setTranslationY(-100f);
            button.animate().translationYBy(100f).setDuration(500);
            System.out.println(tappedButton);

            int[][] winningSequence = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

            String winner = "";
            for (int[] winningSeq : winningSequence) {
                if (gameState[winningSeq[0]] == gameState[winningSeq[1]]
                        && gameState[winningSeq[1]] == gameState[winningSeq[2]]
                        && gameState[winningSeq[0]] != -1) {

                    winner = "red";
                    if (gameState[winningSeq[0]] == 0)
                        winner = "blue";


                    TextView winnerText = (TextView) findViewById(R.id.winMessage);
                    winnerText.setText(winner + "is the winner!");

                    LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                    winnerLayout.setVisibility(View.VISIBLE);
                }
            }

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
