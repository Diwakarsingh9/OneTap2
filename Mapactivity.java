package com.apporio.onetap;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.onetap.R;
import com.apporio.onetap.checker.ConnectionDetector;
import com.apporio.onetap.checker.GPStracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Hashtable;

public class Mapactivity extends AppCompatActivity {
    public static int distance;
    public static Marker now;
    public static MapView mapView;
    public static GoogleMap map;
    public static Circle mapCircle;
    public static GPStracker gps;
    public static ProgressBar pb ;

    public static Boolean isInternetPresent = false;
    public static ConnectionDetector cd;
    public static ImageView back;
    public static Double currentlat, currentlong;
    public  static ArrayList<String> name = new ArrayList<String>();
    public  static ArrayList<String[]> latlng = new ArrayList<>();
    public  static  ArrayList<String> latitude = new ArrayList<>();
    public  static ArrayList<String> longitude = new ArrayList<>();
    public static  String name1 []={"1","2","3","4","5"};
    public static  String lat2 []={"28.595520","28.5465205","28.5655205","28.5555205","28.5455205"};
    public static  String long2 []={"77.23195220000001","77.25195220000001","77.26195220000001","77.22195220000001","77.27195220000001"};

    private View view;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public static Context ctc;
    private Marker marker;

    private Hashtable<String, String> markers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapactivity);
        ctc=getApplicationContext();
        pb = (ProgressBar)findViewById(R.id.progressBarinsplash);
        cd = new ConnectionDetector(ctc);
        gps= new GPStracker(ctc);
        mapView = (MapView)findViewById(R.id.mapgh);
        mapView.onCreate(savedInstanceState);
        back =(ImageView)findViewById(R.id.back);
        for(int i =0;i<name1.length;i++){
            name.add(name1[i]);
            latitude.add(lat2[i]);
            longitude.add(long2[i]);
        }
        //currentlat= gps.getLatitude();
        //currentlong= gps.getLongitude();

        currentlat=  28.5755205;
        currentlong= 77.24195220000001;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                overridePendingTransition(R.anim.slideoutup,0);
                Mapactivity.this.finish();
            }
        });
        initImageLoader();

        imageLoader = ImageLoader.getInstance();

        markers = new Hashtable<String, String>();


        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.stub)		//	Display Stub Image
                .showImageForEmptyUri(R.drawable.stub)	//	If Empty image found
                .cacheInMemory()
                .cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565).build();

        getthetracker();
    }

    public void getthetracker() {

        MapsInitializer.initialize(ctc);
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(ctc)) {
            case ConnectionResult.SUCCESS:

                //Toast.makeText(ctc, "tracker", Toast.LENGTH_SHORT).show();
                // Gets to GoogleMap from the MapView and does initialization stuff
                if (mapView != null) {
                    map = mapView.getMap();
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    map.setMyLocationEnabled(true);
                    gps = new GPStracker(ctc, map);
                    CameraUpdate center =
                            CameraUpdateFactory.newLatLng(new LatLng(currentlat,
                                    currentlong));
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);

                    map.moveCamera(center);
                    map.animateCamera(zoom);
                    map.addCircle(new CircleOptions().center(new LatLng(currentlat,
                            currentlong)).radius(5 * 1000 * 1.2).strokeColor(Color.RED).strokeWidth(4));

                    for (int k = 0; k < name.size(); k++) {
                        //Bitmap bmp = tc.makeIcon(""+ name.get(k));
                        map.setInfoWindowAdapter(new CustomInfoWindowAdapter());
                        now = map.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.parseDouble((latitude.get(k)).toString()), Double.parseDouble((longitude.get(k)).toString())))
                                .title("Restro" + name.get(k)).icon(BitmapDescriptorFactory.fromResource(R.drawable.markerrestro)));
                        map.setMyLocationEnabled(true);
                        String[] parts = now.getId().split("m");
                        String part1 = parts[1];
                        //Toast.makeText(getApplicationContext(),""+part1,Toast.LENGTH_SHORT).show();
                        markers.put(now.getId(), "http://www.touristtiger.com/wp-content/uploads/2013/06/Famous-Places-in-Italy-Spaghetti.png");
                        map.setInfoWindowAdapter(new CustomInfoWindowAdapter());

                        // now.showInfoWindow();
                    }
                    Marker now1 = map.addMarker(new MarkerOptions()
                            .position(new LatLng(currentlat, currentlong)).title("You are here").icon(BitmapDescriptorFactory.fromResource(R.drawable.markerforcurrent)));
                    map.setMyLocationEnabled(true);
                    //animateMarker(now1, new LatLng(currentlat, currentlong),true);
                    now1.showInfoWindow();

                    isInternetPresent =cd.isConnectingToInternet();

                    // check for Internet status
                    if (isInternetPresent) {

                    } else {
                        // Internet connection is not present
                        // Ask user to connect to Internet
                        Toast.makeText(ctc, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(ctc, "SERVICE MISSING", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(ctc, "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(ctc, GooglePlayServicesUtil.isGooglePlayServicesAvailable(ctc), Toast.LENGTH_SHORT).show();
        }




    }
    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = map.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }
    @Override
    public void onResume() {
        //Toast.makeText(getActivity(), "resume", Toast.LENGTH_SHORT).show();
        mapView.onResume();
        super.onResume();
        getthetracker();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Mapactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Mapactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Mapactivity.this.getWindow();
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private View view;

        public CustomInfoWindowAdapter() {
            view = getLayoutInflater().inflate(R.layout.custom_info_window,
                    null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            if (Mapactivity.this.marker != null
                    && Mapactivity.this.marker.isInfoWindowShown()) {
                Mapactivity.this.marker.hideInfoWindow();
                Mapactivity.this.marker.showInfoWindow();
            }
            return null;
        }

        @Override
        public View getInfoWindow(final Marker marker) {
            Mapactivity.this.marker = marker;

            String url = null;

            if (marker.getId() != null && markers != null && markers.size() > 0) {
                if ( markers.get(marker.getId()) != null &&
                        markers.get(marker.getId()) != null) {
                    url = markers.get(marker.getId());
                }
            }
            final ImageView image = ((ImageView) view.findViewById(R.id.badge));

            if (url != null && !url.equalsIgnoreCase("null")
                    && !url.equalsIgnoreCase("")) {
                imageLoader.displayImage(url, image, options,
                        new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri,
                                                          View view, Bitmap loadedImage) {
                                super.onLoadingComplete(imageUri, view,
                                        loadedImage);
                                getInfoContents(marker);
                            }
                        });
            } else {
                image.setImageResource(R.drawable.men);
            }

            final String title = marker.getTitle();
            final TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (title != null) {
                titleUi.setText(title);
            } else {
                titleUi.setText("");
            }

           /* final String snippet = marker.getSnippet();
            final TextView snippetUi = ((TextView) view
                    .findViewById(R.id.snippet));
            if (snippet != null) {
                snippetUi.setText(snippet);
            } else {
                snippetUi.setText("");
            }*/

            return view;
        }
    }

    private void initImageLoader() {
        int memoryCacheSize;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            int memClass = ((ActivityManager)
                    getSystemService(Context.ACTIVITY_SERVICE))
                    .getMemoryClass();
            memoryCacheSize = (memClass / 8) * 1024 * 1024;
        } else {
            memoryCacheSize = 2 * 1024 * 1024;
        }

        final ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheSize(memoryCacheSize)
                .memoryCache(new FIFOLimitedMemoryCache(memoryCacheSize-1000000))
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging()
                .build();

        ImageLoader.getInstance().init(config);
    }

}
