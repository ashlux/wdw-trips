package com.ashlux.tripreports.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Attraction
{
    /**
     * @noinspection UnusedDeclaration
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String name;

    @Persistent
    private String park;

    @Persistent
    private boolean open;

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getPark()
    {
        return park;
    }

    public void setPark( String park )
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
