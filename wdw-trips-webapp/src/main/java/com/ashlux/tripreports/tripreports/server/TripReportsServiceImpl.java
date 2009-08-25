package com.ashlux.tripreports.tripreports.server;

import com.ashlux.tripreports.dao.jpa.PMF;
import com.ashlux.tripreports.domain.Happening;
import com.ashlux.tripreports.tripreports.client.HappeningsDTO;
import com.ashlux.tripreports.tripreports.client.TripReportsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.jdo.PersistenceManager;
import java.util.LinkedList;
import java.util.List;

public class TripReportsServiceImpl
    extends RemoteServiceServlet
    implements TripReportsService
{
    public List<HappeningsDTO> getPreviousHappenings()
    {
        PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
        List<Happening> happenings = (List<Happening>) persistenceManager.newQuery( Happening.class ).execute();

        List<HappeningsDTO> happeningsDTOs = new LinkedList<HappeningsDTO>();
        for ( Happening happening : happenings )
        {
            HappeningsDTO happeningsDTO = new HappeningsDTO();
            happeningsDTO.setName( happening.getName() );
            happeningsDTO.setDetails( happening.getDetails() );
            happeningsDTOs.add( happeningsDTO );
        }

        persistenceManager.close();        
        return happeningsDTOs;
    }

    public HappeningsDTO addHappening( String name, String message )
    {
        PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
        Happening happening = new Happening();
        happening.setName( name );
        happening.setDetails( message );
        persistenceManager.makePersistent( happening );
        persistenceManager.close();

        HappeningsDTO happeningsDTO = new HappeningsDTO();
        happeningsDTO.setName( happening.getName() );
        happeningsDTO.setDetails( happening.getDetails() );
        return happeningsDTO;
    }
}