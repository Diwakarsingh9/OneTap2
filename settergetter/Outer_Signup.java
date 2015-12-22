package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saifi45 on 12/17/2015.
 */
public class Outer_Signup {

    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public String msg;

    @SerializedName("Details")
    public Inner_login Details = new Inner_login();
}
