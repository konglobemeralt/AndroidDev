package com.jesperblidkvist.android.androidlab_phase_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    SimpleBookManager mSimpleBookManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mSimpleBookManger = new SimpleBookManager();


    }
}
