package com.ashlux.tripreports.tripreports.client;

import java.io.Serializable;

public class HappeningsDTO implements Serializable
{
    private String name;

    private String details;

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
}
