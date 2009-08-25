package com.ashlux.tripreports.dao.jpa;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.JDOHelper;

public final class PMF
{

    private static final PersistenceManagerFactory INSTANCE = JDOHelper.getPersistenceManagerFactory( "transactional" );

    public static PersistenceManagerFactory get()
    {
        return INSTANCE;
    }

    private PMF()
    {
    }
}
