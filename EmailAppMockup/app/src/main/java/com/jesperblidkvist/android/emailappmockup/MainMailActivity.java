package com.jesperblidkvist.android.emailappmockup;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainMailActivity extends AppCompatActivity {
    ListView listview;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.email_main, menu);
        return true;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

        //Set the menu bar to a fake account
        //getActionBar().setTitle("Fake_Email.account@temp.com");


      // storing string resources into Array
      String[] emailHeaders = getResources().getStringArray(R.array.email_list);
      String[] emailBodies = getResources().getStringArray(R.array.email_body_list);

      setContentView(R.layout.activity_main_mail);
      listview = (ListView) findViewById(R.id.listview);
      listview.setAdapter(new EmailAdapter(this, emailHeaders, emailBodies));


      // listening to single list item on click
      listview.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
                                  int position, long id) {

              // selected item
              String emailTitle = (listview.getItemAtPosition(position).toString());

              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleEmailActivity.class);
              // sending data to new activity
              i.putExtra("email", emailTitle);
              i.putExtra("body", emailTitle);
              startActivity(i);

          }
      });



    }
}

