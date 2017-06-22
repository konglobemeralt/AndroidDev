package com.jesperblidkvist.android.thirty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.jesperblidkvist.android.thirty.model.ThirtyGame;

public class ThirtyActivity extends AppCompatActivity {

    private ThirtyGame game;
    private Button mRollDiceButton;

    private ImageButton mDice1;
    private ImageButton mDice2;
    private ImageButton mDice3;
    private ImageButton mDice4;
    private ImageButton mDice5;
    private ImageButton mDice6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirty);

        initDices();

        mRollDiceButton = (Button) findViewById(R.id.rollButton);
        mRollDiceButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                game.rollDices();
                updateDices();
                Log.d("ThirtyActivity", game.toString());
            }
        });


        game = new ThirtyGame();
    }

    private void initDices(){
        mDice1 = (ImageButton) findViewById(R.id.diceButton1);
        mDice1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Log.d("ThirtyActivity", "Selected Dice1");
            }
        });

        mDice2 = (ImageButton) findViewById(R.id.diceButton2);
        mDice2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Log.d("ThirtyActivity", "Selected Dice2");
            }
        });

        mDice3 = (ImageButton) findViewById(R.id.diceButton3);
        mDice3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Log.d("ThirtyActivity", "Selected Dice3");
            }
        });

        mDice4 = (ImageButton) findViewById(R.id.diceButton4);
        mDice4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Log.d("ThirtyActivity", "Selected Dice4");
            }
        });

        mDice5 = (ImageButton) findViewById(R.id.diceButton5);
        mDice5.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Log.d("ThirtyActivity", "Selected Dice5");
            }
        });

        mDice6 = (ImageButton) findViewById(R.id.diceButton6);
        mDice6.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Log.d("ThirtyActivity", "Selected Dice6");
            }
        });


    }

    private void updateDices(){
        //TODO: Fancy mapping instead of hardcoding
        mDice1.setImageResource(getResources().getIdentifier("dice_white"+ game.getDiceValueAtIndex(0), "mipmap", getPackageName()));
        mDice2.setImageResource(getResources().getIdentifier("dice_white"+ game.getDiceValueAtIndex(1), "mipmap", getPackageName()));
        mDice3.setImageResource(getResources().getIdentifier("dice_white"+ game.getDiceValueAtIndex(2), "mipmap", getPackageName()));
        mDice4.setImageResource(getResources().getIdentifier("dice_white"+ game.getDiceValueAtIndex(3), "mipmap", getPackageName()));
        mDice5.setImageResource(getResources().getIdentifier("dice_white"+ game.getDiceValueAtIndex(4), "mipmap", getPackageName()));
        mDice6.setImageResource(getResources().getIdentifier("dice_white"+ game.getDiceValueAtIndex(5), "mipmap", getPackageName()));
    }
}
