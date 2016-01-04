package com.apporio.onetap;





import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.widget.PopupMenuCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.apporio.onetap.directory.AlbumStorageDirFactory;
import com.apporio.onetap.directory.BaseAlbumDirFactory;
import com.apporio.onetap.directory.FroyoAlbumDirFactory;
import com.apporio.onetap.parsing.parsing_for_profile;
import com.apporio.onetap.urlapi.Api_s;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Profileactivity extends Activity {

    private static int RESULT_LOAD_IMG = 1;
    public  static Bitmap bitmap1;
    SharedPreferences prefs;
    String primary = "1";
    String laststr = "";
    String laststr2 = "";
    long totalSize =0;
//    private String mCurrentPhotoPath;
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    public static String imgDecodableString;
    public  static TextView save,nameuser;
    public static EditText fname,lname,mob,email,add1,add11,add111,add2,add22,add222;
    public static Spinner sp22;
    public static ImageView camera,remove_picture,dp,back,imgadd1,imgadd2;
    public static CheckBox ch1,ch2;
    private static final String[] m_Codes = { "376",            "971",
            "93",            "355",            "374",            "599",            "244",            "672",            "54",            "43",            "61",            "297",
            "994",            "387",            "880",            "32",            "226",
            "359",            "973",            "257",            "229",            "590",            "673",            "591",            "55",            "975",            "267",            "375",            "501",
            "1",            "61",            "243",            "236",            "242",            "41",            "225",            "682",            "56",            "237",            "86",            "57",
            "506",            "53",            "238",            "61",            "357",            "420",
            "49",            "253",            "45",            "213",            "593",            "372",            "20",            "291",            "34",            "251",            "358",            "679",
            "500",            "691",            "298",            "33",            "241",            "44",            "995",            "233",            "350",            "299",            "220",            "224",            "240",            "30",            "502",            "245",            "592",            "852",            "504",            "385",            "509",            "36",            "62",            "353",            "972",            "44",            "91",            "964",
            "98",            "39",            "962",            "81",            "254",            "996",            "855",            "686",            "269",            "850",            "82",            "965",            "7",            "856",            "961",            "423",            "94",            "231",            "266",            "370",            "352",            "371",            "218",            "212",            "377",          "373",            "382",            "261",            "692",            "389",            "223",            "95",            "976",            "853",            "222",            "356",
            "230",            "960",            "265",            "52",            "60",            "258",            "264",            "687",            "227",            "234",            "505",            "31",            "47",            "977",            "674",            "683",            "64",            "968",            "507",            "51",            "689",            "675",            "63",            "92",            "48",            "508",            "870",            "1",            "351",            "680",            "595",            "974",            "40",            "381",            "7",            "250",            "966",            "677",            "248",            "249",            "46",            "65",            "290",            "386",            "421",            "232",            "378",            "221",            "252",            "597",            "239",            "503",            "963",            "268",            "235",            "228",            "66",            "992",            "690",            "670",            "993",            "216",            "676",            "90",            "688",            "886",            "255",            "380",            "256",            "1",            "598",            "998",            "39",            "58",            "84",            "678",            "681",            "685",            "967",            "262",            "27",            "260",     "263"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);
         prefs = PreferenceManager.getDefaultSharedPreferences(Profileactivity.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
        } else {
            mAlbumStorageDirFactory = new BaseAlbumDirFactory();
        }

        fname = (EditText)findViewById(R.id.firstname1);
        fname.setText("" + prefs.getString("fname", null));
        lname = (EditText)findViewById(R.id.lastname1);
        lname.setText("" + prefs.getString("lname", null));
        email = (EditText)findViewById(R.id.email1);
        email.setText("" + prefs.getString("email", null));
        mob = (EditText)findViewById(R.id.mob1);
        mob.setText("" + prefs.getString("phone_no", null));
        add1 = (EditText)findViewById(R.id.add1);
        String str = "" + prefs.getString("address1", null);
        Log.e("gdgdfg",""+str);
        String[] splitStr = str.trim().split("\\s+");

        add11 = (EditText)findViewById(R.id.add11);
        add111 = (EditText)findViewById(R.id.add111);

        if(splitStr.length==0){
            //
        }
        else if(splitStr.length==1){
            add1.setText(""+splitStr[0]);
            add11.setText("");
            add111.setText("");
        }
        else if(splitStr.length==2){
            add1.setText(""+splitStr[0]+" "+splitStr[1]);
            add11.setText("");
            add111.setText("");
        }
        else if(splitStr.length==3){
            add1.setText(""+splitStr[0]+" "+splitStr[1]);
            add11.setText(""+splitStr[2]);
            add111.setText("");
        }
        else if(splitStr.length==4){
            add1.setText(""+splitStr[0]+" "+splitStr[1]);
            add11.setText(""+splitStr[2]+" "+splitStr[3]);
            add111.setText("");
        }
        else {
            for(int i=4;i<splitStr.length;i++){
                Log.e("loop",""+splitStr[i]);
                laststr=laststr+" "+(splitStr[i]);
            }
            Log.e("size",""+splitStr.length+" "+laststr);
            add1.setText(""+splitStr[0]+" "+splitStr[1]);
            add11.setText(""+splitStr[2]+" "+splitStr[3]);
            add111.setText(""+laststr);
        }


        add2 = (EditText)findViewById(R.id.add2);
        String str2 = "" + prefs.getString("address2", null);
        String[] splitStr2= str2.trim().split("\\s+");
        add22 = (EditText)findViewById(R.id.add22);
        add222 = (EditText)findViewById(R.id.add222);

        Log.e("gdgdfg",""+str2);
        if(splitStr2.length==0){
            //
        }
        else if(splitStr2.length==1){
            add2.setText(""+splitStr2[0]);
            add22.setText("");
            add222.setText("");
        }
        else if(splitStr2.length==2){
            add2.setText(""+splitStr2[0]+" "+splitStr2[1]);
            add22.setText("");
            add222.setText("");
        }
        else if(splitStr2.length==3){
            add2.setText(""+splitStr2[0]+" "+splitStr2[1]);
            add22.setText(""+splitStr2[2]);
            add222.setText("");
        }
        else if(splitStr2.length==4){
            add2.setText(""+splitStr2[0]+" "+splitStr2[1]);
            add22.setText(""+splitStr2[2]+" "+splitStr2[3]);
            add222.setText("");
        }
        else {
            for(int i=4;i<splitStr2.length;i++){

                laststr2=laststr2+" "+splitStr2[i];
            }
            add2.setText(""+splitStr2[0]+" "+splitStr2[1]);
            add22.setText(""+splitStr2[2]+" "+splitStr2[3]);
            add222.setText(""+laststr2);
        }



        sp22 = (Spinner)findViewById(R.id.spinner1);
        ch1=(CheckBox)findViewById(R.id.checkBox1233);
        ch2=(CheckBox)findViewById(R.id.checkBox12);
        camera=(ImageView)findViewById(R.id.camera);
        imgadd1=(ImageView)findViewById(R.id.imgaddress1);
        imgadd2=(ImageView)findViewById(R.id.imgaddress2);
        dp=(ImageView)findViewById(R.id.circleView);
        back =(ImageView)findViewById(R.id.back);
        save =(TextView)findViewById(R.id.save);
       // menimage =(ImageView)findViewById(R.id.circleView);
        nameuser =(TextView)findViewById(R.id.name);
        Picasso.with(Profileactivity.this)
                .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/" + prefs.getString("image", null))
                .placeholder(R.drawable.download) // optional
                .error(R.drawable.download)         // optional
                .into(dp);
        nameuser.setText(""+prefs.getString("fname", null)+" "+prefs.getString("lname", null));
        remove_picture=(ImageView)findViewById(R.id.remvepic);
        ArrayAdapter adp = new ArrayAdapter(getApplicationContext(),R.layout.itemlayoutformobile2,R.id.mobcode,m_Codes);
        sp22.setAdapter(adp);
        String primary22 = "" + prefs.getString("primary", null);
        if(primary22.equals("1")){
            ch1.setChecked(true);
            ch2.setChecked(false);
            imgadd1.setImageResource(R.drawable.ic_location_selected);
            imgadd2.setImageResource(R.drawable.ic_location_disabaled);
            primary="1";

        }
        else {
            ch2.setChecked(true);
            ch1.setChecked(false);
            imgadd1.setImageResource(R.drawable.ic_location_disabaled);
            imgadd2.setImageResource(R.drawable.ic_location_selected);
            primary="2";
        }

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                overridePendingTransition(R.anim.slideoutup, 0);
                Profileactivity.this.finish();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcamerdialog();
            }
        });
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch2.setChecked(false);
                imgadd1.setImageResource(R.drawable.ic_location_selected);
                imgadd2.setImageResource(R.drawable.ic_location_disabaled);
                primary="1";
            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch1.setChecked(false);
                imgadd1.setImageResource(R.drawable.ic_location_disabaled);
                imgadd2.setImageResource(R.drawable.ic_location_selected);
                primary="2";
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UploadFileToServer().execute();

                //Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
//                sp22.getSelectedItem().toString().trim()+
            }
        });
        remove_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Profileactivity.this, remove_picture);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.removepopup, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        dp.setImageResource(R.drawable.download);

                        imgDecodableString="";
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }


        });
    }
    public  void showcamerdialog() {

        final Dialog dialog = new Dialog(Profileactivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.camerdialog);
        Button cancel = (Button)dialog.findViewById(R.id.button1);
        LinearLayout button = (LinearLayout)dialog.findViewById(R.id.layout12);
        LinearLayout button1 = (LinearLayout)dialog.findViewById(R.id.layout13);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dispatchTakePictureIntent(11);


            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void dispatchTakePictureIntent(int actionCode) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch(actionCode) {
            case 11:
                File f = null;

                try {
                    f = setUpPhotoFile();
                    imgDecodableString = f.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                } catch (IOException e) {
                    e.printStackTrace();
                    f = null;
                    imgDecodableString = null;
                }
                break;

            default:
                break;
        } // switch

        startActivityForResult(takePictureIntent, 11);
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(imgDecodableString);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        Profileactivity.this.sendBroadcast(mediaScanIntent);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //Toast.makeText(getActivity(),""+requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();
            switch(requestCode){
                case 11:
                    if(resultCode!=0){

                        // Profileactivity.intentproceed=11;
                        handleBigCameraPhoto();
                    }
                case 1:
                    if (requestCode == RESULT_LOAD_IMG && resultCode != 0
                            && null != data) {
                        // Get the Image from data
                        // MainActivity.intentproceed=1;
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        // Get the cursor
                        Cursor cursor = Profileactivity.this.getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imgDecodableString = cursor.getString(columnIndex);
                        cursor.close();
                        //img.setImageResource(android.R.color.transparent);
                        // Set the Image in ImageView after decoding the String
                        dp.setImageBitmap(BitmapFactory
                                .decodeFile(imgDecodableString));
                        dp.setScaleType(ImageView.ScaleType.FIT_XY);

                    }
                default:{
                    // Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
                }
            }

            // When an Image is picked
            // Toast.makeText(getActivity(),""+requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(Profileactivity.this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    private String getAlbumName() {
        return getString(R.string.album_name);
    }
    private void handleBigCameraPhoto() {

        if (imgDecodableString != null) {
            galleryAddPic();
            setPic();

            imgDecodableString = null;
        }

    }

    private void setPic() {

		/* There isn't enough memory to open up more than a couple camera photos */
		/* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
        int targetW =200;
        int targetH = 300;


		/* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgDecodableString, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

		/* Figure out which way needs to be reduced less */
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        }

		/* Set bitmap options to scale the image decode target */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
        bitmap1 = BitmapFactory.decodeFile(imgDecodableString, bmOptions);
        Log.e("bitmap", "" + bitmap1);
        dp.setImageBitmap(bitmap1);

       		/* Associate the Bitmap to the ImageView */

    }


    private File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    private File setUpPhotoFile() throws IOException {

        File f = createImageFile();
        imgDecodableString = f.getAbsolutePath();

        return f;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Profileactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Profileactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Profileactivity.this.getWindow();
           // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        final ProgressDialog pd = new ProgressDialog(Profileactivity.this);
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            // progressBar.setProgress(0);
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Making progress bar visible

            pd.setMessage("Loading ...");

            // updating progress bar value
            //  progressBar.setProgress(progress[0]);

            // updating percentage value
            //  txtPercentage.setText(String.valueOf(progress[0]) + "%");
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Api_s.uploadphoto);

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File sourceFile = new File(imgDecodableString);

                // Adding file data to http body
                entity.addPart("user_photo", new FileBody(sourceFile));

                // Extra parameters if you want to pass to server
                entity.addPart("user_id",
                        new StringBody(prefs.getString("user_id",null)));

                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("response", "Response from server: " + result);

            // showing the server response in an alert dialog
            //   showAlert(result);
            pd.hide();
            parsing_for_profile.parsing(Profileactivity.this, prefs.getString("user_id", null), fname.getText().toString().trim(), lname.getText().toString().trim(),
                    mob.getText().toString().trim(),
                    add1.getText().toString().trim() + " " + add11.getText().toString().trim() + " " + add111.getText().toString().trim(),
                    add2.getText().toString().trim() + " " + add22.getText().toString().trim() + " " + add222.getText().toString().trim(),
                    primary, email.getText().toString().trim(), sp22.getSelectedItem().toString().trim());
            super.onPostExecute(result);

        }

    }
}
