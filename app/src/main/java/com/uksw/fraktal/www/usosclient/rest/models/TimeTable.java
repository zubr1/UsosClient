package com.uksw.fraktal.www.usosclient.rest.models;

/**
 * Created by zubr on 19.02.2017.
 */

public class TimeTable
{
    private String end_time;

    private Name name;

    private String start_time;

    public String getEnd_time ()
    {
        return end_time;
    }

    public void setEnd_time (String end_time)
    {
        this.end_time = end_time;
    }

    public Name getName ()
    {
        return name;
    }

    public void setName (Name name)
    {
        this.name = name;
    }

    public String getStart_time ()
    {
        return start_time;
    }

    public void setStart_time (String start_time)
    {
        this.start_time = start_time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [end_time = "+end_time+", name = "+name+", start_time = "+start_time+"]";
    }
}
