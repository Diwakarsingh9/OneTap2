package com.apporio.onetap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.apporio.onetap.parsing.parsing_forgotpassword;
import com.apporio.onetap.parsing.parsingforlogin;

public class Forgotpassword extends Activity {
    public static EditText username;
    public static TextView submit;
    public static ImageView back,emailval;
    public static View v1,v2;
    public static Forgotpassword log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        log=Forgotpassword.this;
        username = (EditText)findViewById(R.id.username);
        submit = (TextView)findViewById(R.id.login34);
        back = (ImageView)findViewById(R.id.back);
        v1 = (View)findViewById(R.id.view11);
        v2 = (View)findViewById(R.id.view12);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    v1.setVisibility(View.GONE);
                    v2.setVisibility(View.VISIBLE);
                } else {
                    v1.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.GONE);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

              Forgotpassword.this.finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(getApplicationContext(), "loging in...", Toast.LENGTH_SHORT).show();
                Handler handler1 = new Handler();

                handler1.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        parsing_forgotpassword.parsing(Forgotpassword.this,username.getText().toString().trim());

                    }
                }, 1000);

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Forgotpassword.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Forgotpassword.this.getResources().getColor(R.color.example_color));
        } else {
            Window window = Forgotpassword.this.getWindow();
            // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
