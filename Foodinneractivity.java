package com.apporio.onetap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.onetap.parsing.parsing_for_fooddetails;
import com.apporio.onetap.parsing.parsingfordofav;
import com.apporio.onetap.parsing.parsingforratingadd;
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
import com.nirhart.parallaxscroll.views.ParallaxScrollView;

import java.util.ArrayList;

import views.CustomRatingBar;
import views.textview.CustomRatingBarGreen;

public class Foodinneractivity extends Activity implements OnChartValueSelectedListener {
    public  static  HorizontalBarChart mChart;
    public  static LinearLayout ll22;
    public  static TextView foodname,foodtype,price,ratingtext,descp;
    public  static String[] y={"1","2","3","4","5"};
    float f = (float) 14.90;
    String sd;
    public  static String fav="0";
    public  static SharedPreferences prefs;
    public  static int[] x={10,22,33,45,155};
    public  static String []cb={"Extra Cheese","Topping:Black Olivers","Topping:White Paprika","White Thin Crust"};
    public  static LinearLayout ll;
    public  static ParallaxScrollView ps;
    public  static ImageView img,back,imghrtred,imghrtwyt,location,cartwyt,foodimage;
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
        imghrtred = (ImageView) findViewById(R.id.imgheartred);
        imghrtwyt = (ImageView) findViewById(R.id.imgheartwhite);
        location = (ImageView) findViewById(R.id.locat);
        foodname = (TextView) findViewById(R.id.foodname);
        foodtype = (TextView) findViewById(R.id.foodtype);
        ratingtext = (TextView) findViewById(R.id.ratingtext);
        descp = (TextView) findViewById(R.id.descption);
        Bundle b = getIntent().getExtras();
        final String pro_id= b.getString("product_id", null);
        prefs = PreferenceManager.getDefaultSharedPreferences(Foodinneractivity.this);
        price = (TextView) findViewById(R.id.price);
        ll = (LinearLayout) findViewById(R.id.llforcb);
        ll22 = (LinearLayout) findViewById(R.id.ll22);
        ll22.setFocusable(false);
        ll22.setClickable(false);
        parsing_for_fooddetails.parsing(Foodinneractivity.this,pro_id);


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

        setData(5, 50);
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
    private void setData(int count, int range) {

        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count; i++) {

            xVals.add(y[i]);

            yVals1.add(new BarEntry((x[i]), i));
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
}