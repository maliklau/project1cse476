package edu.msu.maliklau.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Lauren on 2/21/17.
 */

public class EndActivity extends AppCompatActivity{

    public final static String WINNER = "edu.msu.maliklau.project1.WINNER";
    public final static String LOSER = "edu.msu.maliklau.project1.LOSER";
    public String winner = "";
    public String loser = "";

    @BindView(R.id.game_over_text)
    TextView gameOverTextTV;

    @BindView(R.id.loser)
    TextView loserTV;

    @BindView(R.id.winner)
    TextView winnerTV;

    @Override
    public void onBackPressed(){
        onBackToMenu();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.end_activity);
        ButterKnife.bind(this);
        if(savedInstanceState != null) {
            winner = savedInstanceState.getString(WINNER);
            loser = savedInstanceState.getString(LOSER);
        }else{
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            winner = bundle.getString(WINNER);
            loser = bundle.getString(LOSER);
        }
        winnerTV.setText(winner + ", you win!");
        loserTV.setText(loser + ", you lose!");
    }







    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState);
        outState.putString(WINNER, winner);
        outState.putString(LOSER, loser);
    }

    @BindView(R.id.back_to_menu)
    Button back_to_menu;

    @OnClick(R.id.back_to_menu)
    public void onBackToMenu() {
        Intent intent = new Intent(EndActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
