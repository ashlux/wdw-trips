package com.ashlux.tripreports.tripreports.client.vo;

import java.io.Serializable;

public class AttractionBean
    implements Serializable
{
    private String name;

    private Park park;

    private boolean open;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Park getPark()
    {
        return park;
    }

    public void setPark( Park park )
    {
        this.park = park;
    }

    public boolean isOpen()
    {
        return open;
    }

    public void setOpen( boolean open )
    {
        this.open = open;
    }
}
