package com.apporio.onetap.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.apporio.onetap.Foodinneractivity;
import com.apporio.onetap.R;
import com.apporio.onetap.DBManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import views.CustomRatingBar;

/**
 * Created by saifi45 on 9/10/2015.
 */
public class Mainlistviewadapter extends BaseAdapter {
    Context ctc;
    public  static ArrayList<String[]> toppingidarray= new ArrayList<>();
    public static ArrayList<String[]> toppingnamearray = new ArrayList<>();
    ArrayList<String> product_id = new ArrayList<String>();
    ArrayList<String>   rest_id = new ArrayList<String>();
    ArrayList<String>   food_type = new ArrayList<String>();
    ArrayList<String>   food_price = new ArrayList<String>();
    ArrayList<String>   food_name = new ArrayList<String>();
    ArrayList<String>   food_image= new ArrayList<String>();
    ArrayList<String>   food_rating= new ArrayList<String>();
    DBManager dbm;
    ArrayList<String>   no_of_units= new ArrayList<String>();
    int value;




    public Mainlistviewadapter(FragmentActivity activity, ArrayList<String> product_id, ArrayList<String> rest_id, ArrayList<String> food_name, ArrayList<String> food_type, ArrayList<String> food_rating, ArrayList<String> food_price, ArrayList<String> food_image, ArrayList<String> no_of_units, ArrayList<String[]> toppingnameArray, ArrayList<String[]> toppingidarray) {
        this.ctc=activity;
        this.product_id=product_id;
        this.rest_id=rest_id;
        this.food_type=food_type;
        this.food_price=food_price;
        this.food_name=food_name;
        this.food_image=food_image;
        this.food_rating=food_rating;
        this.no_of_units=no_of_units;
        this.toppingidarray=toppingidarray;
        this.toppingnamearray=toppingnameArray;
        dbm = new DBManager(ctc);

    }

    @Override
    public int getCount() {
        return product_id.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        ViewHolder viewHolder = new ViewHolder();
        if(rowView == null){
            LayoutInflater inflater = (LayoutInflater) ctc.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            rowView = inflater.inflate(R.layout.recyclerview_item, null);
            // configure view holder

            viewHolder.imageview = (ImageView) rowView.findViewById(R.id.imageView);
            viewHolder.product_name = (TextView) rowView.findViewById(R.id.itemTextView);
            viewHolder.product_price = (TextView) rowView.findViewById(R.id.price);
            viewHolder.noofunit_product = (TextView) rowView.findViewById(R.id.result);
            viewHolder.cuisines = (TextView) rowView.findViewById(R.id.textView);
            viewHolder.ratingBar = (CustomRatingBar) rowView.findViewById(R.id.ratingBar4);
            viewHolder.plusbtn = (ImageView) rowView.findViewById(R.id.plus22);
            viewHolder.minusbtn = (ImageView) rowView.findViewById(R.id.minus22);
            rowView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)rowView.getTag();
        }

//           viewHolder.imageview.setImageResource(Integer.parseInt(productimages_arr.get(i)));
        viewHolder.product_name.setText(""+food_name.get(i));
        viewHolder.ratingBar.setScore(Float.parseFloat(food_rating.get(i)));
        viewHolder.product_price.setText(""+food_price.get(i));
        viewHolder.cuisines.setText(""+food_type.get(i));
        viewHolder.noofunit_product.setText(""+dbm.getNoofunitAccordingToProductId(product_id.get(i)));
        String images = ""+food_image.get(i);
        images = "http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/"+ images.replace("//","/");

      //  Log.e("bahjd",""+images);
        Picasso.with(ctc)
                .load(images)
                .placeholder(R.drawable.logofork)
                .error(R.drawable.logofork)
                .into(viewHolder.imageview);

        // viewHolder.noofunit_product.setText(product_noofunit_arr.get(i));


        final ViewHolder finalViewHolder = viewHolder;
      //  sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
///////////////////////  PLS
        viewHolder.plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = Integer.parseInt(finalViewHolder.noofunit_product.getText().toString());
                finalViewHolder.noofunit_product.setText("" + (value + 1));
                no_of_units.set(i, "" + (value + 1));
              // Toast.makeText(ctc, "" + toppingnamearray.get(i), Toast.LENGTH_SHORT).show();
                dbm.addtocart(product_id.get(i),rest_id.get(i), food_name.get(i), food_type.get(i), food_price.get(i),food_image.get(i),food_rating.get(i),
                        no_of_units.get(i),toppingnamearray.get(i),toppingidarray.get(i));


            }
        });
//        Toast.makeText(con,sharedpreferences.getString("Itemunits",("" + (value + 1))),Toast.LENGTH_SHORT).show();


/////////////////////  MINS
        viewHolder.minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(finalViewHolder.noofunit_product.getText().toString());
                if (value > 0) {
                    if (value == 1) {
                        finalViewHolder.noofunit_product.setText("" + (value - 1));
                        no_of_units.set(i, "" + (value - 1));
                        // remove it from DB
                        dbm.removeItemfromDB(product_id.get(i));

                    }
                    if (value > 1) {
                        finalViewHolder.noofunit_product.setText("" + (value - 1));
                        no_of_units.set(i, "" + (value - 1));
                        ///edit it into DB
                        dbm.addtocart(product_id.get(i), rest_id.get(i), food_name.get(i), food_type.get(i), food_price.get(i), food_image.get(i), food_rating.get(i),
                                no_of_units.get(i), toppingnamearray.get(i), toppingidarray.get(i));
                    }
                } else {
                    //do nothing
                }
            }
        });



        viewHolder.imageview.setTag(i);
        viewHolder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                Intent in = new Intent(ctc, Foodinneractivity.class);
                in.putExtra("product_id",product_id.get(pos));
                ctc.startActivity(in);


            }
        });
        viewHolder.product_name.setTag(i);
        viewHolder.product_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                Intent in = new Intent(ctc, Foodinneractivity.class);
                in.putExtra("product_id",product_id.get(pos));
                ctc.startActivity(in);


            }
        });
        viewHolder.noofunit_product.setTag(i);
        viewHolder.noofunit_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                Intent in = new Intent(ctc, Foodinneractivity.class);
                in.putExtra("product_id",product_id.get(pos));
                ctc.startActivity(in);


            }
        });
        viewHolder.cuisines.setTag(i);
        viewHolder.cuisines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                Intent in = new Intent(ctc, Foodinneractivity.class);
                in.putExtra("product_id",product_id.get(pos));
                ctc.startActivity(in);


            }
        });
        viewHolder.ratingBar.setTag(i);
        viewHolder.ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos= (int) v.getTag();
                Intent in = new Intent(ctc, Foodinneractivity.class);
                in.putExtra("product_id",product_id.get(pos));
                ctc.startActivity(in);


            }
        });
        viewHolder.product_price.setTag(i);
        viewHolder.product_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                Intent in = new Intent(ctc, Foodinneractivity.class);
                in.putExtra("product_id",product_id.get(pos));
                ctc.startActivity(in);


            }
        });


        return rowView;
    }

    static class ViewHolder {
        public ImageView imageview ,plusbtn , minusbtn,bannerimage;
        public CustomRatingBar ratingBar;
        public TextView  product_name  , product_service_name  , product_price , noofunit_product,cuisines;

    }
}
