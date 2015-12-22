package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/15/2015.
 */
public class Product_setter_getter {
    @SerializedName("result")
    public String result;

    @SerializedName("Message")
    public List<Innerproductnames> innerproductnames = new ArrayList<Innerproductnames>();
}
