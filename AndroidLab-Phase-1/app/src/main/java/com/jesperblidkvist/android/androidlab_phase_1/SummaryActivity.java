package com.jesperblidkvist.android.androidlab_phase_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SummaryActivity extends AppCompatActivity {

    SimpleBookManager mSimpleBookManger;

    TextView mTotalPrice;
    TextView mMostExpensive;
    TextView mLeastExpensive;
    TextView mAveragePrice;
    TextView mBookCounterString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        mSimpleBookManger = new SimpleBookManager();

        mTotalPrice = (TextView) findViewById(R.id.averagePriceString);
        mTotalPrice.setText(mSimpleBookManger.getTotalCost());

        mMostExpensive = (TextView) findViewById(R.id.mostExpensiveItemString);
        mMostExpensive.setText(mSimpleBookManger.getMaxPrice());

        mLeastExpensive = (TextView) findViewById(R.id.leastExpensiveItemString);
        mLeastExpensive.setText(mSimpleBookManger.getMinPrice());


    }
}
