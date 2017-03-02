package edu.msu.maliklau.project1;


import android.content.Context;

import android.graphics.Canvas;


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

   // private Bitmap connectFourComplete;



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

    /**
     * Collection of puzzle pieces
     */
    public ArrayList<ConnectFourBoard> connects = new ArrayList<ConnectFourBoard>();

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
        float scaleFactor = boardSize / FullBoardWidth;


        for (ConnectFourBoardCells piece : boardCells) {
            piece.draw(canvas, marginX, marginY, boardSize, scaleFactor);
        }
    }


}


