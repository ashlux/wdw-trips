package com.ashlux.tripreports.dao.jdo;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.JDOHelper;

public final class PMF
{

    public static final PersistenceManagerFactory INSTANCE = JDOHelper.getPersistenceManagerFactory( "transactional" );

    public static PersistenceManagerFactory get()
    {
        return INSTANCE;
    }

    private PMF()
    {
    }
}
