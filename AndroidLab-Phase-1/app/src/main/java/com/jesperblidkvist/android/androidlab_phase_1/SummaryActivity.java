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

        mTotalPrice = (TextView) findViewById(R.id.totalCostString);
        mTotalPrice.setText(Integer.toString(mSimpleBookManger.getTotalCost()));

        mMostExpensive = (TextView) findViewById(R.id.mostExpensiveItemString);
        mMostExpensive.setText(Integer.toString(mSimpleBookManger.getMaxPrice()));

        mLeastExpensive = (TextView) findViewById(R.id.leastExpensiveItemString);
        mLeastExpensive.setText(Integer.toString(mSimpleBookManger.getMinPrice()));

        mAveragePrice = (TextView) findViewById(R.id.averagePriceString);
        mAveragePrice.setText(Float.toString(mSimpleBookManger.getMeanPrice()));

        mBookCounterString = (TextView) findViewById(R.id.bookCountString);
        mBookCounterString.setText(mSimpleBookManger.getAllBooks().size() + " Books in your library");
    }
}
