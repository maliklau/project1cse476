package edu.msu.maliklau.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
//    private GridView gridView;
//    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.activity_main);
//        gridView = (GridView) findViewById(R.id.gridView);
//        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
//        gridView.setAdapter(gridAdapter);
    }
    /**
     * Handle a Picture button press
     * @param view
     */
    public void onStart(View view) {
        EditText ID1=(EditText) findViewById(R.id.userOneText);

        Intent intent = new Intent(this, grid.class);
        intent.putExtra("location", ID1.getText().toString());
        startActivity(intent);
    }

    /**
     * Prepare some dummy data for gridview
     */
//    private ArrayList<ImageItem> getData() {
//        final ArrayList<ImageItem> imageItems = new ArrayList<>();
//        for (int i = 0; i < 42; i++) {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slot);
//            imageItems.add(new ImageItem(bitmap, "Image#" + i));
//        }
//        return imageItems;
//    }
}