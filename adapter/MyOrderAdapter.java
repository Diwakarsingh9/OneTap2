package com.apporio.onetap.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.onetap.R;


public class MyOrderAdapter extends BaseAdapter {

    Context ctc;
    LayoutInflater inflater;
    String[]ad;


    public MyOrderAdapter(Context context, String[] timmingarraylist2) {
        ctc=context;
        ad=timmingarraylist2;
        inflater = LayoutInflater.from(this.ctc);
    }

    @Override
    public int getCount() {
        return ad.length;
    }

    @Override
    public Object getItem(int position) {
        Log.e("position", "Dish" + position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView tv1,tv2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemlayoutfororder, null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }


        holder.tv1 = (TextView)convertView.findViewById(R.id.itemTextView);
        holder.tv1.setText("Dish "+ad[position]);
        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctc,"U.D.",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
