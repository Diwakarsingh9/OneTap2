package com.apporio.onetap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
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

import com.apporio.onetap.parsing.parsigforallcategories;
import com.apporio.onetap.parsing.parsing_for_settings;
import com.apporio.onetap.parsing.parsingforallfoods;
import com.apporio.onetap.parsing.parsingforalllocations;
import com.apporio.onetap.parsing.parsingforalltoppings;

public class Filteractivity extends Activity {
    public static ImageView back;
    String lookingfor[]={"Italian","Mexican","Chinese","Indian","Austrian","German"};
    String thatdel[]={"Burritos","Burger","Pizza","Spaghetti","Noodles","Thukpa","Manchurian"};
    String within[]={"5min","10min","15 mins","20min","30 mins","45 mins","1 hour","2 hour"};

    public static SharedPreferences.Editor edit22;
    String toppings1[]={"Salsa","Garlic","Pepper","Red Chillies"};
    String toppings2[]={"Guacamole","Coriander","Cilantro","Broccoli"};
    String toppings3[]={"Sour Cream","Peas","Salmon","Onions"};
    String toppings4[]={"Cheese","Chedar","Smoked Gouda","Manchego"};
   public static Spinner look,with,thatd,extr1,extr2,extr3,extr4,location;
    public static TextView search;
    public static ArrayAdapter adp,adpl,adp2,adp3,adp4,adp5,adp6,adp7;

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
        location=(Spinner)findViewById(R.id.spinnerlocation);
        search=(TextView)findViewById(R.id.submitbr);
        parsigforallcategories.parsing(Filteractivity.this);


        ArrayAdapter adp2 = new ArrayAdapter(Filteractivity.this,R.layout.itemforfilter,R.id.textView39,within);
        with.setAdapter(adp2);


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
