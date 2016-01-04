package com.apporio.onetap.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.onetap.R;
import com.apporio.onetap.adapter.Mainlistviewadapter;
import com.apporio.onetap.DBManager;
import com.apporio.onetap.settergetter.Inner_toppings;
import com.apporio.onetap.settergetter.Innerproductnames;
import com.apporio.onetap.settergetter.NO_Data_Setter;
import com.apporio.onetap.settergetter.Product_setter_getter;
import com.apporio.onetap.singleton.VolleySingleton;
import com.apporio.onetap.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Base_fragment extends Fragment {
    static String ds2;
    int i=0;
    public  static  int y;
    public  static String d[]={"1","2","3","4","5"};
    public  static String d2[]={"0","0","0","0","0"};
    public  static Mainlistviewadapter adp;
//    @Bind(R.id.listView22)ListView lv;
    ListView lv;
    int pos;
    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    List<Innerproductnames> product_names = new ArrayList<>();
    List<Inner_toppings> toppingssss = new ArrayList<>();
    ArrayList<String> product_id = new ArrayList<String>();
    ArrayList<String>   rest_id = new ArrayList<String>();
    ArrayList<String>   food_type = new ArrayList<String>();
    ArrayList<String>   food_price = new ArrayList<String>();
    ArrayList<String>   food_name = new ArrayList<String>();
    ArrayList<String>   food_image= new ArrayList<String>();
    ArrayList<String>   food_rating= new ArrayList<String>();
    ArrayList<String>   toppingprice= new ArrayList<String>();
    ArrayList<String>   toppingid= new ArrayList<String>();
    ArrayList<String>   toppingname= new ArrayList<String>();
    ArrayList<String>   no_of_units= new ArrayList<String>();
    ArrayList<String[]> toppingnameArray= new ArrayList<>();
    ArrayList<String[]> toppingidarray= new ArrayList<>();
    DBManager dbm ;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_blank, null);

        lv= (ListView)root.findViewById(R.id.listView22);



           // Log.e("poss22222", ""+pos);



         lv.setFocusable(false);


        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        dbm = new DBManager(getActivity());
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        try {
            parsingforfoodnames();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Base_fragment newInstance(Context context ,  String text) {

        Base_fragment f = new Base_fragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
//            Toast.makeText(getActivity(), "gggggggggg ", Toast.LENGTH_SHORT).show();
            try {
                parsingforfoodnames();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void parsingforfoodnames() {

        Rest_id = getArguments().getString("msg");

        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Api_s.food_names+ Rest_id;
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd",""+urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();

                GsonBuilder gsonBuilder = new GsonBuilder();
                final Gson gson = gsonBuilder.create();
                toppingssss.clear();
                product_id.clear();
                rest_id.clear();
                food_image.clear();
                toppingprice.clear();
                food_name.clear();
                food_price.clear();
                food_rating.clear();
                food_type.clear();
                toppingidarray.clear();
                toppingnameArray.clear();
                toppingid.clear();
                toppingname.clear();


                NO_Data_Setter received2 = new NO_Data_Setter();
                received2 = gson.fromJson(response, NO_Data_Setter.class);
                if (received2.result.equals("1")) {
                    Product_setter_getter product_setter_getter = gson.fromJson(response, Product_setter_getter.class);
                    product_names = product_setter_getter.innerproductnames;

                    for (int i = 0; i < product_names.size(); i++)
                    {
                        product_id.add(product_names.get(i).product_id);
                        rest_id.add(product_names.get(i).restraurant_id);
                        food_name.add(product_names.get(i).food_name);
                        food_type.add(product_names.get(i).food_type);
                        food_rating.add(product_names.get(i).rating);
                        food_price.add(product_names.get(i).price);
                        food_image.add(product_names.get(i).images);
                        toppingprice.add(product_names.get(i).topping_price);
                        toppingssss=product_names.get(i).toppingsss;
                        for(int j=0;j<toppingssss.size();j++){
                            toppingid.add(toppingssss.get(j).topping_id);
                            toppingname.add(toppingssss.get(j).topping);
                        }
                        String[] topname = new String[toppingid.size()];
                        topname = toppingid.toArray(topname);
                        String[] topid = new String[toppingname.size()];
                        topid = toppingname.toArray(topid);
                        toppingidarray.add(topid);
                        toppingnameArray.add(topname);
                        no_of_units.add("0");
                        toppingid.clear();
                        toppingname.clear();
                       // Toast.makeText(getActivity(), "" + toppingnameArray.get(i).toString(), Toast.LENGTH_SHORT).show();


                    }

                        lv.setAdapter(new Mainlistviewadapter(getActivity(), product_id, rest_id,
                                food_name, food_type, food_rating, food_price,food_image,no_of_units,toppingnameArray,toppingidarray));

                } else {
                    //Toast.makeText(getActivity(), "Please enter correct details", Toast.LENGTH_SHORT).show();
                    //pDialog.dismiss();


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
    }

}


