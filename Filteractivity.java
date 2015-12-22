package com.apporio.onetap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Filteractivity extends Activity {
    ImageView back;
    String lookingfor[]={"Italian","Mexican","Chinese","Indian","Austrian","German"};
    String thatdel[]={"Burritos","Burger","Pizza","Spaghetti","Noodles","Thukpa","Manchurian"};
    String within[]={"15 mins","30 mins","45 mins","1 hour","2 hour"};
    String toppings1[]={"Salsa","Garlic","Pepper","Red Chillies"};
    String toppings2[]={"Guacamole","Coriander","Cilantro","Broccoli"};
    String toppings3[]={"Sour Cream","Peas","Salmon","Onions"};
    String toppings4[]={"Cheese","Chedar","Smoked Gouda","Manchego"};
    Spinner look,with,thatd,extr1,extr2,extr3,extr4;
    TextView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filteractivity);
        back = (ImageView) findViewById(R.id.back);
        look=(Spinner)findViewById(R.id.spinner2);
        with=(Spinner)findViewById(R.id.spinner2544);
        thatd=(Spinner)findViewById(R.id.spinner25dd);
        extr1=(Spinner)findViewById(R.id.spinner25);
        extr2=(Spinner)findViewById(R.id.spinner25a);
        extr3=(Spinner)findViewById(R.id.spinner25s);
        extr4=(Spinner)findViewById(R.id.spinner25d);
        search=(TextView)findViewById(R.id.submitbr);

        ArrayAdapter adp = new ArrayAdapter(Filteractivity.this,R.layout.itemforfilter,R.id.textView39,lookingfor);
        look.setAdapter(adp);
        ArrayAdapter adp2 = new ArrayAdapter(Filteractivity.this,R.layout.itemforfilter,R.id.textView39,thatdel);
        with.setAdapter(adp2);
        ArrayAdapter adp3 = new ArrayAdapter(Filteractivity.this,R.layout.itemforfilter,R.id.textView39,within);
        thatd.setAdapter(adp3);
        ArrayAdapter adp4 = new ArrayAdapter(Filteractivity.this,R.layout.itemforfilter,R.id.textView39,toppings1);
        extr1.setAdapter(adp4);
        ArrayAdapter adp5 = new ArrayAdapter(Filteractivity.this,R.layout.itemforfilter,R.id.textView39,toppings2);
        extr2.setAdapter(adp5);
        ArrayAdapter adp6 = new ArrayAdapter(Filteractivity.this,R.layout.itemforfilter,R.id.textView39,toppings3);
        extr3.setAdapter(adp6);
        ArrayAdapter adp7 = new ArrayAdapter(Filteractivity.this,R.layout.itemforfilter,R.id.textView39,toppings4);
        extr4.setAdapter(adp7);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filteractivity.this.finish();

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Filteractivity.this, "Filtered data will show", Toast.LENGTH_SHORT).show();
                Filteractivity.this.finish();

            }
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Filteractivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Filteractivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Filteractivity.this.getWindow();
           // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
