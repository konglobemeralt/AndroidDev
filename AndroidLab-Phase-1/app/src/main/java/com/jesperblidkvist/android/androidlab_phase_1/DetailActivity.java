package com.jesperblidkvist.android.androidlab_phase_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    SimpleBookManager mSimpleBookManger;

    TextView mTitleView;
    TextView mAuthorView;
    TextView mCourseView;
    TextView mPriceView;
    TextView mISBNView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mSimpleBookManger = new SimpleBookManager();

        mTitleView = (TextView) findViewById(R.id.titleText);
        mAuthorView = (TextView) findViewById(R.id.authorText);
        mCourseView = (TextView) findViewById(R.id.courseText);
        mPriceView = (TextView) findViewById(R.id.priceText);
        mISBNView = (TextView) findViewById(R.id.ISBNText);


        setTitle();
        mTitleView.setText(mSimpleBookManger.getBook(0).getTitle());
        mAuthorView.setText(mSimpleBookManger.getBook(0).getAuthor());
        mCourseView.setText(mSimpleBookManger.getBook(0).getCourse());
        mPriceView.setText(Integer.toString(mSimpleBookManger.getBook(0).getPrice()));
        mISBNView.setText(mSimpleBookManger.getBook(0).getIsbn());
    }


    private void setTitle(){

        String title = mSimpleBookManger.getBook(0).getTitle();

        if(title != null){
            mTitleView.setText(mSimpleBookManger.getBook(0).getTitle());
        }

        else{
            mTitleView.setText("No book avaliable");
        }

    }

}
