package edu.msu.maliklau.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Savanna on 2/27/2017.
 */

public class ConnectFourView extends View{

    private ConnectFourBoard board;

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





}