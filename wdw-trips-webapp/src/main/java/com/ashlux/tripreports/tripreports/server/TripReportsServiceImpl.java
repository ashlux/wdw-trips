package com.ashlux.tripreports.tripreports.server;

import com.ashlux.tripreports.dao.jpa.EMF;
import com.ashlux.tripreports.domain.Happening;
import com.ashlux.tripreports.tripreports.client.TripReportsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.persistence.EntityManager;

public class TripReportsServiceImpl
    extends RemoteServiceServlet
    implements TripReportsService
{
    public String getMessage( String msg )
    {
        EntityManager entityManager = EMF.get().createEntityManager();
        Happening happening = new Happening();
        happening.setDetails( "BLAH BLAH" );
        entityManager.persist( happening );
        entityManager.close();

        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}