package com.amrdeveloper.reactbutton;

import android.graphics.Color;

/**
 * Created by AmrDeveloper on 2/2/2018.
 */

public class ReactConstance {

    //Color Constance
    public final static String BLUE  = "#0366d6";
    public final static String RED_LOVE  = "#f0716b";
    public final static String RED_ANGRY  = "#f15268";
    public final static String YELLOW_HAHA = "#fde99c";
    public final static String YELLOW_WOW = "#f0ba15";
    public final static String DEFAULT = "#616770";


    //Convert UniCode To String
    public static String uniCodeToString(int uniCode){
        return String.valueOf(Character.toChars(uniCode));
    }

    //Return Color as Integer
    public static int getColor(String color){
        return Color.parseColor(color);
    }

}
