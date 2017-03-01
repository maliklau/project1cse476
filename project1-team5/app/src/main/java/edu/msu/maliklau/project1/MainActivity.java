package edu.msu.maliklau.project1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText playerOne;
    EditText playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.activity_main);

        playerOne = (EditText) findViewById(R.id.userOneText);
        playerTwo = (EditText) findViewById(R.id.userTwoText);

    }

    public void onPlay(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(!Objects.equals(playerOne.getText().toString(), "") && (!Objects.equals(playerTwo.getText().toString(), ""))){
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("location", playerOne.getText().toString());
                intent.putExtra("location1", playerTwo.getText().toString());
                startActivity(intent);
            }
        }
    }

    public void onHowToPlay(View view){
        Intent intent = new Intent(this, HowToPlayActivity.class);
        startActivity(intent);
    }

}