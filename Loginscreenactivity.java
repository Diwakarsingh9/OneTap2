package com.apporio.onetap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.apporio.onetap.R;
import com.apporio.onetap.parsing.parsing_for_settings;
import com.apporio.onetap.parsing.parsingforlogin;
import com.apporio.onetap.parsing.parsingforsignup;

import org.json.JSONException;
import org.json.JSONObject;

public class Loginscreenactivity extends Activity {

    public static TextView logintry, login, signup, register, passalert, frgtpass;
    public static ImageView back, emailval;
    public static Spinner sp22;
    public static Loginscreenactivity log;
    String email1,emailfb, emailid;
    String emailPattern;
    private ProfileTracker mProfileTracker;
    CallbackManager callbackManager;
    public static LoginButton fbbutton;
    public static EditText username, password, fname, lname, mob, repass;
    public static View v1, v2, v3, v4, v5, v7, v9, v11;
    private static final String[] m_Codes = {"376", "971",
            "93", "355", "374", "599", "244", "672", "54", "43", "61", "297",
            "994", "387", "880", "32", "226",
            "359", "973", "257", "229", "590", "673", "591", "55", "975", "267", "375", "501",
            "1", "61", "243", "236", "242", "41", "225", "682", "56", "237", "86", "57",
            "506", "53", "238", "61", "357", "420",
            "49", "253", "45", "213", "593", "372", "20", "291", "34", "251", "358", "679",
            "500", "691", "298", "33", "241", "44", "995", "233", "350", "299", "220", "224", "240", "30", "502", "245", "592", "852", "504", "385", "509", "36", "62", "353", "972", "44", "91", "964",
            "98", "39", "962", "81", "254", "996", "855", "686", "269", "850", "82", "965", "7", "856", "961", "423", "94", "231", "266", "370", "352", "371", "218", "212", "377", "373", "382", "261", "692", "389", "223", "95", "976", "853", "222", "356",
            "230", "960", "265", "52", "60", "258", "264", "687", "227", "234", "505", "31", "47", "977", "674", "683", "64", "968", "507", "51", "689", "675", "63", "92", "48", "508", "870", "1", "351", "680", "595", "974", "40", "381", "7", "250", "966", "677", "248", "249", "46", "65", "290", "386", "421", "232", "378", "221", "252", "597", "239", "503", "963", "268", "235", "228", "66", "992", "690", "670", "993", "216", "676", "90", "688", "886", "255", "380", "256", "1", "598", "998", "39", "58", "84", "678", "681", "685", "967", "262", "27", "260", "263"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreenactivity);
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);
        flipper.startFlipping();
        ViewFlipper flipper1 = (ViewFlipper) findViewById(R.id.flipper2);
        flipper1.startFlipping();
        log = Loginscreenactivity.this;
        callbackManager = CallbackManager.Factory.create();
        logintry = (TextView) findViewById(R.id.login22);
        register = (TextView) findViewById(R.id.register);
        fbbutton = (LoginButton) findViewById(R.id.login_button);
        fbbutton.setReadPermissions("public_profile email");
        logintry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler11 = new Handler();
                handler11.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                        showEditpicdialog();

                    }
                }, 1000);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler12 = new Handler();
                handler12.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                        showregisterdialog();
                    }
                }, 1000);

            }
        });
        fbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler12 = new Handler();
                handler12.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                    }
                }, 1000);

            }
        });
        fbbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if (AccessToken.getCurrentAccessToken() != null) {
                    RequestData();
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Loginscreenactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));
        } else {
            Window window = Loginscreenactivity.this.getWindow();
           // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void showEditpicdialog() {

        final Dialog dialog = new Dialog(Loginscreenactivity.this, android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforlogin);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        back = (ImageView)dialog.findViewById(R.id.back);
        login = (TextView)dialog.findViewById(R.id.login34);
        frgtpass = (TextView)dialog.findViewById(R.id.frgtpass);
        username = (EditText)dialog.findViewById(R.id.username);
        password = (EditText)dialog.findViewById(R.id.password);
        v1 = (View)dialog.findViewById(R.id.view11);
        v2 = (View)dialog.findViewById(R.id.view12);
        v3 = (View)dialog.findViewById(R.id.view21);
        v4 = (View)dialog.findViewById(R.id.view22);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v1.setVisibility(View.GONE);
                    v2.setVisibility(View.VISIBLE);
                }
                else{
                    v1.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.GONE);
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    v3.setVisibility(View.GONE);
                    v4.setVisibility(View.VISIBLE);
                } else {
                    v3.setVisibility(View.VISIBLE);
                    v4.setVisibility(View.GONE);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(getApplicationContext(), "loging in...", Toast.LENGTH_SHORT).show();
                Handler handler1 = new Handler();

                handler1.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        parsingforlogin.parsing(Loginscreenactivity.this, username.getText().toString().trim(),
                                password.getText().toString().trim());

                    }
                }, 1000);

            }
        });

        frgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(getApplicationContext(), "loging in...", Toast.LENGTH_SHORT).show();
                Handler handler1 = new Handler();

                handler1.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        Intent in = new Intent(Loginscreenactivity.this, Forgotpassword.class);
                        startActivity(in);

                    }
                }, 1000);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public  void showregisterdialog() {

        final Dialog dialog = new Dialog(Loginscreenactivity.this,android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_registration_screen);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        back = (ImageView)dialog.findViewById(R.id.back);
        signup = (TextView)dialog.findViewById(R.id.signup);
        passalert = (TextView)dialog.findViewById(R.id.passalert);
        username = (EditText)dialog.findViewById(R.id.username);
        password = (EditText)dialog.findViewById(R.id.password);
        fname = (EditText)dialog.findViewById(R.id.firstname);
        lname = (EditText)dialog.findViewById(R.id.lastname);
        repass = (EditText)dialog.findViewById(R.id.repass);
        mob = (EditText)dialog.findViewById(R.id.mob);
        sp22 = (Spinner)dialog.findViewById(R.id.spinner);
        emailval=(ImageView)dialog.findViewById(R.id.emailvalidat);
        v1 = (View)dialog.findViewById(R.id.view11);

        v3 = (View)dialog.findViewById(R.id.view21);

        v5 = (View)dialog.findViewById(R.id.view31);

        v7 = (View)dialog.findViewById(R.id.view41);

        v9 = (View)dialog.findViewById(R.id.view51);

        v11= (View)dialog.findViewById(R.id.view61);

        ArrayAdapter adp = new ArrayAdapter(getApplicationContext(),R.layout.itemlayoutformobile,R.id.mobcode,m_Codes);
        sp22.setAdapter(adp);


        fname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    v1.setBackgroundColor(Color.parseColor("#bd362b"));
                } else {
                    v1.setBackgroundColor(Color.parseColor("#ffffff"));

                }
            }
        });
        lname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    v3.setBackgroundColor(Color.parseColor("#bd362b"));
                } else {
                    v3.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });

        emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-zA-Z0-9._-]+";

        username .addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                email1 = username.getText().toString().trim();
                if (email1.matches(emailPattern) && s.length() > 0) {
                    emailval.setImageResource(R.drawable.success);


                } else {
                    emailval.setImageResource(R.drawable.cross);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v5.setBackgroundColor(Color.parseColor("#bd362b"));
                }
                else{
                    v5.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v7.setBackgroundColor(Color.parseColor("#bd362b"));
                }
                else{
                    v7.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
        repass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v9.setBackgroundColor(Color.parseColor("#bd362b"));
                }
                else{

                    v9.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
        mob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v11.setBackgroundColor(Color.parseColor("#bd362b"));
                }
                else{
                    v11.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Handler handler1 = new Handler();

                handler1.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                        if (!password.getText().toString().equals(repass.getText().toString())) {
                            passalert.setVisibility(View.VISIBLE);
                        } else {
                            passalert.setVisibility(View.GONE);
                            parsingforsignup.parsing(Loginscreenactivity.this, fname.getText().toString().trim(),
                                    lname.getText().toString().trim(),
                                    username.getText().toString().trim(),
                                    password.getText().toString().trim(),
                                    mob.getText().toString().trim(), sp22.getSelectedItem().toString().trim());

//                            sp22.getSelectedItem().toString().trim()+
                        }

                    }
                }, 1000);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void RequestData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                 JSONObject json = response.getJSONObject();
                try {
                    if(json != null){
                        Log.e("name", json.getString("name"));

                        Log.e("email", json.getString("email"));
                        Log.e("link", json.getString("link"));
                        Log.e("id", json.getString("id"));
                        emailfb=json.getString("email");
                        emailid=json.getString("id");
                        if(Profile.getCurrentProfile() == null) {
                            mProfileTracker = new ProfileTracker() {
                                @Override
                                protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                                    Log.e("facebook - profile", profile2.getFirstName());
                                    mProfileTracker.stopTracking();
                                    parsingforlogin.facebookparsing(Loginscreenactivity.this, profile2.getFirstName(), profile2.getLastName(),
                                            emailfb,emailid, "");
                                }
                            };
                            mProfileTracker.startTracking();
                        }
                        else {
                            Profile profile = Profile.getCurrentProfile();
                            Log.e("facebook - profile22", profile.getFirstName());
                            parsingforlogin.facebookparsing(Loginscreenactivity.this, profile.getFirstName(), profile.getLastName(),
                                    emailfb, emailid, "");
                        }


//                        Profile profile1 = Profile.getCurrentProfile();
//                       Log.e("firstname", "" + profile1.getFirstName());
//                        Log.e("lastname", "" + profile1.getLastName());
//                        Log.e("profile image", "" + profile1.getProfilePictureUri(40, 40));
//                        Log.e("profill",""+profile1);
//                        parsingforlogin.facebookparsing(Loginscreenactivity.this,profile1.getFirstName(),profile1.getLastName(),
//                                json.getString("email"), json.getString("id"),"");
                    }

                } catch (Exception e) {
                    Log.e("exception", ""+e);
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
