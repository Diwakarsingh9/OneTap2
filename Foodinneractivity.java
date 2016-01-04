package com.apporio.onetap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.onetap.parsing.parsingfordofav;
import com.apporio.onetap.parsing.parsingforratingadd;
import com.apporio.onetap.settergetter.Inner_add_ons;
import com.apporio.onetap.settergetter.Inner_fooddetails;
import com.apporio.onetap.settergetter.Inner_rating;
import com.apporio.onetap.settergetter.Outer_fooddetails;
import com.apporio.onetap.singleton.VolleySingleton;
import com.apporio.onetap.urlapi.Api_s;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import views.CustomRatingBar;
import views.textview.CustomRatingBarGreen;

public class Foodinneractivity extends Activity implements OnChartValueSelectedListener {

    public static RequestQueue queue;
    public static StringRequest sr2;
    public static Inner_fooddetails data_list1;
    public static Inner_rating data_list_rating;
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
    public static String rest_images ;
    public static String no_of_rating_person ;
    public static List<Inner_add_ons> addons ;
    public static String rating1 ;
    public static String rating2;
    public static String rating3 ;
    public static String rating4 ;
    public static String rating5 ;
    public static ArrayList<String> toppingid = new ArrayList<String>();
    public static ArrayList<String> toppingname = new ArrayList<String>();
    public static ArrayList<String> rating_nos = new ArrayList<String>();

    ////////////////////////////////////////////
    public  static  HorizontalBarChart mChart;
    public  static LinearLayout ll22;
    public  static TextView foodname,foodtype,price,ratingtext,descp,no_of_person_rated;
    public  static String[] y={"1","2","3","4","5"};
    float f = (float) 14.90;
    String sd;
    public  static String fav="0";
    public  static SharedPreferences prefs;
    public  static int[] x={10,22,33,45,155};
    public  static String []cb={"Extra Cheese","Topping:Black Olivers","Topping:White Paprika","White Thin Crust"};
    public  static LinearLayout ll;
    public  static ParallaxScrollView ps;
    public  static ImageView img,back,imghrtred,imghrtwyt,location,cartwyt,foodimage,rest_image;
    public  static CustomRatingBarGreen rb;
    public  static CustomRatingBar rbred;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodinneractivity);
        mChart = (HorizontalBarChart) findViewById(R.id.chart1);
        rb = (CustomRatingBarGreen) findViewById(R.id.ratingBar2);
        rbred = (CustomRatingBar) findViewById(R.id.ratingbarred);
        ps = (ParallaxScrollView) findViewById(R.id.parallex);
        img = (ImageView) findViewById(R.id.buttonforpaying);
        back = (ImageView) findViewById(R.id.back);
        cartwyt = (ImageView) findViewById(R.id.cartwyt);
        foodimage = (ImageView) findViewById(R.id.foodimage);
        rest_image = (ImageView) findViewById(R.id.imageView11);
        imghrtred = (ImageView) findViewById(R.id.imgheartred);
        imghrtwyt = (ImageView) findViewById(R.id.imgheartwhite);
        location = (ImageView) findViewById(R.id.locat);
        foodname = (TextView) findViewById(R.id.foodname);
        foodtype = (TextView) findViewById(R.id.foodtype);
        ratingtext = (TextView) findViewById(R.id.ratingtext);
        no_of_person_rated = (TextView) findViewById(R.id.textView13);
        descp = (TextView) findViewById(R.id.descption);
        Bundle b = getIntent().getExtras();
        final String pro_id= b.getString("product_id", null);
        prefs = PreferenceManager.getDefaultSharedPreferences(Foodinneractivity.this);
        price = (TextView) findViewById(R.id.price);
        ll = (LinearLayout) findViewById(R.id.llforcb);
        ll22 = (LinearLayout) findViewById(R.id.ll22);
        ll22.setFocusable(false);
        ll22.setClickable(false);
        parsing(Foodinneractivity.this, pro_id);


        imghrtred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsingfordofav.parsing(Foodinneractivity.this, prefs.getString("user_id", null), pro_id, "0");
                imghrtwyt.setVisibility(View.VISIBLE);
                imghrtred.setVisibility(View.GONE);
                fav="1";

            }
        });

        imghrtwyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsingfordofav.parsing(Foodinneractivity.this,prefs.getString("user_id", null),pro_id,"1");
                imghrtred.setVisibility(View.VISIBLE);
                imghrtwyt.setVisibility(View.GONE);
                fav="0";

            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Foodinneractivity.this, Mapactivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });



        rb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = rb.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    int stars = (int) starsf + 1;
                    parsingforratingadd.parsing(Foodinneractivity.this,prefs.getString("user_id", null),pro_id,stars+"");

                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }


                return true;
            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Foodinneractivity.this,Cartactivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });
        cartwyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Foodinneractivity.this,Cartactivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
                Foodinneractivity.this.finish();
            }
        });

        // mChart.setOnChartValueSelectedListener(this);
        // mChart.setHighlightEnabled(false);

        // mChart.setDrawBarShadow(false);

        // mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);
        mChart.setClickable(false);
        mChart.setFocusable(false);
        // scaling can now only be done on x- and y-axis separately
        // mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        // mChart.setDrawXLabels(false);

        // mChart.setDrawGridBackground(false);

        // mChart.setDrawYLabels(false);

        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        // xl.setTextColor(Color.parseColor("#ffffff"));
        // xl.setTypeface(tf);

        xl.setDrawAxisLine(false);
        xl.setDrawGridLines(false);
        // xl.setGridLineWidth(0.3f);

        YAxis yl = mChart.getAxisLeft();
        // yl.setTypeface(tf);
        yl.setTextColor(Color.parseColor("#ffffff"));
        yl.setDrawAxisLine(false);
        yl.setDrawGridLines(false);
        //  yl.setGridLineWidth(0.3f);
        yl.setInverted(false);

        YAxis yr = mChart.getAxisRight();
        // yr.setTypeface(tf);

        yr.setTextColor(Color.parseColor("#ffffff"));
        yr.setDrawAxisLine(false);
        yr.setDrawGridLines(false);
        yr.setInverted(false);

        //setData(5, 50, rating_nos);
        mChart.animateY(2500);

        // setting data
       /* mSeekBarY.setProgress(50);
        mSeekBarX.setProgress(12);

        mSeekBarY.setOnSeekBarChangeListener(this);
        mSeekBarX.setOnSeekBarChangeListener(this);*/

        Legend l = mChart.getLegend();

        l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        l.setFormSize(0);
        // l.setXEntrySpace(4f);
        mChart.setBackgroundColor(Color.parseColor("#ffffff"));

    }
    public  void setData(int count, int range, ArrayList<String> rating_nos) {

        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count; i++) {

            xVals.add(y[i]);

            yVals1.add(new BarEntry(Integer.parseInt((rating_nos.get(i))), i));
            //Toast.makeText(Foodinneractivity.this, ""+new BarEntry(x[i],i), Toast.LENGTH_SHORT).show();



        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(new int[]{Foodinneractivity.this.getResources().getColor(R.color.red2),
                Foodinneractivity.this.getResources().getColor(R.color.orange),
                Foodinneractivity.this.getResources().getColor(R.color.yellow2),
                Foodinneractivity.this.getResources().getColor(R.color.lightgreen),
                Foodinneractivity.this.getResources().getColor(R.color.green)});
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);

        // data.setValueTypeface(tf);

        mChart.setData(data);
    }


    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mChart.getBarBounds((BarEntry) e);
        PointF position = mChart.getPosition(e, mChart.getData().getDataSetByIndex(dataSetIndex)
                .getAxisDependency());

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());
    }

    public void onNothingSelected() {
    };
    public static View ordersview(Context c,int layout_name, String cls) {

        LayoutInflater layoutInflater =
                (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final CheckBox cb = (CheckBox) addView.findViewById(R.id.checkBox2);

        cb.setText(cls);

        return addView ;
    }

    @Override
    protected void onResume(){
        super.onResume();
        ps.setScrollY(0);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Foodinneractivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Foodinneractivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Foodinneractivity.this.getWindow();
           // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public void parsing(final Context activity, String s1) {
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
                        rest_images=(data_list1.restraurant_image);
                        no_of_rating_person=(data_list1.no_of_ret_pers);
                        images=(data_list1.image);
                        food_price=(data_list1.price);
                        description=(data_list1.description);
                        rating=(data_list1.rating);
                        favourite=(data_list1.favorite);
                        rating1=(data_list1.inner_rating.rating_1);
                        rating2=(data_list1.inner_rating.rating_2);
                        rating3=(data_list1.inner_rating.rating_3);
                        rating4=(data_list1.inner_rating.rating_4);
                        rating5=(data_list1.inner_rating.rating_5);
                        addons=data_list1.add_on;
                        Log.e("sizeaddon", addons.size() + "");
                        for(int i=0;i<addons.size();i++) {
                            toppingname.add(addons.get(i).topping);
                            toppingid.add(addons.get(i).topping_id);

                        }
                       rating_nos.add(rating1);
                        rating_nos.add(rating2);
                        rating_nos.add(rating3);
                        rating_nos.add(rating4);
                        rating_nos.add(rating5);
                        Foodinneractivity.foodname.setText(""+food_name);
                        Foodinneractivity.foodtype.setText(""+food_type);
                        Foodinneractivity.descp.setText(""+description);
                        Foodinneractivity.ratingtext.setText("" + rating);
                        Foodinneractivity.price.setText("" + food_price);
                        Foodinneractivity.no_of_person_rated.setText("" + no_of_rating_person);
                        Picasso.with(activity)
                                .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/" + images)
                                .placeholder(R.drawable.logo) // optional
                                .error(R.drawable.logo)         // optional
                                .into(Foodinneractivity.foodimage);
                        Picasso.with(activity)
                                .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/" + rest_images)
                                .placeholder(R.drawable.logo) // optional
                                .error(R.drawable.logo)         // optional
                                .into(Foodinneractivity.rest_image);
                        setData(5,50,rating_nos);
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