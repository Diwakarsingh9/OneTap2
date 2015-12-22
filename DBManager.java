package com.apporio.onetap;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.apporio.onetap.CartTable;
import com.apporio.onetap.database.CartEvent;
import com.apporio.onetap.database.CheckCart;
import com.orm.SugarRecord;

import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * Created by samir on 10/07/15.
 */
public class DBManager {

    public static CartTable ct ;
    public static Context con ;

    public static EventBus bus = EventBus.getDefault() ;

    public DBManager(Context con ){
        this.con = con ;
    }

    public static  void addtocart(String ProductId, String rest_id,
                                  String food_name, String foodtype, String foodprice, String foodimage, String foodrating, String food_no_of_units){

        if(new CheckCart().idalreadyExsistinCart(ProductId)){
            ////change already saved details in database
            changeExsistingRowintable(ProductId  , food_no_of_units);
        }else {
            /////  create new row in database
            createnreRowintable( ProductId, rest_id, food_name, foodtype, foodprice,foodimage,foodrating,food_no_of_units);
        }

    }

    private static void createnreRowintable(String ProductId, String rest_id,
                                            String food_name, String foodtype, String foodprice, String foodimage, String foodrating, String food_no_of_units) {
        Log.e("hfdhg","nounits   ---"+food_no_of_units);
        new CartTable(ProductId,
                rest_id,
                food_name,
                foodtype,
                foodprice,foodimage,foodrating,food_no_of_units
        ).save();

        updateTotalOnActionBar();
        showdataoncart();

    }

    private static void updateTotalOnActionBar() {
       /* if(ActivityDetector.openActivity.equals("MainActivity")){
            MainActivity.showgrossOnCart(calculationForGrossPrice());
        }if(ActivityDetector.openActivity.equals("CartActivity")){
            CartActivity.showgrossOnCartCartActivity(calculationForGrossPrice());
        }   */


    }

    public static double calculationForGrossPrice() {

        Double dtemp1  , multiplier , gross = 0.0;
        int temp1;
        List<CartTable> templist = CartTable.listAll(CartTable.class);

        for(int i = 0 ; i<templist.size() ; i++){
            dtemp1 = Double.parseDouble(templist.get(i).getFoodprice());
            temp1 = Integer.parseInt(templist.get(i).getFoodNoOfUnits());
            multiplier = temp1 * dtemp1 ;
            gross = gross + multiplier ;
        }
        return gross ;
    }


    public static void changeExsistingRowintable(String reportid , String noOfunitreport) {
        Log.e("hfdhg","nounits   ---"+noOfunitreport);
        List<CartTable> temp =  CartTable.find(CartTable.class, "Product_Id = ?", reportid);
        long id_of_row_intable = temp.get(0).getId();
        ct = CartTable.findById(CartTable.class, id_of_row_intable);
        ct.FoodNoOfUnits = noOfunitreport ;
        ct.save();
        updateTotalOnActionBar();
        showdataoncart();
    }


    public static void removeItemfromDB(String reportid){

        List<CartTable>  data_in_list;
        data_in_list = CartTable.find(CartTable.class, "Product_Id = ?", reportid);
        long id_of_row = data_in_list.get(0).getId();
        ct = CartTable.findById(CartTable.class, id_of_row);
        ct.delete();
        updateTotalOnActionBar();
        showdataoncart();
    }


    public static  int countNoofRowsInDatabse(){
        List<CartTable> templist = CartTable.listAll(CartTable.class);
        //Toaster.generatemessage(con , "No Of rows in Database"+templist.size());
        Log.e("No Rows in CART TABLE ", "" + templist.size());
        return templist.size();

    }



    public static  List<CartTable> getAllrows(){
        List<CartTable> templist = CartTable.listAll(CartTable.class);
        //Toaster.generatemessage(con , "No Of rows in Database"+templist.size());
        Log.e("No Rows in CART TABLE ", "" + templist.size());
        return templist;

    }

    public static  int totalNoofitemsincar(){
        List<CartTable> templist = CartTable.listAll(CartTable.class);
        //Toaster.generatemessage(con , "No Of rows in Database"+templist.size());
        Log.e("No Rows in CART TABLE ", "" + templist.size());
        int totalitems = 0;
        totalitems = templist.size();
        return totalitems;

    }

    public static void clearCartTable(){

        SugarRecord.deleteAll(CartTable.class);
    }

    public static void showdataoncart (){
        CartEvent event = null ;
        event = new CartEvent(""+totalNoofitemsincar() , ""+calculationForGrossPrice());
        bus.post(event);

    }


    public  static String getNoofunitAccordingToProductId(String id ){


        if(new CheckCart().idalreadyExsistinCart(id)){
           // Log.e("fgdfdfdfdf",""+id);
            List<CartTable>  data_in_list;
            data_in_list = CartTable.find(CartTable.class, "Product_Id = ?", id);
            long id_of_row = data_in_list.get(0).getId();
            ct = CartTable.findById(CartTable.class, id_of_row);
            return  ct.getFoodNoOfUnits();
        }else {
            return "0";
        }


    }




}
