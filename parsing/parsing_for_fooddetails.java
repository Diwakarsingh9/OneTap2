package com.apporio.onetap.parsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.onetap.Foodinneractivity;
import com.apporio.onetap.R;
import com.apporio.onetap.settergetter.Inner_add_ons;
import com.apporio.onetap.settergetter.Inner_fooddetails;
import com.apporio.onetap.settergetter.Outer_fooddetails;
import com.apporio.onetap.singleton.VolleySingleton;
import com.apporio.onetap.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/19/2015.
 */
public class parsing_for_fooddetails {

    public static RequestQueue queue;
    public static StringRequest sr2;
    public static Inner_fooddetails data_list1;
    public static String rest_id ;
    public static String product_id ;
    public static String food_type;
    public static String food_name ;
    public static String delivry_time;
    public static String images ;
    public static String description ;
    public static String rating;
    public static String favourite ;
    public static String food_price ;
    public static List<Inner_add_ons> addons ;
    public static ArrayList<String> toppingid = new ArrayList<String>();
    public static ArrayList<String> toppingname = new ArrayList<String>();


    public static  void parsing(final Context activity,String s1) {
        final ProgressDialog pd = new ProgressDialog(activity);
        pd.setMessage("loading");


        queue = VolleySingleton.getInstance(activity).getRequestQueue();


        String locationurl2 = Api_s.product_info.concat(s1);
        locationurl2 = locationurl2.replace(" ", "%20");
       // locationurl2 = locationurl2.replace("VIDEOS","VIDEO");
        Log.e("url", "" + locationurl2);

        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//


                pd.dismiss();
//                Videoactivity.pb.setVisibility(View.GONE);
//                Videoactivity.llforlist.setVisibility(View.VISIBLE);

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Outer_fooddetails received2 = new Outer_fooddetails();
                    received2 = gson.fromJson(response, Outer_fooddetails.class);

                    String result = received2.result;
                    if(result.equals("0")){
//                        Videoactivity.relatedsearch.setVisibility(View.GONE);
//                        Videoactivity.v111.setVisibility(View.GONE);
                    }
                    else {

                        data_list1=received2.inner_fooddetails;

                            rest_id=(data_list1.restraurant_id);
                            product_id=(data_list1.product_id);
                            food_type=(data_list1.food_type);
                            food_name=(data_list1.food_name);
                            delivry_time=(data_list1.deliver_time);
                            images=(data_list1.image);
                        food_price=(data_list1.price);
                            description=(data_list1.description);
                            rating=(data_list1.rating);
                            favourite=(data_list1.favorite);
                        addons=data_list1.add_on;
                        Log.e("sizeaddon",addons.size()+"");
                        for(int i=0;i<addons.size();i++) {
                            toppingname.add(addons.get(i).topping);
                            toppingid.add(addons.get(i).topping_id);

                        }
                        Foodinneractivity.foodname.setText(""+food_name);
                        Foodinneractivity.foodtype.setText(""+food_type);
                        Foodinneractivity.descp.setText(""+description);
                        Foodinneractivity.ratingtext.setText("" + rating);
                        Foodinneractivity.price.setText("" + food_price);
                        if(rating.toString().trim().equals("0")){

                        }
                        else {
                            Foodinneractivity.rbred.setScore(Float.parseFloat(rating+""));
                        }



                        if(favourite.equals("0")){
                            Foodinneractivity.fav="0";
                            Foodinneractivity.imghrtwyt.setVisibility(View.VISIBLE);
                            Foodinneractivity.imghrtred.setVisibility(View.GONE);
                        }
                        else {
                            Foodinneractivity.fav="1";
                            Foodinneractivity.imghrtwyt.setVisibility(View.GONE);
                            Foodinneractivity.imghrtred.setVisibility(View.VISIBLE);
                        }

                        for(int i=0;i<addons.size();i++){

                            Foodinneractivity.ll.addView(Foodinneractivity.ordersview(activity, R.layout.layoutforcb, toppingname.get(i)));
                        }

                    }



                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("exception", "" + e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr2);
        pd.show();
//        Videoactivity.pb.setVisibility(View.VISIBLE);
//        Videoactivity.llforlist.setVisibility(View.GONE);

    }

    }
