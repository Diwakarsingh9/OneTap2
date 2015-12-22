package com.apporio.onetap.parsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.onetap.Loginscreenactivity;
import com.apporio.onetap.MainActivity;
import com.apporio.onetap.settergetter.Inner_login;
import com.apporio.onetap.settergetter.Outer_Signup;
import com.apporio.onetap.settergetter.Outer_login;
import com.apporio.onetap.singleton.VolleySingleton;
import com.apporio.onetap.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by saifi45 on 12/17/2015.
 */
public class parsingforsignup {
    public static RequestQueue queue;
    public static StringRequest sr2;
    public static Inner_login data_list1;


    public static Context ctc22;

    public static void parsing(final Context activity,String s1, String s2,String s3, String s4,String s5,String s6) {
        final ProgressDialog pd = new ProgressDialog(activity);
        pd.setMessage("Signing up ...");
        ctc22 = activity;

        queue = VolleySingleton.getInstance(activity).getRequestQueue();


        String locationurl2 = Api_s.sign_up.concat(s1).concat("&lname=").concat(s2).concat("&email=").concat(s3)
                .concat("&password=").concat(s4).concat("&mobile_number=").concat(s5).concat("&country_code=").concat(s6);
        locationurl2 = locationurl2.replace(" ", "%20");

        Log.e("url", "" + locationurl2);

        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//

                pd.dismiss();

                SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(activity);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Outer_Signup received2 = new Outer_Signup();
                    received2 = gson.fromJson(response, Outer_Signup.class);

                    String result = received2.result;
                    if(result.equals("0")){
//
                        Toast.makeText(ctc22, "" + received2.msg, Toast.LENGTH_SHORT).show();
                    }
                    else {

                        data_list1=received2.Details;
                        String fname11 =received2.Details.fname;
                        String lname11 = received2.Details.lname;
                        String emailid = received2.Details.email;
                        String user_id =received2.Details.user_id;
                        String phone_no = received2.Details.mobile_number;
                        String address1 =received2.Details.address1 ;
                        String address2 = received2.Details.address22;
                        String primary =received2.Details.primaryy ;
                        String latitude = received2.Details.latt;
                        String longitude =received2.Details.long22;
                        String image = received2.Details.images;
                        String fb_id = received2.Details.facebook_id;



                        //Toast.makeText(activity, ""+name11, Toast.LENGTH_SHORT).show();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                        SharedPreferences.Editor edit2 = prefs.edit();

                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                        edit2.putString("fname", "" + fname11);
                        edit2.putString("lname", "" + lname11);
                        edit2.putString("email", "" + emailid);
                        edit2.putString("user_id", "" +user_id);
                        edit2.putString("phone_no", "" + phone_no);
                        edit2.putString("address1", "" +address1);
                        edit2.putString("address2", "" + address2);
                        edit2.putString("primary", "" +primary);
                        edit2.putString("latitude", "" + latitude);
                        edit2.putString("longitude", "" +longitude);
                        edit2.putString("image", "" + image);
                        edit2.putString("fb_id", "" + fb_id);

                        edit2.commit();


                        SharedPreferences.Editor edit22 = prefs2.edit();

                        edit22.putBoolean("pref_previously_started", Boolean.TRUE);
                        edit22.commit();

                        Toast.makeText(activity, "Signed Up...", Toast.LENGTH_SHORT).show();

                        Intent in = new Intent(activity, MainActivity.class);
                        activity.startActivity(in);
                        Loginscreenactivity.log.finish();



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
        pd.show();


    }
}
