package com.apporio.onetap.parsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.onetap.Favouritesactivity;
import com.apporio.onetap.R;
import com.apporio.onetap.Settingsactivity;
import com.apporio.onetap.adapter.myfavouritesadapter;
import com.apporio.onetap.settergetter.Inner_all_cities;
import com.apporio.onetap.settergetter.Inner_view_fav;
import com.apporio.onetap.settergetter.NO_Data_Setter;
import com.apporio.onetap.settergetter.Outer_all_cities;
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
public class parsing_for_settings {

    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static List<Inner_all_cities> city_names = new ArrayList<>();
    public static ArrayList<String> cityname = new ArrayList<String>();
    public static ArrayList<String> cityid = new ArrayList<String>();

    public static void parsing(final Context c) {
        final ProgressDialog pd = new ProgressDialog(c);
        pd.setMessage("Loading ...");

        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Api_s.all_cities;
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

                    cityname.clear();
                    cityid.clear();


                    Outer_all_cities received2 = new Outer_all_cities();
                    received2 = gson.fromJson(response, Outer_all_cities.class);

                    if (received2.result.equals("1")) {
                        city_names=received2.inner_all_cities;

                        for (int i = 0; i < city_names.size(); i++)
                        {
                            cityid.add(city_names.get(i).city_id);
                            cityname.add(city_names.get(i).city);
                        }
                        Settingsactivity.adp = new ArrayAdapter(c, R.layout.itemforfilter,R.id.textView39,cityname);
                        Settingsactivity.sp.setAdapter(Settingsactivity.adp);


                    } else {



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
