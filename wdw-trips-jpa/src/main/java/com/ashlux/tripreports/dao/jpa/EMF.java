package com.ashlux.tripreports.dao.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF
{
    private static final EntityManagerFactory INSTANCE =
        Persistence.createEntityManagerFactory( "helloorm" );

    private EMF()
    {
    }

    public static EntityManagerFactory get()
    {
        return INSTANCE;
    }
}