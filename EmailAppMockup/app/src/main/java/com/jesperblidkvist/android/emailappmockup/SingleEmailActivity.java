package com.jesperblidkvist.android.emailappmockup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Jesper on 2017-07-26.
 */

public class SingleEmailActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_email_activity);

        TextView txtProduct = (TextView) findViewById(R.id.email_label);
        TextView txtBody = (TextView) findViewById(R.id.email_body);

        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("email");
        // displaying selected product name
        txtProduct.setText(product);
        txtBody.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
    }
}
