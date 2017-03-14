package edu.msu.maliklau.project1;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
     * How much we scale the puzzle pieces
     */
    private int turn = 1;

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

    public int row1[] = {0, 0, 0, 0, 0, 0, 0};

    public int[][] disk = new int[7][6];
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


    private Context text;

    public ConnectFourBoard(Context context) {

        // connects.add(new ConnectFourBoard(context, R.drawable.spartan_green));
        // Load the puzzle pieces
        for (int row = 0; row <= 6; row++) {
            for (int column = 0; column <= 5; column++) {
                boardCells.add(new ConnectFourBoardCells(context,
                        (float) (row + .5) / 7,
                        (float) (column + .5) / 6
                ));
                disk[row][column] = 0;
            }
        }

        //    callmessage(context);
        // Load the puzzle pieces
        pieces.add(new ConnectPiece(context,
                R.drawable.spartan_green,
                0.259f,
                0.238f));

        text = context;
    }

    public void callmessage(Context context) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("User 1 turn!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void callmessage1(Context context) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("User 2 turn!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public Canvas canvasOne;

    public void draw(Canvas canvas) {


        canvasOne = canvas;
        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        // Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        boardSize = (int) (minDim * SCALE_IN_VIEW);
        puzzleHeight = (boardSize * 6 / 7);

        // Compute the margins so we center the puzzle
        marginX = (wid - boardSize) / 2;
        marginY = (hit - puzzleHeight) / 2;

        float FullBoardWidth = 7 * ConnectFourBoardCells.EMPTY_SLOT_WIDTH;
        scaleFactor = (float) boardSize / FullBoardWidth;

        for (ConnectFourBoardCells piece : boardCells) {
            piece.draw(canvas, marginX, marginY, boardSize, scaleFactor);
        }

        for (ConnectPiece piece : pieces) {
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
     *
     * @param x x location for the touch, relative to the puzzle - 0 to 1 over the puzzle
     * @param y y location for the touch, relative to the puzzle - 0 to 1 over the puzzle
     * @return true if the touch is handled
     */
    private boolean onTouched(float x, float y) {

        // Check each piece to see if it has been hit
        // We do this in reverse order so we find the pieces in front
        for (int p = pieces.size() - 1; p >= 0; p--) {
            if (pieces.get(p).hit(x, y, boardSize, scaleFactor)) {
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
     * Checks horizontal win
     */
    private boolean checkhorizontal(int x, int y) {
        int count;
        int row = x;
        int column = y;
        count = 0;
        for (int i = 0; i <= 2; i++) {
            if (row >= 1) {
                row = row - 1;
                if (disk[row][column] == disk[x][y]) {
                    count += 1;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        count = 0;
        row = x;
        column = y;
        for (int i = 0; i <= 2; i++) {
            if (row <= 5) {
                row = row + 1;
                if (disk[row][column] == disk[x][y]) {
                    count += 1;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    /**
     * Checks vertical win
     */
    private boolean checkvertical(int x, int y) {
        int count;
        int row = x;
        int column = y;
        count = 0;
        for (int i = 0; i <= 2; i++) {
            if (column >= 1) {
                column = column - 1;
                if (disk[row][column] == disk[x][y]) {
                    count += 1;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        count = 0;
        row = x;
        column = y;
        for (int i = 0; i <= 2; i++) {
            if (column <= 4) {
                column = column + 1;
                if (disk[row][column] == disk[x][y]) {
                    count += 1;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    /**
     * Checks diagonal win
     */
    private boolean checkdiagonal(int x, int y) {
        int count;
        int row = x;
        int column = y;
        count = 0;
        for (int i = 0; i <= 2; i++) {
            if (column >= 1 && row >= 1) {
                column = column - 1;
                row = row - 1;
                if (disk[row][column] == disk[x][y]) {
                    count += 1;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        count = 0;
        row = x;
        column = y;
        for (int i = 0; i <= 2; i++) {
            if (column <= 4 && row <= 5) {
                column = column + 1;
                row = row + 1;
                if (disk[row][column] == disk[x][y]) {
                    count += 1;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        count = 0;
        row = x;
        column = y;
        for (int i = 0; i <= 2; i++) {
            if (column >= 1 && row <= 5) {
                column = column - 1;
                row = row + 1;
                if (disk[row][column] == disk[x][y]) {
                    count += 1;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        count = 0;
        row = x;
        column = y;
        for (int i = 0; i <= 2; i++) {
            if (column <= 4 && row >= 1) {
                column = column + 1;
                row = row - 1;
                if (disk[row][column] == disk[x][y]) {
                    count += 1;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    /**

     */
    public int getrow;
    private boolean onReleased(View view, float x, float y) {
        int row;
        row = (int) ((x + 0.03) * 7 - 0.5);
        row1[row] += 1;
        getrow=row;
        if (row1[row] >= 7) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(text);
            builder1.setMessage("Invalid Move");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Continue",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            return false;
        }
        int column = 5 - row1[row];
        int column1 = column + 1;
        disk[row][column1] = turn;

        float Y = (float) (column + .5) / 6;
        Y += .010 + 0.025 * row1[row];
        dragging.setX((float) (row + .5) / 7);
        dragging.setY(Y);
        boolean result = false;
        result = checkhorizontal(row, column1);
        if (result == true) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(text);
            builder1.setMessage("Horizontal win for the Player " + turn);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "continue",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            //maybe add a call from the game activity to go to the en activity, we need to switch the game activity to the end activity
            return false;
        }
        result = checkvertical(row, column1);
        if (result == true) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(text);
            builder1.setMessage("Vertical win for the Player " + turn);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "continue",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            return false;
        }
        result = checkdiagonal(row, column1);
        if (result == true) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(text);
            builder1.setMessage("Diagonal win for the Player " + turn);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "continue",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

            return false;
        }

        //connectSwitch();


        // dragging.setX(marginX + X * boardSize);
        // dragging.setY(marginY + Y * boardSize * 6 / 7.0f);

        if (dragging != null) {
            dragging = null;
            return true;
        }

        return false;
    }

    public void connectSwitch() {
        if (turn == 1) {
            pieces.add(new ConnectPiece(text,
                    R.drawable.spartan_white,
                    0.259f,
                    0.238f));
            turn = 2;

        } else {
            pieces.add(new ConnectPiece(text,
                    R.drawable.spartan_green,
                    0.259f,
                    0.238f));
            turn = 1;

        }

    }

    public void restoreConnect() {
        if (turn == 1) {
            pieces.add(new ConnectPiece(text,
                    R.drawable.spartan_green,
                    0.259f,
                    0.238f));
            turn = 1;

        } else {
            pieces.add(new ConnectPiece(text,
                    R.drawable.spartan_white,
                    0.259f,
                    0.238f));
            turn = 2;

        }

    }


    public void undomove() {

        row1[getrow]-=1;
        pieces.remove(pieces.size() - 1);
        restoreConnect();


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


