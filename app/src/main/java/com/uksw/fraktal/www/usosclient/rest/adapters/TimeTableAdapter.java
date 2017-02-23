package com.uksw.fraktal.www.usosclient.rest.adapters;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by zubr on 19.02.2017.
 */

public class TimeTableAdapter {








    public static String GetToken()
    {
        return "";
    }

    public static String GenerateTimeStamp()
    {
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    // Generates random number between 123400 and 9999999
    public static String GenerateNonce()
    {
        Random r = new Random();
        return String.valueOf(r.nextInt(123400) + 9999999);
    }
}
