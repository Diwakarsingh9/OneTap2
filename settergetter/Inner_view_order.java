package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/21/2015.
 */
public class Inner_view_order {
    @SerializedName("user_id")
    public String user_id;

    @SerializedName("restraurant_id")
    public String restraurant_id;

    @SerializedName("order_date")
    public String order_date;

    @SerializedName("order_time")
    public String order_time;

    @SerializedName("total_items")
    public String total_items;

    @SerializedName("total_quantity")
    public String total_quantity;

    @SerializedName("total_price")
    public String total_price;

    @SerializedName("product_info")
    public List<Innermost_view_order> innermost_view_orders = new ArrayList<Innermost_view_order>();
}
