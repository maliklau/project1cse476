package edu.msu.maliklau.project1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ConnectFourBoardCells {

    private float X;
    private float Y;
    private Bitmap emptySlot;

    public static float EMPTY_SLOT_WIDTH;
    public static float EMPTY_SLOT_HEIGHT;


    public ConnectFourBoardCells(Context context, float X, float Y) {
        this.X = X;
        this.Y = Y;
        this.emptySlot = BitmapFactory.decodeResource(context.getResources(), R.drawable.slot);

        EMPTY_SLOT_WIDTH = emptySlot.getWidth();
        EMPTY_SLOT_HEIGHT = emptySlot.getHeight();

    }


    public void draw(Canvas canvas, int marginX, int marginY,
                     int boardSize, float scaleFactor) {
        canvas.save();

        // Convert x,y to pixels and add the margin, then draw
        canvas.translate(marginX + X * boardSize, marginY + Y * boardSize * 6 / 7.0f);

        // Scale it to the right size
        canvas.scale(scaleFactor, scaleFactor);

        // This magic code makes the center of the piece at 0, 0
        canvas.translate(-emptySlot.getWidth() / 2, -emptySlot.getHeight() / 2);

        // Draw the bitmap
        canvas.drawBitmap(emptySlot, 0, 0, null);
        canvas.restore();
    }


}
