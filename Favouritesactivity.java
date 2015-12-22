package com.apporio.onetap;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.onetap.adapter.myfavouritesadapter;
import com.apporio.onetap.parsing.parsingfordofav;
import com.apporio.onetap.parsing.parsingforviewfav;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class Favouritesactivity extends AppCompatActivity {
    public  static ListView lv;
    public  int iiii=0;
    public  static ImageView search;
    String ds[]={"1","2","3"};
    ArrayList<String> ds2 = new ArrayList<>();
    public static ImageView back;
    public  static SharedPreferences prefs;
   public static myfavouritesadapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouritesactivity);
        prefs = PreferenceManager.getDefaultSharedPreferences(Favouritesactivity.this);

        lv=(ListView)findViewById(R.id.listView);
        search=(ImageView)findViewById(R.id.imghr);
        back =(ImageView)findViewById(R.id.back);
        for(int h=0;h<ds.length;h++){
            ds2.add(ds[h]);
        }
        parsingforviewfav.parsing(Favouritesactivity.this,prefs.getString("user_id", null));
//        final myfavouritesadapter adp = new myfavouritesadapter(Favouritesactivity.this,ds2);
//        lv.setAdapter(adp);
        final  SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(lv),
                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                if(iiii==0) {
                                    iiii++;
                                    Toast.makeText(Favouritesactivity.this, "Swipe again to dismiss !!!!", LENGTH_SHORT).show();
                                }
                                return true;
                            }

                            @Override
                            public void onDismiss(ListViewAdapter view, int position) {

                                parsingfordofav.parsing(Favouritesactivity.this, prefs.getString("user_id", null), parsingforviewfav.product_id.get(position), "0");
                               adp.remove(position);
                            }
                        });
        lv.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        lv.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss();
                } else {
                    Toast.makeText(Favouritesactivity.this, "Position " + position, LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                overridePendingTransition(R.anim.slideoutup, 0);
                Favouritesactivity.this.finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(Favouritesactivity.this, MainActivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
                MainActivity.mainact.finish();
                Favouritesactivity.this.finish();
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Favouritesactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Favouritesactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Favouritesactivity.this.getWindow();
          //  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
