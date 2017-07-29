package com.jesperblidkvist.android.emailappmockup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jesper on 2017-07-26.
 */

class EmailAdapter extends BaseAdapter {

    Context context;
    String[] headers;
    String[] bodies;
    private static LayoutInflater inflater = null;

    public EmailAdapter(Context context, String[] headers, String[] bodies) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.headers = headers;
        this.bodies = bodies;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return headers.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return headers[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.single_row, null);
        TextView text = (TextView) vi.findViewById(R.id.email_header);
        TextView body = (TextView) vi.findViewById(R.id.email_body);


        text.setText(headers[position]);
        body.setText(bodies[position]);

        return vi;
    }
}
