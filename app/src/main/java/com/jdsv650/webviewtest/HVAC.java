package com.jdsv650.webviewtest;

import java.io.Serializable;

public class HVAC implements Serializable {

    Integer id = -99;
    Integer mode = -99;
    String dateTime = "";
    String macAddress = "";
    String location = "";
    String status = "";
    Double dBm = -99.0;

    String date1 = "";
    String date2 = "";
    String date3 = "";
    String date4 = "";
    String date5 = "";
    String date6 = "";
    String date7 = "";
    Double runT1 = -99.0;
    Double runT2 = -99.0;
    Double runT3 = -99.0;
    Double runT4 = -99.0;
    Double runT5 = -99.0;
    Double runT6 = -99.0;
    Double runT7 = -99.0;
    Double delT1 = -99.0;
    Double delT2 = -99.0;
    Double delT3 = -99.0;
    Double delT4 = -99.0;
    Double delT5 = -99.0;
    Double delT6 = -99.0;
    Double delT7 = -99.0;
    Double on1 = -99.0;
    Double on2 = -99.0;
    Double on3 = -99.0;
    Double on4 = -99.0;
    Double on5 = -99.0;
    Double on6 = -99.0;
    Double on7 = -99.0;
    Double cycle1 = -99.0;
    Double cycle2 = -99.0;
    Double cycle3 = -99.0;
    Double cycle4 = -99.0;
    Double cycle5 = -99.0;
    Double cycle6 = -99.0;
    Double cycle7 = -99.0;
    Integer rule0 = -99;  //overall operating summary
    Integer rule1 = -99;
    Integer rule2 = -99;
    Integer rule3 = -99;
    Integer rule4 = -99;
    Integer rule5 = -99;
    Integer rule6 = -99;
    Integer rule7 = -99;
    Integer rule8 = -99;

    // for displaying check on login scr
    private boolean isChecked;

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}