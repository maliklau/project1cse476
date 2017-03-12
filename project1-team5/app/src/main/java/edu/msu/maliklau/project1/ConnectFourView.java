package edu.msu.maliklau.project1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Savanna on 2/27/2017.
 */

public class ConnectFourView extends View{

    private ConnectFourBoard board;

    public final static String PLAYING_AREA = "playingArea";
    public final static String CONNECT_BANK = "connectBank";
    public final static String PARAMETERS = "parameters";

    public ConnectFourView(Context context) {
        super(context);
        init(null, 0);
    }

    public ConnectFourView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ConnectFourView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        board = new ConnectFourBoard(getContext());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        board.draw(canvas);
    }

    private class Touch {
        public float dX = 0;

        public float dY = 0;

        public int id = -1;

        public float x = 0;

        public float y = 0;

        public float lastX = 0;

        public float lastY = 0;

        public void copyToLast() {
            lastX = x;
            lastY = y;
        }

        public void computeDeltas() {
            dX = x - lastX;
            dY = y - lastY;
        }
    }


    /**
     * Save the puzzle to a bundle
     * @param bundle The bundle we save to
     */
    public void saveInstanceState(Bundle bundle) {
        board.saveInstanceState(bundle);
    }


    /**
     * Load the puzzle from a bundle
     * @param bundle The bundle we save to
     */
    public void loadInstanceState(Bundle bundle) {
        board.loadInstanceState(bundle);
    }

    public ConnectFourBoard getConnectFourBoard() {
        return board;
    }



}