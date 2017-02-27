package edu.msu.maliklau.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.GridView;

public class grid extends ActionBarActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.activity_grid);
        gridView = (GridView) findViewById(edu.msu.maliklau.project1.R.id.gridView);
        gridAdapter = new GridViewAdapter(this, edu.msu.maliklau.project1.R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
        Intent in = getIntent();
        String tv1= in.getExtras().getString("location");
        textView1.setText(tv1);
    }

    public void onStart(View view) {

        Intent intent = new Intent(this, grid.class);
        startActivity(intent);
    }

    public void onDone(View view) {

        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }

    public void onSurrender(View view) {

        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }
    /**
     * Prepare some dummy data for gridview
     */
    private ArrayList<ImageItem> getData() {



        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        for (int i = 0; i < 42; i++) {

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), edu.msu.maliklau.project1.R.drawable.slot);





            imageItems.add(new ImageItem(bitmap, "Image#" + i) );

        // use scalng, once we find the sides we can get the coordinates, the for loop can branch from i zero to 6 and j from 0 to 7, ixside, jxside
        }
        return imageItems;
    }
}
