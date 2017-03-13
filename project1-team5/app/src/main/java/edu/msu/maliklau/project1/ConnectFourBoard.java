package edu.msu.maliklau.project1;


import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class ConnectFourBoard {


    /**
     * Most recent relative X touch when dragging
     */
    private float lastRelX;

    /**
     * Most recent relative Y touch when dragging
     */
    private float lastRelY;
    /**
     * This variable is set to a piece we are dragging. If
     * we are not dragging, the variable is null.
     */
    private ConnectPiece dragging = null;

    /**
     * The size of the puzzle in pixels
     */
    private int boardSize;


    /**
     * How much we scale the puzzle pieces
     */
    private float scaleFactor;

    /**
     * Left margin in pixels
     */
    private int marginX;

    /**
     * Top margin in pixels
     */
    private int marginY;

    /**
     * This variable is set to a piece we are dragging. If
     * we are not dragging, the variable is null.
     */
   // private ConnectPiece dragging = null;

   // private Bitmap connectFourComplete;

    /**
     * Collection of puzzle pieces
     */
    public ArrayList<ConnectPiece> pieces = new ArrayList<ConnectPiece>();


    /**
     * Collection of board cells
     */
    public ArrayList<ConnectFourBoardCells> boardCells = new ArrayList<>();

    private int puzzleHeight;
    /**
     * Percentage of the display width or height that
     * is occupied by the puzzle.
     */
    final static float SCALE_IN_VIEW = 0.9f;



    public ConnectFourBoard(Context context) {

       // connects.add(new ConnectFourBoard(context, R.drawable.spartan_green));
        // Load the puzzle pieces
        for (int row = 0; row <= 6; row++) {
            for (int column = 0; column <= 5; column++) {
                boardCells.add(new ConnectFourBoardCells(context,
                        (float) (row + .5) / 7,
                        (float) (column + .5) / 6
                ));
            }
        }

        // Load the puzzle pieces
        pieces.add(new ConnectPiece(context,
                R.drawable.spartan_green,
                0.259f,
                0.238f));


    }


    public void draw(Canvas canvas) {

        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        // Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        boardSize = (int) (minDim * SCALE_IN_VIEW);
        puzzleHeight = (boardSize * 6/7);

        // Compute the margins so we center the puzzle
        marginX = (wid - boardSize) / 2;
        marginY = (hit - puzzleHeight) / 2;

        float FullBoardWidth = 7 * ConnectFourBoardCells.EMPTY_SLOT_WIDTH;
        scaleFactor = (float) boardSize / FullBoardWidth;

        for (ConnectFourBoardCells piece : boardCells) {
            piece.draw(canvas, marginX, marginY, boardSize, scaleFactor);
        }

        for ( ConnectPiece piece : pieces) {
            piece.draw(canvas, marginX, marginY, boardSize, scaleFactor);
        }

    }

    public boolean onTouchEvent(View view, MotionEvent event) {
        //
        // Convert an x,y location to a relative location in the
        // puzzle.
        //
        float relX = (event.getX() - marginX) / boardSize;
        float relY = (event.getY() - marginY) / boardSize;
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                 //Log.i("onTouchEvent", "ACTION_DOWN");
                return onTouched(relX, relY);
            // return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Log.i("onTouchEvent", "ACTION_UP");'
                return onReleased(view, relX, relY);


            case MotionEvent.ACTION_MOVE:
                // Log.i("onTouchEvent",  "ACTION_MOVE: ");
                // If we are dragging, move the piece and force a redraw
                if (dragging != null) {
                    dragging.move(relX - lastRelX, relY - lastRelY);
                    lastRelX = relX;
                    lastRelY = relY;
                    view.invalidate();
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Handle a touch message. This is when we get an initial touch
     * @param x x location for the touch, relative to the puzzle - 0 to 1 over the puzzle
     * @param y y location for the touch, relative to the puzzle - 0 to 1 over the puzzle
     * @return true if the touch is handled
     */
    private boolean onTouched(float x, float y) {

        // Check each piece to see if it has been hit
        // We do this in reverse order so we find the pieces in front
        for(int p=pieces.size()-1; p>=0;  p--) {
            if(pieces.get(p).hit(x, y, boardSize, scaleFactor)) {
                // We hit a piece!
                dragging = pieces.get(p);
                lastRelX = x;
                lastRelY = y;
                return true;
            }
        }

        return false;
    }

    /**
     * Handle a release of a touch message.
     * @param x x location for the touch release, relative to the puzzle - 0 to 1 over the puzzle
     * @param y y location for the touch release, relative to the puzzle - 0 to 1 over the puzzle
     * @return true if the touch is handled
     */
    private boolean onReleased(View view, float x, float y) {

        if(dragging != null) {
            dragging = null;
            return true;
        }

        return false;
    }


    /**
     * Read the board from a bundle
     *
     * @param bundle The bundle we save to
     */
    public void loadInstanceState(Bundle bundle) {
    }

    /**
     * Save the board to a bundle
     *
     * @param bundle The bundle we save to
     */

    public void saveInstanceState(Bundle bundle) {
    }
}


