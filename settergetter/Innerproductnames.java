package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/15/2015.
 */
public class Innerproductnames {

    @SerializedName("product_id")
    public String product_id;

    @SerializedName("restraurant_id")
    public String restraurant_id;

    @SerializedName("food_type")
    public String food_type;

    @SerializedName("price")
    public String price;

    @SerializedName("food_name")
    public String food_name;

    @SerializedName("image")
    public String images;

    @SerializedName("rating")
    public String rating;

    @SerializedName("topping_price")
    public String topping_price;


    @SerializedName("topping")
    public List<Inner_toppings> toppingsss = new ArrayList<Inner_toppings>();




}
