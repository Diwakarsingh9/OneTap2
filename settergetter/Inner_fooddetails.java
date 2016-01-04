package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/19/2015.
 */
public class Inner_fooddetails {

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

    @SerializedName("deliver_time")
    public String deliver_time;

    @SerializedName("image")
    public String image;

    @SerializedName("description")
    public String description;

    @SerializedName("rating")
    public String rating;


    @SerializedName("favorite")
    public String favorite;

    @SerializedName("restraurant_image")
    public String restraurant_image;

    @SerializedName("no_of_ret_pers")
    public String no_of_ret_pers;

    @SerializedName("rating_number")
    public Inner_rating inner_rating = new Inner_rating();

    @SerializedName("add_on")
    public List<Inner_add_ons> add_on = new ArrayList<Inner_add_ons>();
}
