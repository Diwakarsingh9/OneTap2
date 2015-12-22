package com.apporio.onetap.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.apporio.onetap.R;
import com.apporio.onetap.adapter.Mainlistviewadapter;
import com.squareup.picasso.Picasso;


public class Base_fragmentImage extends Fragment {
    static String ds2;
    int i=0;
    public  static  int y;
    public  static String d[]={"1","2","3","4","5"};
    public  static String d2[]={"0","0","0","0","0"};
    public  static Mainlistviewadapter adp;
    public  static ImageView img;
    String pos;



        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_blank2, null);
        img=(ImageView)root.findViewById(R.id.imgforimg);
        pos= getArguments().getString("msg22");

            Picasso.with(getActivity())
                    .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/" + pos)
                    .placeholder(R.drawable.stub) // optional
                    .error(R.drawable.stub)         // optional
                    .into(img);

            Log.e("gfgfg",""+"http://keshavgoyal.com/realtysingh/" + pos);
           // img.setImageResource(pos);
           // Log.e("poss22222", ""+pos);



        return root;
    }




    public static Base_fragmentImage newInstance(String position) {
        Base_fragmentImage f = new Base_fragmentImage();
        Bundle b = new Bundle();
        b.putString("msg22", position);
        //Log.e("possss",""+position);
        f.setArguments(b);

        return f;
    }
}


