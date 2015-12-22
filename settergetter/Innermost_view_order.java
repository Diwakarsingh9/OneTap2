package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/21/2015.
 */
public class Innermost_view_order {

    @SerializedName("product_id")
    public String product_id;

    @SerializedName("quantity")
    public String quantity;

    @SerializedName("food_category")
    public String food_category;

    @SerializedName("name")
    public String name;

    @SerializedName("deliver_time")
    public String deliver_time;

    @SerializedName("price")
    public String price;



    @SerializedName("add_on")
    public List<Innermost_view_order2> innermost_view_orders2 = new ArrayList<Innermost_view_order2>();
}
