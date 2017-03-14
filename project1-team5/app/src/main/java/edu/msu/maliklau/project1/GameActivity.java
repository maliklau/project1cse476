package edu.msu.maliklau.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GameActivity extends AppCompatActivity {

//    @BindView(R.id.currentPlayer)
    TextView currentPlayerTV;



    @BindView(R.id.forfeit)
    FloatingActionButton forfeitFAB;

    String playerOneName;
    String playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.activity_game);
        ButterKnife.bind(this);
        currentPlayerTV = (TextView) findViewById(R.id.currentPlayer);

        ((ConnectFourView) findViewById(R.id.gameView)).startGame();

        if (savedInstanceState != null) {
            getConnectFourView().loadState(savedInstanceState);
        } else {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            playerOneName = bundle.getString(MainActivity.PLAYERONENAME);
            playerTwoName = bundle.getString(MainActivity.PLAYERTWONAME);


        }
        updateUI();
    }

    @OnClick(R.id.undo)
    public void onUndoFABClick() {

        getConnectFourBoard().undomove();
        getConnectFourView().redrawBoard();
       // getConnectFourBoard().draw(Canvas canvas);



    }

    @OnClick(R.id.forfeit)
    public void onForfeitFABClick() {

        Intent intent = new Intent(this, EndActivity.class);
        Bundle bundle = new Bundle();
        if (getConnectFourView().isPlayerOneTurn()) {
            bundle.putString(EndActivity.WINNER, getConnectFourView().getPlayerTwo().getName());
            bundle.putString(EndActivity.LOSER, getConnectFourView().getPlayerOne().getName());
        } else {
            bundle.putString(EndActivity.WINNER, getConnectFourView().getPlayerOne().getName());
            bundle.putString(EndActivity.LOSER, getConnectFourView().getPlayerTwo().getName());
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.done)
    public void onDoneFABClick() {

       getConnectFourView().done();
       getConnectFourBoard().connectSwitch();
        updateUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getConnectFourView().saveState(bundle);
    }

    ConnectFourView getConnectFourView() {
        return (ConnectFourView) findViewById(R.id.gameView);
    }
    ConnectFourBoard getConnectFourBoard() {
       return getConnectFourView().getConnectFourBoard();
   }

    private void updateUI() {
        if (getConnectFourView().isPlayerOneTurn()) {
            currentPlayerTV.setText(getConnectFourView().getPlayerOne().getName() + ", it is your turn!");

        } else {
            currentPlayerTV.setText(getConnectFourView().getPlayerTwo().getName() + ", it is your turn!");

        }


    }
}
