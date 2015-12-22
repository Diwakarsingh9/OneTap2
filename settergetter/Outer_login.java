package com.apporio.onetap.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/16/2015.
 */
public class Outer_login {

    @SerializedName("result")
    public String result;

    @SerializedName("msg")
    public String msg;

    @SerializedName("details")
    public Inner_login details = new Inner_login();
}
