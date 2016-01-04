package com.apporio.onetap;


import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by samir on 10/07/15.
 */
public class CartTable  extends SugarRecord {


    public String ProductId ;
    public String RestId ;
    public String Foodname ;
    public String Foodtype ;
    public  String Foodprice ;
    public String Foodimage ;
    public String Foodrating ;
    public String FoodNoOfUnits;
    public String[] Toppingsoffood ;
    public String[] Toppingsidoffood;





    public CartTable(){
    }


    public CartTable(String productId, String rest_id, String food_name, String foodtype, String foodprice,
                     String foodimage, String foodrating, String food_noOfUnits, String[] toppingsoffood, String[] toppingsidoffood){
        this.ProductId = productId;
        this.RestId = rest_id;
        this.Foodname = food_name;
        this.Foodtype = foodtype;
        this.Foodprice = foodprice ;
        this.Foodimage = foodimage;
        this.Foodrating = foodrating;
        this.FoodNoOfUnits = food_noOfUnits;
        this.Toppingsoffood = toppingsoffood;
        this.Toppingsidoffood = toppingsidoffood;

    }









    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getRestId() {
        return RestId;
    }

    public void setRestId(String rest_id) {
        RestId = rest_id;
    }

    public String getFoodname() {
        return Foodname;
    }

    public void setFoodname(String foodname) {
        Foodname = foodname;
    }

    public String getFoodtype() {
        return Foodtype;
    }

    public void setFoodtype(String foodtype) {
        Foodtype = foodtype;
    }

    public String getFoodprice() {
        return Foodprice;
    }

    public void setFoodprice(String foodprice) {
        Foodprice = foodprice;
    }
    public String getFoodimage() {
        return Foodimage;
    }

    public void setFoodimage(String foodimage) {
        Foodimage = foodimage;
    }

    public String getFoodrating() {
        return Foodrating;
    }

    public void setFoodrating(String foodrating) {
        Foodrating = foodrating;
    }

    public String getFoodNoOfUnits() {
        return FoodNoOfUnits;
    }

    public void setFoodNoOfUnits(String foodNoOfUnits) {
        FoodNoOfUnits = foodNoOfUnits;
    }

    public String[] getToppingsoffood() {
        return Toppingsoffood;
    }
    public void setToppingsoffood(String[] toppingsoffood) {
        Toppingsoffood = toppingsoffood;
    }

    public void setToppingsidoffood(String[]  toppingsidoffood) {
        Toppingsidoffood = toppingsidoffood;
    }
    public String[] getToppingsidoffood() {
        return Toppingsidoffood;
    }


}
