package com.apporio.onetap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.concurrent.TimeUnit;

public class Paidactivity extends Activity {
    private DonutProgress donutProgress;
    TextView hourvalue,mins,minsvalue;
    ImageView back,imghrtred,imghrtwyt,location;
    int j=0;
    String []foodtitle = {"Pasta al Dente x 2","Chicken Balsamico","Classic Caesar"};
    String []subtitle = {"with Black Olives","null","null"};
    String []foodtitlerate = {"16","14","27"};
    String []subtitlerate = {"0.90","null","null"};
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paidactivity);
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        hourvalue = (TextView) findViewById(R.id.hourtxt);
        mins = (TextView) findViewById(R.id.mins);
        back = (ImageView) findViewById(R.id.back);
        ll = (LinearLayout) findViewById(R.id.llfortotalitems);
        minsvalue = (TextView) findViewById(R.id.mintext);
        imghrtred = (ImageView) findViewById(R.id.imgheartred);
        imghrtwyt = (ImageView) findViewById(R.id.imgheartwhite);
        location = (ImageView) findViewById(R.id.locat);
        donutProgress.setProgress(j);
        donutProgress.setText("01:00");
        hourvalue.setText("02");
        minsvalue.setText("00");
        final CounterClass timer = new CounterClass(1000*60*60*2, 1000);
       // textViewTime.setText("03:00");
        timer.start();
        donutProgress.setFinishedStrokeColor(Color.parseColor("#FFD0D0D0"));
        donutProgress.setUnfinishedStrokeColor(Color.parseColor("#a61c1e"));
        donutProgress.setMax(60 * 60 * 2);
        for (int i = 0; i < 4; i++) {
            if (i ==3 ) {
               ll.addView(ordersview2(R.layout.layoutforgrandtotal));
            } else {
              ll.addView(ordersview(R.layout.layoutforfooditemsadd,foodtitle[i],foodtitlerate[i],subtitle[i],subtitlerate[i] ));
            }
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        imghrtred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imghrtwyt.setVisibility(View.VISIBLE);
                imghrtred.setVisibility(View.GONE);
            }
        });

        imghrtwyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imghrtred.setVisibility(View.VISIBLE);
                imghrtwyt.setVisibility(View.GONE);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Paidactivity.this, Mapactivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });
    }
    private View ordersview(int layoutforfooditemsadd, String foodtitle, String foodtitlerate, String subtitle, String subtitlerate) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Paidactivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layoutforfooditemsadd, null);
        final TextView foodt = (TextView) addView.findViewById(R.id.titlet);
        final TextView foodr = (TextView) addView.findViewById(R.id.foodr);
        final TextView subt = (TextView) addView.findViewById(R.id.subtitlet);
        final TextView subr = (TextView) addView.findViewById(R.id.subr);
        final LinearLayout llforsub = (LinearLayout) addView.findViewById(R.id.llforsubprice);
        foodt.setText(foodtitle);
        foodr.setText(foodtitlerate);
        subt.setText(subtitle);
        subr.setText(subtitlerate);
        if(subtitle.equals("null")){
            subt.setVisibility(View.GONE);
            llforsub.setVisibility(View.GONE);
        }
        return addView ;
    }
    private View ordersview2(int layout_name) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Paidactivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);

       final TextView totalprice= (TextView) addView.findViewById(R.id.tp);
        totalprice.setText("47.90");


        return addView ;
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            String hour22 = String.format("%02d", TimeUnit.MILLISECONDS.toHours(millis));
            String mins22 = String.format("%02d",  TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
            String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
            );
            System.out.println(hms);
            hourvalue.setText(hour22);

            minsvalue.setText(mins22);

           // textViewTime.setText(hms);
            donutProgress.setText(hms);
          //  i++;
            j++;

           // mProgressBar.setProgress(i);
            donutProgress.setProgress(j);
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
           // textViewTime.setText("Completed.");
            donutProgress.setText("Done");
          //  i++;
            j++;
          //  mProgressBar.setProgress(i);
            donutProgress.setProgress(j);
        }



    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Paidactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Paidactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Paidactivity.this.getWindow();
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
