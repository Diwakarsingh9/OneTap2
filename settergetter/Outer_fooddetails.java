package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/19/2015.
 */
public class Outer_fooddetails {

    @SerializedName("result")
    public String result;

    @SerializedName("Message")
    public Inner_fooddetails inner_fooddetails = new Inner_fooddetails();
}
