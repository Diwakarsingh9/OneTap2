package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/21/2015.
 */
public class Outer_view_order {

    @SerializedName("result")
    public String result;

    @SerializedName("Message")
    public List<Inner_view_order> inner_view_orders = new ArrayList<Inner_view_order>();


}
