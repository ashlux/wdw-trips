package com.ashlux.tripreports.tripreports.client;

import java.io.Serializable;
import java.util.Date;

public class HappeningsDTO implements Serializable
{
    private String name;

    private String details;

    private Date date;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails( String details )
    {
        this.details = details;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }
}
