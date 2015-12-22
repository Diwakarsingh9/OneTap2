package com.apporio.onetap.parsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.onetap.Favouritesactivity;
import com.apporio.onetap.adapter.myfavouritesadapter;
import com.apporio.onetap.settergetter.Inner_view_fav;
import com.apporio.onetap.settergetter.NO_Data_Setter;
import com.apporio.onetap.settergetter.Outer_view_fav;
import com.apporio.onetap.singleton.VolleySingleton;
import com.apporio.onetap.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by saifi45 on 12/22/2015.
 */
public class parsingforalllocations {

    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static List<Inner_view_fav> product_names = new ArrayList<>();
    public static ArrayList<String> product_id = new ArrayList<String>();
    public static  ArrayList<String>   rest_id = new ArrayList<String>();
    public static  ArrayList<String>   food_type = new ArrayList<String>();
    public static  ArrayList<String>   food_price = new ArrayList<String>();
    public static  ArrayList<String>   food_name = new ArrayList<String>();
    public static  ArrayList<String>   food_image= new ArrayList<String>();
    public static  ArrayList<String>   food_rating= new ArrayList<String>();
    public static  ArrayList<String>   no_of_units= new ArrayList<String>();

    public static void parsing(final Context c, String s) {
        final ProgressDialog pd = new ProgressDialog(c);
        pd.setMessage("Uploading ...");

        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Api_s.view_favourites+ s;
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                pd.dismiss();
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    product_id.clear();
                    rest_id.clear();
                    food_image.clear();

                    food_name.clear();
                    food_price.clear();
                    food_rating.clear();
                    food_type.clear();

                    NO_Data_Setter received2 = new NO_Data_Setter();
                    received2 = gson.fromJson(response, NO_Data_Setter.class);
                    if (received2.result.equals("1")) {
                        Outer_view_fav product_setter_getter = gson.fromJson(response, Outer_view_fav.class);
                        product_names = product_setter_getter.inner_view_fav;

                        for (int i = 0; i < product_names.size(); i++)
                        {
                            product_id.add(product_names.get(i).product_ids);
                            rest_id.add(product_names.get(i).restraurant_id);
                            food_name.add(product_names.get(i).food_name);
                            food_type.add(product_names.get(i).food_type);
                            food_rating.add(product_names.get(i).rating);
                            food_price.add(product_names.get(i).prices);
                            food_image.add(product_names.get(i).images);
                            no_of_units.add("0");


                        }
                        Favouritesactivity.adp=new myfavouritesadapter(c, product_id, rest_id,
                                food_name, food_type, food_rating, food_price, food_image);
                        Favouritesactivity.lv.setAdapter(Favouritesactivity.adp);
                        Toast.makeText(c, "Swipe to dismiss items ....", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(c, "No favourite items", Toast.LENGTH_SHORT).show();
                        //pDialog.dismiss();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                Log.e("Sucess", "" + error.toString());
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);
        pd.show();
    }
}
