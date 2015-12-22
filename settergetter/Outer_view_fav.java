package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/19/2015.
 */
public class Outer_view_fav {

    @SerializedName("result")
    public String result;

    @SerializedName("Message")
    public List<Inner_view_fav> inner_view_fav = new ArrayList<Inner_view_fav>();
}
