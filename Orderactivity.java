package com.apporio.onetap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.apporio.onetap.adapter.MyOrderAdapter;

public class Orderactivity extends Activity {
    public  static ListView lv;
    public  static ImageView search;
    String ds[]={"1","2","3"};
    public static ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderactivity);
        lv=(ListView)findViewById(R.id.listView);
        search=(ImageView)findViewById(R.id.imghr);
        back =(ImageView)findViewById(R.id.back);


        MyOrderAdapter adp = new MyOrderAdapter(Orderactivity.this,ds);
        lv.setAdapter(adp);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                overridePendingTransition(R.anim.slideoutup, 0);
                Orderactivity.this.finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(Orderactivity.this, MainActivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
                MainActivity.mainact.finish();
                Orderactivity.this.finish();
            }
        });


    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Orderactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Orderactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Orderactivity.this.getWindow();
          //  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}

