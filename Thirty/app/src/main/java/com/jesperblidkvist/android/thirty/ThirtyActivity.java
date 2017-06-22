package com.jesperblidkvist.android.thirty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jesperblidkvist.android.thirty.model.ThirtyGame;

public class ThirtyActivity extends AppCompatActivity {

    private ThirtyGame game;
    private Button mRollDiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirty);

        mRollDiceButton = (Button) findViewById(R.id.rollButton);
        mRollDiceButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                game.rollDices();
                Log.d("ThirtyActivity", game.toString());
            }
        });
        game = new ThirtyGame();
    }
}
