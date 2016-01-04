package com.apporio.onetap;

import android.annotation.TargetApi;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.onetap.database.CartEvent;
import com.apporio.onetap.fragment.Base_fragment;
import com.apporio.onetap.fragment.Base_fragmentImage;

import com.apporio.onetap.settergetter.Inner_Restaurant_id;
import com.apporio.onetap.settergetter.Restaurant_id_outer;
import com.apporio.onetap.singleton.VolleySingleton;
import com.apporio.onetap.urlapi.Api_s;
import com.apporio.onetap.viewpagertransformer.ZoomOutPageTransformer;
import com.astuetz.PagerSlidingTabStrip;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {



    //save our header or result
    public static RequestQueue queue;
    public static StringRequest sr2;
    public static List<Inner_Restaurant_id> data_list1;
    public static ArrayList<String> rest_id = new ArrayList<String>();
    public static ArrayList<String> rest_name = new ArrayList<String>();
    public static ArrayList<String> rest_address = new ArrayList<String>();
    public static ArrayList<String> rest_logo = new ArrayList<String>();
    public static ArrayList<String> rest_email = new ArrayList<String>();
    public static ArrayList<String> rest_phn_no = new ArrayList<String>();

    public static Context ctc22;

    public String[] rest_idarray,titles_array;
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    public static String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private String profile;        //int Resource for header view profile picture
    private String email;       //String Resource for header view email

    public static Context ctc2;
    public static ViewPager viewPager,viewpagerforimagetransition;
    PagerSlidingTabStrip tabs;
    public static Context ctc;
    private static String mCurrentPhotoPath;
    public static String NAME = "Sam Wanderwolf";
    public static String EMAIL = "apporio@abc.com";
    public static String PROFILE = "";
    private Toolbar toolbar;
    String email1;
    String emailPattern;
    String cuisines[]={"Italian",
            "Mexican",
            "Chinese",
            "Indian"
            ,"Austrian"
            ,"German"};
    public  static ViewFlipper viewFlipper;
                    // Declaring the Toolbar Object
//    public  static  int gallery_grid_Images[]={R.drawable.actionbarimage1,R.drawable.actionbarimage2,
//                            R.drawable.actionbarimage3,R.drawable.actionbarimage1,R.drawable.actionbarimage2,
//                            R.drawable.actionbarimage1,R.drawable.actionbarimage2};
    public static RecyclerView mRecyclerView;                           // Declaring RecyclerView
    public static RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    public static RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    public static DrawerLayout Drawer;
    public static MainActivity mainact;
    SharedPreferences prefs;
    public static String TITLES[] = {"Near By", "My Orders", "Favourites", "Profile","Settings","Log out"};
    public static int ICONS[] = {
            R.drawable.icon_city, R.drawable.ic_order,
            R.drawable.ic_favorites, R.drawable.ic_profile,   R.drawable.ic_settings, R.drawable.ic_logout};
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    static DBManager dbm;
    private EventBus bus = EventBus.getDefault();
    public static Button cancel2;
    public static ImageView cart,filter;
    View layout, layout2;
    public static EditText search;
    public static TextView text, total_item, nameuser, confirm, text22, cabtype, couponsavailable;
    Typeface font;
    Bitmap bitmap1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        nameuser =(TextView)findViewById(R.id.nameuser);
        prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
       nameuser.setText("Hi " + prefs.getString("fname", null) + ",");
        NAME=prefs.getString("fname",null)+" "+prefs.getString("lname",null);
        EMAIL=prefs.getString("email", null);
       PROFILE= prefs.getString("image", "");

        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
       filter =(ImageView)findViewById(R.id.filter);
        total_item =(TextView)findViewById(R.id.total_item);
//        backgroundchange.setImageResource(gallery_grid_Images[0]);
        viewPager =(ViewPager)findViewById(R.id.view);
        search =(EditText)findViewById(R.id.searchbar);
//        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), rest_name));
        viewpagerforimagetransition =(ViewPager)findViewById(R.id.view2);
//        viewpagerforimagetransition.setAdapter(new MyPagerAdapter2(getSupportFragmentManager(), rest_name));
        // lv=(ListView)findViewById(R.id.listView22);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setIndicatorHeight(8);
        tabs.setTextColor(Color.parseColor("#ffffff"));
        tabs.setUnderlineColor(Color.parseColor("#ffffff"));
        tabs.setIndicatorColor(Color.parseColor("#ffffff"));
       // tabs.setDividerColor(Color.parseColor("#ffffff"));
        bus.register(this);

        dbm = new DBManager(MainActivity.this );
        dbm.clearCartTable();



        viewpagerforimagetransition.setPageTransformer(true, new ZoomOutPageTransformer());
        parsing(MainActivity.this);
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    viewpagerforimagetransition.setCurrentItem(position);
               // ImageViewAnimatedChange(MainActivity.this,backgroundchange,gallery_grid_Images[position]);
               // backgroundchange.setImageResource(gallery_grid_Images[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        setSupportActionBar(toolbar);
        ctc = getApplicationContext();
        mainact = MainActivity.this;
        cart =(ImageView)findViewById(R.id.cartimg);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, Cartactivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, Filteractivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });
//        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
//        for(int i=0;i<gallery_grid_Images.length;i++)
//        {
//            //  This will create dynamic image view and add them to ViewFlipper
//            setFlipperImage(gallery_grid_Images[i]);
//
//        }
//        viewFlipper.startFlipping();
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.download);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, Drawer, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        };

        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Drawer.openDrawer(Gravity.LEFT);
            }
        });

        //cd = new ConnectionDetector(ctc);
        //gps= new GPStracker(ctc);

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = MainActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = MainActivity.this.getWindow();
            // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)

        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    private void setFlipperImage(int res) {
        Log.i("Set Filpper Called", res+"");
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }

    @Override
    public void onDestroy() {
        bus.unregister(this);
        super.onDestroy();

    }
    public void onEvent(CartEvent event){

        total_item.setText("" + event.getDatatotalitems());


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        // Creating a ViewHolder which extends the RecyclerView View Holder
        // ViewHolder are used to to store the inflated views in order to recycle them

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            int Holderid;

            TextView textView;
            ImageView imageView;
            public ImageView profile11;
           public TextView Name;
            TextView email;
            LinearLayout itemll, llforprof;

            public ViewHolder(View itemView, int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
                super(itemView);


                // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

                if (ViewType == TYPE_ITEM) {
                    textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                    imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                    itemll = (LinearLayout) itemView.findViewById(R.id.llfornavi);
                    itemll.setOnClickListener(this);
                    // Creating ImageView object with the id of ImageView from item_row.xml
                    Holderid = 1;

                    // setting holder id as 1 as the object being populated are of type item row
                } else {
                    Typeface font = Typeface.createFromAsset(ctc.getAssets(), "fonts/Roboto-Regular.ttf");

                    Name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                    // Creating Text View object from header.xml for email
                    Name.setTypeface(font);

                    profile11 = (ImageView) itemView.findViewById(R.id.circleView);
                    llforprof = (LinearLayout) itemView.findViewById(R.id.llforprofile);
                    profile11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //MainActivity.Drawer.closeDrawer(Gravity.LEFT);
                            //  showcamerdialog();
                        }
                    });
                    llforprof.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent in = new Intent(ctc2, Profileactivity.class);
//                            ctc2.startActivity(in);
                        }
                    });

                    // Creating Image view object from header.xml for profile pic
                    Holderid = 0;
                }
            }


            @Override
            public void onClick(View v) {
                try {
                    if (mNavTitles[getPosition() - 1].equals("Near By")) {
                        Intent i2 = new Intent(MainActivity.this, Mapactivity.class);
                        startActivity(i2);
                        overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

                    } else if (mNavTitles[getPosition() - 1].equals("My Orders")) {
                        Intent i2 = new Intent(MainActivity.this, Orderactivity.class);
                        startActivity(i2);
                        overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Favourites")) {

                        Intent i2 = new Intent(MainActivity.this, Favouritesactivity.class);
                        startActivity(i2);
                        overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Profile")) {

                        Intent i2 = new Intent(MainActivity.this, Profileactivity.class);
                        startActivity(i2);
                        overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Settings")) {



                        Intent i2 = new Intent(MainActivity.this, Settingsactivity.class);
                        startActivity(i2);
                        overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Log out")) {



                        showlogoutdialog();

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Help")) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "abc@gmail.com", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report Issue Regarding Delivery");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));

                        emailIntent.setType("text/plain");
                    }

                    else{
                     //
                    }
                }catch (Exception e){
                    Log.e("ddddd", "" + e);
                }
            }

        }



        public MyAdapter(MainActivity mainActivity, String Titles[], int Icons[], String Name, String Email, String Profile){ // MyAdapter Constructor with titles and icons parameter
            // titles, icons, name, email, profile pic are passed from the main activity as we
            mNavTitles = Titles;                //have seen earlier
            mIcons = Icons;
            name = Name;
            email = Email;
            profile = Profile;
            ctc2= mainActivity;
            //here we assign those passed values to the values we declared here
            //in adapter


        }



        //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
        //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
        // if the viewType is TYPE_HEADER
        // and pass it to the view holder

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false); //Inflating the layout

                ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhItem; // Returning the created object

                //inflate your layout and pass it to view holder

            } else if (viewType == TYPE_HEADER) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

                ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhHeader; //returning the object created


            }
            return null;

        }

        //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
        // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
        // which view type is being created 1 for item row
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
                // position by 1 and pass it to the holder while setting the text and image
                holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
                holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
            }
            else{
                Picasso.with(MainActivity.this)
                        .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/" + profile)
                        .placeholder(R.drawable.download) // optional
                        .error(R.drawable.download)         // optional
                        .into(holder.profile11);
              //  holder.profile11.setImageBitmap(bitmap1);           // Similarly we set the resources for header view
                holder.Name.setText(name);

            }

        }

        // This method returns the number of items present in the list
        @Override
        public int getItemCount() {
            return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
        }


        // Witht the following method we check what type of view is being passed
        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;

            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        String messages;

        String[] Rest_id,TITLES ;

        public MyPagerAdapter(FragmentManager fm, String[] rest_name, String[] rest_id) {
            super(fm);
            this.TITLES=rest_name;
            this.Rest_id=rest_id;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            frag= Base_fragment.newInstance(MainActivity.this,Rest_id[position]);

            //io++;
            return frag;

        }

    }

    public class MyPagerAdapter2 extends FragmentStatePagerAdapter {
        String messages;
        ArrayList<String> TITLES = new ArrayList<>();

        public MyPagerAdapter2(FragmentManager fm, ArrayList<String> rest_name) {
            super(fm);
            this.TITLES=rest_name;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES.get(position);
        }

        @Override
        public int getCount() {
            return TITLES.size();
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            frag= Base_fragmentImage.newInstance(rest_logo.get(position));

            //io++;
            return frag;

        }

    }



    public  void showlogoutdialog() {

        final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforlogout);
        TextView yes = (TextView)dialog.findViewById(R.id.yes);
        TextView no = (TextView)dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                SharedPreferences.Editor edit2 = prefs.edit();
                edit2.putBoolean("pref_previously_started", Boolean.FALSE);
                edit2.commit();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();

                    LoginManager.getInstance().logOut();

                Intent in = new Intent(MainActivity.this,Loginscreenactivity.class);
                startActivity(in);
                MainActivity.this.finish();
                dialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }




    //////////////////////////////////////////////////////////////////////







        public void parsing(final Context activity) {
            final ProgressDialog pd = new ProgressDialog(activity);
            pd.setMessage("loading");
            ctc22 = activity;

            queue = VolleySingleton.getInstance(activity).getRequestQueue();


            String locationurl2 = Api_s.restaurant_all_products;
            locationurl2 = locationurl2.replace(" ", "%20");
            locationurl2 = locationurl2.replace("VIDEOS","VIDEO");
            Log.e("url", "" + locationurl2);

            sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
//
                    rest_id.clear();
                    rest_name.clear();
                    rest_address.clear();
                    rest_email.clear();
                    rest_logo.clear();
                    rest_phn_no.clear();
                    pd.dismiss();
//                Videoactivity.pb.setVisibility(View.GONE);
//                Videoactivity.llforlist.setVisibility(View.VISIBLE);

                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        Restaurant_id_outer received2 = new Restaurant_id_outer();
                        received2 = gson.fromJson(response, Restaurant_id_outer.class);

                        String result = received2.result;
                        if(result.equals("0")){
//                        Videoactivity.relatedsearch.setVisibility(View.GONE);
//                        Videoactivity.v111.setVisibility(View.GONE);
                        }
                        else {

                            data_list1=received2.inner_restaurant_id;
                            for (int i = 0; i < data_list1.size(); i++) {
                                rest_id.add(data_list1.get(i).restraurant_id);
                                rest_name.add(data_list1.get(i).restraurant_name);
                                rest_address.add(data_list1.get(i).address);
                                rest_logo.add(data_list1.get(i).logo);
                                rest_phn_no.add(data_list1.get(i).phone_number);
                                rest_email.add(data_list1.get(i).email);

                            }
                            titles_array = new String[rest_name.size()];
                            rest_idarray = new String[rest_id.size()];
                            titles_array = rest_name.toArray(titles_array);
                            rest_idarray = rest_id.toArray(rest_idarray);
                            Log.e("title", rest_id + "");
                           // Toast.makeText(activity, "" + rest_name, Toast.LENGTH_SHORT).show();
                            MainActivity.viewpagerforimagetransition.setAdapter(new MainActivity.MyPagerAdapter2(getSupportFragmentManager(), rest_name));
                            viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),titles_array,rest_idarray));
                            tabs.setViewPager(MainActivity.viewPager);
                        }
                        //adp.notifyDataSetChanged();


//                        Newsfragment.lv.setFocusable(false);


                    } catch (JsonSyntaxException e) {
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

