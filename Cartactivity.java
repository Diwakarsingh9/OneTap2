package com.apporio.onetap;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import views.CustomRatingBar;

/**
 * This is an example usage of the AnimatedExpandableListView class.
 *
 * It is an activity that holds a listview which is populated with 100 groups
 * where each group has from 1 to 100 children (so the first group will have one
 * child, the second will have two children and so on...).
 */
public class Cartactivity extends Activity {

    public static ArrayList<String> productid_arr = new ArrayList<String>();
    public static ArrayList<String>   productname_arr = new ArrayList<String>();
    public static ArrayList<String>   product_no_of_unit = new ArrayList<String>();
    public static ArrayList<String>   product_price_arr = new ArrayList<String>();
    public static ArrayList<String>   product_type = new ArrayList<String>();
    public static ArrayList<String>   product_rating = new ArrayList<String>();
    public static ArrayList<String>   product_image = new ArrayList<String>();
    String []foodtitle = {"Pasta al Dente x 2","Chicken Balsamico","Classic Caesar"};
    String []subtitle = {"with Black Olives","null","null"};
    String []foodtitlerate = {"16","14","27"};
    String []subtitlerate = {"0.90","null","null"};
    LinearLayout ll,ll22;
    DBManager dbm ;
    public static List<CartTable> ct;
    ValueAnimator mAnimator;
    String ds[]={"1","2","3","4"};
public static  TextView crdhed,cartitems;
    public static ImageView back,imghrtred,imghrtwyt,paid;
    String []cb={"Extra Cheese","Topping:Black Olivers","Topping:White Paprika","White Thin Crust"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartactivity);
        back =(ImageView)findViewById(R.id.back);
        paid =(ImageView)findViewById(R.id.imghr);
        ll = (LinearLayout) findViewById(R.id.llfortotalitems);
        ll22 = (LinearLayout) findViewById(R.id.llfortotalitems2);
        imghrtred = (ImageView) findViewById(R.id.imgheartred);
        imghrtwyt = (ImageView) findViewById(R.id.imgheartwhite);
        crdhed = (TextView) findViewById(R.id.carthead);
        cartitems = (TextView) findViewById(R.id.textitems);

        dbm = new DBManager(Cartactivity.this);
        cartitems.setText(""+dbm.totalNoofitemsincar()+" items");
        loadlistview();


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

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                overridePendingTransition(R.anim.slideoutup, 0);
                Cartactivity.this.finish();
            }
        });
        paid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent in = new Intent(Cartactivity.this, Paidactivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

            }
        });
        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.

    }

    private void loadlistview() {
        ct = dbm.getAllrows();

        product_price_arr.clear();
        productid_arr.clear();
        productname_arr.clear();
        product_no_of_unit.clear();
        product_type.clear();
        product_rating.clear();
        product_image.clear();
        for (int i = 0; i < ct.size(); i++) {
            productid_arr.add(ct.get(i).getProductId());
            productname_arr.add(ct.get(i).getFoodname());
            product_no_of_unit.add(ct.get(i).getFoodNoOfUnits());
            product_price_arr.add(ct.get(i).getFoodprice());
            product_type.add(ct.get(i).getFoodtype());
            product_rating.add(ct.get(i).getFoodrating());
            product_image.add(ct.get(i).getFoodimage());

        }

        for (int i = 0; i < ct.size(); i++) {

            ll22.addView(ordersviewmain(R.layout.itemlayoutforcartactivity, productname_arr.get(i),product_price_arr.get(i),
                    product_type.get(i),product_rating.get(i),product_image.get(i),product_no_of_unit.get(i) ,cb));

        }
        for (int i = 0; i <= ct.size(); i++) {
            if (i ==ct.size() ) {
                ll.addView(ordersview2(R.layout.layoutforgrandtotal,dbm.calculationForGrossPrice()));
            } else {
                ll.addView(ordersview(R.layout.layoutforfooditemsadd,productname_arr.get(i),product_price_arr.get(i),productname_arr.get(i),product_price_arr.get(i),product_no_of_unit.get(i) ));
            }
        }
    }

    private View ordersview2(int layout_name, double v) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Cartactivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);

        final TextView totalprice= (TextView) addView.findViewById(R.id.tp);
        totalprice.setText(""+v);


        return addView ;
    }

    private View ordersview(int layoutforfooditemsadd, String foodtitle, String foodtitlerate, String subtitle, String subtitlerate, String s) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Cartactivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layoutforfooditemsadd, null);
        final TextView foodt = (TextView) addView.findViewById(R.id.titlet);
        final TextView foodr = (TextView) addView.findViewById(R.id.foodr);
        final TextView subt = (TextView) addView.findViewById(R.id.subtitlet);
        final TextView subr = (TextView) addView.findViewById(R.id.subr);
        final LinearLayout llforsub = (LinearLayout) addView.findViewById(R.id.llforsubprice);
        foodt.setText(foodtitle+ " x " + s);
        foodr.setText(""+(Integer.parseInt(foodtitlerate)*Integer.parseInt(s)));
        subt.setText(subtitle);
        subr.setText(subtitlerate);

            subt.setVisibility(View.GONE);
            llforsub.setVisibility(View.GONE);

        return addView ;
    }

    private View ordersviewmain(int layoutforfooditemsadd, String foodtitle, String s1, String s2, String s3, String s4, String s, String[] cb) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Cartactivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int chck=0;
        final View addView = layoutInflater.inflate(layoutforfooditemsadd, null);
        final TextView foodt = (TextView) addView.findViewById(R.id.itemTextView);
        final TextView type = (TextView) addView.findViewById(R.id.typecart);
        final TextView price = (TextView) addView.findViewById(R.id.price);
        final ImageView imgprof = (ImageView) addView.findViewById(R.id.imageView);
        final CustomRatingBar rb = (CustomRatingBar) addView.findViewById(R.id.rbcart);
       final  LinearLayout llchckbox =(LinearLayout)addView.findViewById(R.id.llforcb);
        final ImageView img = (ImageView) addView.findViewById(R.id.addons);
        llchckbox.setVisibility(View.GONE);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llchckbox.getVisibility() == View.GONE) {
                    llchckbox.setVisibility(View.VISIBLE);
                    img.setImageResource(R.drawable.ic_addons_close);

                } else {
                    llchckbox.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.ic_addons);

                }
            }
        });
        for(int j=0;j<cb.length;j++) {
            llchckbox.addView(orderchckbox(R.layout.layoutforcb, cb[j]));
        }
        foodt.setText(foodtitle+" x "+s );
        type.setText("" + s2);
        price.setText("" + (Integer.parseInt(s)*Integer.parseInt(s1)));
        rb.setScore(Float.parseFloat("" + s3));


        //  Log.e("bahjd",""+images);
        Picasso.with(Cartactivity.this)
                .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/"+ s4.replace("//","/"))
                .placeholder(R.drawable.logofork)
                .error(R.drawable.logofork)
                .into(imgprof);


        return addView ;
    }
    private View orderchckbox(int layoutforfooditemsadd, String foodtitle) {

        LayoutInflater layoutInflater =
                (LayoutInflater)Cartactivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layoutforfooditemsadd, null);
        final CheckBox foodt = (CheckBox) addView.findViewById(R.id.checkBox2);


        foodt.setText(foodtitle);




        return addView ;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Cartactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Cartactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Cartactivity.this.getWindow();
           // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


//    private ValueAnimator slideAnimator(int start, int end, final LinearLayout llchckbox) {
//
//        ValueAnimator animator = ValueAnimator.ofInt(start, end);
//
//
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                //Update Height
//                int value = (Integer) valueAnimator.getAnimatedValue();
//
//                ViewGroup.LayoutParams layoutParams = llchckbox.getLayoutParams();
//                layoutParams.height = value;
//                llchckbox.setLayoutParams(layoutParams);
//            }
//        });
//        return animator;
//    }
//
//    private void expand(LinearLayout llchckbox) {
//        //set Visible
//        llchckbox.setVisibility(View.VISIBLE);
//        mAnimator.start();
//    }
//
//    private void collapse(final LinearLayout llchckbox) {
//        int finalHeight = llchckbox.getHeight();
//
//        ValueAnimator mAnimator = slideAnimator(finalHeight, 0,llchckbox);
//
//        mAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                //Height=0, but it set visibility to GONE
//                llchckbox.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animator) {
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//            }
//        });
//        mAnimator.start();
//    }
}
