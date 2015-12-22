package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/22/2015.
 */
public class Outer_all_cities {

    @SerializedName("result")
    public String result;

    @SerializedName("Message")
    public List<Inner_all_cities> inner_all_cities = new ArrayList<Inner_all_cities>();
}
