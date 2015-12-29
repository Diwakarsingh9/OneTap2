package com.apporio.onetap;


        import android.annotation.TargetApi;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Build;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RatingBar;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.apporio.onetap.parsing.parsing_for_settings;
        import com.appyvet.rangebar.RangeBar;

        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

public class Settingsactivity extends Activity {




    public  static ImageView search;
    public  static ArrayAdapter adp;
    public static SharedPreferences prefs2;
    public static SharedPreferences.Editor edit22;
    String []cb={"Low Cal Food","Morning Coffee","Fitness Food","Brunch"};
//    String []cities={"Aberdeen","Armagh","Bangor","Bath",
//            "Belfast","Birmingham","Bradford","Brighton and Hove",
//            "Derby","Cambridge","Canterbury","Cardiff","Carlisle","Chester","Chichester","Coventry",
//            "Aberdeen","Dundee","Durham","Edinburgh","fly","Glasgow","Gloucester","Hereford",
//            "Inverness","Kingston","Lancaster","Leeds","Leicester","Lichfield","Lincoln","Lisburn",
//            "Liverpool","London","Londonderry","Manchester","Newcastle","Newport","Newry","Norwich",
//            "Nottingham","Oxford","Peterborough","Plymouth","Portsmouth","Preston","Ripon","Salford",};
public static ArrayList<String> interested = new ArrayList<String>();
    public static ImageView back;
    public  static LinearLayout ll;
    public  static com.appyvet.rangebar.RangeBar rb;
    public static Spinner sp;
    ArrayList<String> sample=new ArrayList<>();
    public static  ArrayList<String> myArrayList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsactivity);
        search=(ImageView)findViewById(R.id.imghr);
        back =(ImageView)findViewById(R.id.back);
        ll = (LinearLayout) findViewById(R.id.llforcb2);
        rb = (com.appyvet.rangebar.RangeBar) findViewById(R.id.rangebar);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(Settingsactivity.this);
        edit22 = prefs2.edit();
        sp=(Spinner)findViewById(R.id.spoo);
        interested.clear();
        parsing_for_settings.parsing(Settingsactivity.this, "settings");
        rb.setSeekPinByValue(Integer.parseInt(prefs2.getString("distance", "50")));
        int size=prefs2.getInt("size", 0);




        for(int j=0;j<size;j++)
        {
            sample.add(prefs2.getString("interested"+j,""));
        }
        Log.e("fdfdfdfd",""+sample);
        for(int i=0;i<cb.length;i++){

            ll.addView(ordersview(R.layout.layoutforcb,cb[i],sample.get(i)));
        }

        rb.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                edit22.putString("distance", ""+rightPinValue);
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit22.putString("category_id", "" + parsing_for_settings.cityid.get(position));
                edit22.putString("category_id11", "" +position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edit22.putString("category_id", "" + prefs2.getString("category_id",parsing_for_settings.cityid.get(0)));
                edit22.putString("category_id11", "" + prefs2.getString("category_id11","0"));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for(int i = 0 ; i<ll.getChildCount(); i++){

                    View vv = ll.getChildAt(i);
                Log.e("size",""+ll.getChildCount());
                    CheckBox tvt = (CheckBox) vv.findViewById(R.id.checkBox2);

                    if(tvt.isChecked()){
                        interested.add(i,tvt.getText()+"");
                    }
                    else {
                        interested.add(i,"");
                    }

                }



                for(int i=0;i<interested.size();i++)
                {
                    edit22.putString("interested"+i, interested.get(i));

                }
                edit22.putInt("size", interested.size());
                edit22.commit();
                overridePendingTransition(R.anim.slideoutup, 0);
                Settingsactivity.this.finish();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0 ; i<ll.getChildCount(); i++){

                    View vv = ll.getChildAt(i);

                    CheckBox tvt = (CheckBox) vv.findViewById(R.id.checkBox2);

                    if(tvt.isChecked()){
                        interested.add(i,tvt.getText()+"");
                    }
                    else {
                        interested.add(i,"");
                    }

                }



                for(int i=0;i<interested.size();i++)
                {
                    edit22.putString("interested"+i, interested.get(i));

                }
                edit22.putInt("size",interested.size());

                //edit22.putString("distance", "50");


                edit22.commit();
                Intent i2 = new Intent(Settingsactivity.this, MainActivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
                MainActivity.mainact.finish();
                Settingsactivity.this.finish();
            }
        });

    }
    private View ordersview(int layout_name, String cls, String sample) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Settingsactivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);

        final CheckBox cb = (CheckBox) addView.findViewById(R.id.checkBox2);
        if(sample.equals("")){
            cb.setChecked(false);
        }
        else {
            cb.setChecked(true);
        }
        cb.setText(cls);

        return addView ;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Settingsactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Settingsactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Settingsactivity.this.getWindow();
             //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
