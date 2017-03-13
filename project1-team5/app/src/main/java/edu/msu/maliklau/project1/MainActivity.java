package edu.msu.maliklau.project1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String PLAYERONENAME = "PlayerOneName";
    public static final String PLAYERTWONAME = "PlayerTwoName";
    public  String playerOne = "Player 1";
    public  String playerTwo = "Player 2";

    @BindView(R.id.userOne)
    ImageView userOneIV;

    @BindView(R.id.userTwo)
    ImageView userTwoIV;

    @BindView(R.id.playerOne)
    TextView playerOneTV;

    @BindView(R.id.playerTwo)
    TextView playerTwoTV;


    @OnClick(R.id.userOne)
    public void userOneIBClicked() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialogue_user_name, null);
        final EditText nameEdit = (EditText) dialogView.findViewById(R.id.name);
        nameEdit.setText("Player 1");

        nameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdit.setCursorVisible(true);
            }
        });


        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Customize your name :");
        dialogBuilder
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name = nameEdit.getText().toString();
                        playerOneTV.setText(name);
                        if (name != null) {
                            playerOne = name;
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    @OnClick(R.id.userTwo)
    public void userTwoIBClicked() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialogue_user_name, null);


        final EditText nameEdit = (EditText) dialogView.findViewById(R.id.name);
        nameEdit.setText("Player 2");


        nameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdit.setCursorVisible(true);
            }
        });


        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Customize your name :");
        dialogBuilder
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name = nameEdit.getText().toString();
                        playerTwoTV.setText(name);
                        if (name != "") {
                            playerTwo = name;
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    @OnClick(R.id.howTo)
    public void onHowToClick() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogHowTo = inflater.inflate(R.layout.dialogue_how_to, null);
        dialogBuilder.setView(dialogHowTo);
        dialogBuilder.setTitle("LEARN HOW TO PLAY THE GAME!");
        dialogBuilder
                .setCancelable(false)
                .setNegativeButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    @BindView(R.id.playButton)
    Button playButton;

    @OnClick(R.id.playButton)
    public void onPlayClick() {
        newGame();


    }

    public void newGame(){
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(PLAYERONENAME, playerOne);
        bundle.putString(PLAYERTWONAME, playerTwo);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerOneTV.setText(savedInstanceState.getString(PLAYERONENAME));
        playerTwoTV.setText(savedInstanceState.getString(PLAYERTWONAME));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PLAYERONENAME, playerOneTV.getText().toString());
        outState.putString(PLAYERTWONAME, playerTwoTV.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.msu.maliklau.project1.R.layout.activity_main);
        ButterKnife.bind(this);
//        checkForUpdates();

    }
/*--------------HockeyApp--------------------------------------------
        @Override
        public void onResume() {
            super.onResume();
            checkForCrashes();
        }

        @Override
        public void onPause() {
            super.onPause();
            unregisterManagers();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            unregisterManagers();
        }

        private void checkForCrashes() {
            CrashManager.register(this);
        }

        private void checkForUpdates() {
            UpdateManager.register(this);
        }

        private void unregisterManagers() {
            UpdateManager.unregister();
        }
    --------------------------------------------------------------------*/


}