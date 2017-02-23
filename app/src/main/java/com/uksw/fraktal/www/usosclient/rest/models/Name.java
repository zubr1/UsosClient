package com.uksw.fraktal.www.usosclient.rest.models;

/**
 * Created by zubr on 19.02.2017.
 */

public class Name
{
    private String pl;

    private String en;

    public String getPl ()
    {
        return pl;
    }

    public void setPl (String pl)
    {
        this.pl = pl;
    }

    public String getEn ()
    {
        return en;
    }

    public void setEn (String en)
    {
        this.en = en;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pl = "+pl+", en = "+en+"]";
    }
}
