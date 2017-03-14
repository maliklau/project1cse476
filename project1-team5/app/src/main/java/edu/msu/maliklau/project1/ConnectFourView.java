package edu.msu.maliklau.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;

/**
 * Created by Savanna on 2/27/2017.
 */


public class ConnectFourView extends View {

    public final static String BOARD_SIZE = "edu.msu.maliklau.project1.BOARD_SIZE";
    public final static String CONNECT_FOUR_BOARD = "playingArea";

    private Parameters params = null;

    private ConnectFourBoard connectBoard = null;


    private ConnectFourBoard board = null;

    private Context context;


    public final static String PARAMETERS = "parameters";

    public ConnectFourView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ConnectFourView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ConnectFourView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context con, AttributeSet attrs, int defStyle) {
        context = con;
        board = new ConnectFourBoard(getContext());
    }

    public void startGame() {
        params = new Parameters(((GameActivity) context).getIntent().getStringExtra(MainActivity.PLAYERONENAME), ((GameActivity) context).getIntent().getStringExtra(MainActivity.PLAYERTWONAME));

    }

    public boolean isInitialized() {
        return board != null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        board.draw(canvas);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        return board.onTouchEvent(this, event);
    }


    /**
     * Save the puzzle to a bundle
     *
     * @param bundle The bundle we save to
     */
    public void saveInstanceState(Bundle bundle) {
        board.saveInstanceState(bundle);
        bundle.putSerializable(PARAMETERS, params);
    }


    /**
     * Load the puzzle from a bundle
     *
     * @param bundle The bundle we save to
     */
    public void loadState(Bundle bundle) {
        board.loadInstanceState(bundle);
        params = (Parameters) bundle.getSerializable(PARAMETERS);

    }

    public ConnectFourBoard getConnectFourBoard() {
        return board;
    }


    public boolean isPlayerOneTurn() {
        return params.playerOneTurn;
    }

    public boolean isPlayerTwoTurn() {
        return params.playerTwoTurn;
    }

    public void switchTurn() {
        if (isPlayerOneTurn()) {
            params.playerOneTurn = false;
            params.playerTwoTurn = true;
        } else {
            params.playerOneTurn = true;
            params.playerTwoTurn = false;
        }
    }


    public void saveState(Bundle bundle) {
        bundle.putSerializable(PARAMETERS, params);
    }

    public Player getPlayerOne() {
        return params.playerOne;
    }

    public Player getPlayerTwo() {
        return params.playerTwo;
    }


    public void setPlayers(Player playerOne, Player playerTwo) {

        params.playerOne = playerOne;
        params.playerTwo = playerTwo;
    }

    public void done() {

        switchTurn();
        invalidate();
    }


    private static class Parameters implements Serializable {


        public Parameters(String playeronename, String playertwoname) {

            playerOne = new Player(playeronename);
            playerTwo = new Player(playertwoname);
        }

        //public boardSize;
        public boolean playerOneTurn = true;
        public boolean playerTwoTurn = false;

        public Player playerOne;
        public Player playerTwo;

    }
}
