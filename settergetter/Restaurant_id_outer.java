package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/14/2015.
 */
public class Restaurant_id_outer {

    @SerializedName("result")
    public String result;

    @SerializedName("Message")
    public List<Inner_Restaurant_id> inner_restaurant_id = new ArrayList<Inner_Restaurant_id>();
}
