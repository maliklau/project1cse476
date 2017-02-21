package edu.msu.maliklau.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Lauren on 2/21/17.
 */

public class EndActivity extends ActionBarActivity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.end_activity);

    }

    public void onMainMenu(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
