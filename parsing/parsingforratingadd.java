package com.apporio.onetap.parsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.onetap.Foodinneractivity;
import com.apporio.onetap.Forgotpassword;
import com.apporio.onetap.settergetter.Forgotpassword_settergetter;
import com.apporio.onetap.settergetter.Inner_login;
import com.apporio.onetap.settergetter.ratingouter;
import com.apporio.onetap.singleton.VolleySingleton;
import com.apporio.onetap.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by saifi45 on 12/18/2015.
 */
public class parsingforratingadd {


    public static RequestQueue queue;
    public static StringRequest sr2;
    public static Inner_login data_list1;


    public static Context ctc22;

    public static void parsing(final Context activity,String s,String s1, final String s2) {
        final ProgressDialog pd = new ProgressDialog(activity);

        ctc22 = activity;

        queue = VolleySingleton.getInstance(activity).getRequestQueue();


        String locationurl2 = Api_s.ratingadd.concat(s).concat("&product_id=").concat(s1).concat("&rating=").concat(s2);
        locationurl2 = locationurl2.replace(" ", "%20");

        Log.e("url", "" + locationurl2);

        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//




                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    ratingouter received2 = new ratingouter();
                    received2 = gson.fromJson(response, ratingouter.class);

                    String result = received2.result;
                    if(result.equals("0")){
//
                        Toast.makeText(ctc22, "" + received2.msg, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ctc22, "" + received2.msg, Toast.LENGTH_SHORT).show();
                        Foodinneractivity.rb.setScore(Integer.parseInt(s2));


                        final Toast toast = Toast.makeText(activity, "Rating " + s2, Toast.LENGTH_SHORT);
                        toast.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 500);



                    }



                } catch (JsonSyntaxException e) {
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



    }
}
