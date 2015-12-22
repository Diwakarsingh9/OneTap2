package com.apporio.onetap.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.onetap.R;


public class Cartadapter extends BaseAdapter {

    Context ctc;
    LayoutInflater inflater;
    String[]ad;
    String []cb={"Extra Cheese","Topping:Black Olivers","Topping:White Paprika","White Thin Crust"};

    public Cartadapter(Context context, String[] timmingarraylist2) {
        ctc=context;
        ad=timmingarraylist2;
        inflater = LayoutInflater.from(this.ctc);


    }
    private View ordersview(int layout_name, String cls) {

        LayoutInflater layoutInflater =
                (LayoutInflater)ctc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final CheckBox cb = (CheckBox) addView.findViewById(R.id.checkBox2);

        cb.setText(cls);

        return addView ;
    }
    private View ordersview2(int layout_name) {

        LayoutInflater layoutInflater =
                (LayoutInflater)ctc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);

        Holder.up= (ImageView) addView.findViewById(R.id.circleups);


        return addView ;
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
    public static class Holder{

        TextView tv1,tv2;
        ImageView img;
        LinearLayout ll;
        public static ImageView up;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemlayoutforcartactivity, null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }
        holder.ll = (LinearLayout) convertView.findViewById(R.id.llforcb);
        if(position==0) {
            for (int i = 0; i < 5; i++) {
                if (i == 4) {
                    holder.ll.addView(ordersview2(R.layout.layoutforimage));
                } else {
                    holder.ll.addView(ordersview(R.layout.layoutforcb, cb[i]));
                }
            }
        }

        holder.tv1 = (TextView)convertView.findViewById(R.id.itemTextView);
        holder.img = (ImageView)convertView.findViewById(R.id.addons);
        holder.tv1.setText("Dish "+ad[position]);

        int currentposition=position;
        holder.up.setTag(currentposition);
        holder.img.setTag(currentposition);


        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctc, "U.D.", Toast.LENGTH_SHORT).show();

            }
        });

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.ll.getTag();
                holder.img.getTag();
                holder.ll.setVisibility(View.VISIBLE);
                holder.img.setVisibility(View.INVISIBLE);
            }
        });
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ll.getTag();
                holder.img.getTag();
                holder.ll.setVisibility(View.INVISIBLE);
                holder.img.setVisibility(View.VISIBLE);
            }
        });
        return convertView;
    }
}
