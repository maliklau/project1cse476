package edu.msu.maliklau.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.activity_main);
//
    }

    /**
     * Handle a Picture button press
     *
     * @param view
     */
    public void onStart(View view) {
        EditText ID1 = (EditText) findViewById(R.id.userOneText);
        EditText ID2 = (EditText) findViewById(R.id.userTwoText);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("location", ID1.getText().toString());
        intent.putExtra("location1", ID2.getText().toString());
        startActivity(intent);
    }



}