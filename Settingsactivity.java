package com.apporio.onetap;


        import android.annotation.TargetApi;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Build;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.Spinner;

        import com.apporio.onetap.parsing.parsing_for_settings;

public class Settingsactivity extends Activity {




    public  static ImageView search;
    public  static ArrayAdapter adp;
    String []cb={"Low Cal Food","Morning Coffee","Fitness Food","Brunch"};
//    String []cities={"Aberdeen","Armagh","Bangor","Bath",
//            "Belfast","Birmingham","Bradford","Brighton and Hove",
//            "Derby","Cambridge","Canterbury","Cardiff","Carlisle","Chester","Chichester","Coventry",
//            "Aberdeen","Dundee","Durham","Edinburgh","fly","Glasgow","Gloucester","Hereford",
//            "Inverness","Kingston","Lancaster","Leeds","Leicester","Lichfield","Lincoln","Lisburn",
//            "Liverpool","London","Londonderry","Manchester","Newcastle","Newport","Newry","Norwich",
//            "Nottingham","Oxford","Peterborough","Plymouth","Portsmouth","Preston","Ripon","Salford",};

    public static ImageView back;
    LinearLayout ll;
    public static Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsactivity);
        search=(ImageView)findViewById(R.id.imghr);
        back =(ImageView)findViewById(R.id.back);
        ll = (LinearLayout) findViewById(R.id.llforcb2);
        sp=(Spinner)findViewById(R.id.spoo);
        parsing_for_settings.parsing(Settingsactivity.this);
        for(int i=0;i<cb.length;i++){

            ll.addView(ordersview(R.layout.layoutforcb,cb[i]));
        }




        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                overridePendingTransition(R.anim.slideoutup, 0);
                Settingsactivity.this.finish();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(Settingsactivity.this, MainActivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
                MainActivity.mainact.finish();
                Settingsactivity.this.finish();
            }
        });

    }
    private View ordersview(int layout_name, String cls) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Settingsactivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final CheckBox cb = (CheckBox) addView.findViewById(R.id.checkBox2);

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
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
