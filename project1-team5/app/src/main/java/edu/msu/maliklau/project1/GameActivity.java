package edu.msu.maliklau.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.activity_game);
        Intent in = getIntent();
        String tv1 = in.getExtras().getString("location");
        ((TextView) findViewById(R.id.userOneData)).setText(tv1);
        Intent in1 = getIntent();
        String tv2 = in1.getExtras().getString("location1");
        ((TextView) findViewById(R.id.userTwoData)).setText(tv2);
    }


    //ON SURRENDER PUBLIC VOID
    //ONDONE        PUBLIC VOID


    public void OnUndo(View view){

        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }

    public void onSurrender(View view) {

        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }

    public void onDone(View view) {

        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }




}
