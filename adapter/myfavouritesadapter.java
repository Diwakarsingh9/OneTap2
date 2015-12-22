package com.apporio.onetap.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.apporio.onetap.Favouritesactivity;
import com.apporio.onetap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import views.CustomRatingBar;


public class myfavouritesadapter extends BaseAdapter {

    Context ctc;
    LayoutInflater inflater;
    ArrayList<String> ad;
    ArrayList<String> product_id = new ArrayList<String>();
    ArrayList<String>   rest_id = new ArrayList<String>();
    ArrayList<String>   food_type = new ArrayList<String>();
    ArrayList<String>   food_price = new ArrayList<String>();
    ArrayList<String>   food_name = new ArrayList<String>();
    ArrayList<String>   food_image= new ArrayList<String>();
    ArrayList<String>   food_rating= new ArrayList<String>();


    public myfavouritesadapter(Context c, ArrayList<String> product_id, ArrayList<String> rest_id, ArrayList<String> food_name, ArrayList<String> food_type, ArrayList<String> food_rating, ArrayList<String> food_price, ArrayList<String> food_image) {
        ctc=c;
        this.product_id=product_id;
        this.rest_id=rest_id;
        this.food_type=food_type;
        this.food_price=food_price;
        this.food_name=food_name;
        this.food_image=food_image;
        this.food_rating=food_rating;
        inflater = LayoutInflater.from(this.ctc);
    }

    @Override
    public int getCount() {
        return product_id.size();
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
        public ImageView imageview ,plusbtn , minusbtn,bannerimage;
        public CustomRatingBar ratingBar;
        public TextView  product_name  , product_service_name  , product_price , noofunit_product,cuisines;
        TextView tv1,delete,undo;
    }
    public void remove(int position) {
            product_id.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemlayoutforfavourites, null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.imageview = (ImageView) convertView.findViewById(R.id.imageView);
        holder.product_name = (TextView) convertView.findViewById(R.id.itemTextView);
        holder.product_price = (TextView) convertView.findViewById(R.id.price);

        holder.cuisines = (TextView) convertView.findViewById(R.id.textView);
        holder.ratingBar = (CustomRatingBar) convertView.findViewById(R.id.ratingBar4);
        holder.product_name.setText(food_name.get(i));
        holder.ratingBar.setScore(Float.parseFloat(food_rating.get(i)));
        holder.product_price.setText(food_price.get(i));
        holder.cuisines.setText(food_type.get(i));

        String images = food_image.get(i);
        images = "http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/"+ images.replace("//","/");

        //  Log.e("bahjd",""+images);
        Picasso.with(ctc)
                .load(images)
                .placeholder(R.drawable.logofork)
                .error(R.drawable.logofork)
                .into(holder.imageview);


        return convertView;
    }
}
