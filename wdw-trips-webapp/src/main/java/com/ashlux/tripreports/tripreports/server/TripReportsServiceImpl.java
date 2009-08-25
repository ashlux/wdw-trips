package com.ashlux.tripreports.tripreports.server;

import com.ashlux.tripreports.dao.jpa.PMF;
import com.ashlux.tripreports.domain.Happening;
import com.ashlux.tripreports.tripreports.client.HappeningsDTO;
import com.ashlux.tripreports.tripreports.client.TripReportsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public class TripReportsServiceImpl
    extends RemoteServiceServlet
    implements TripReportsService
{
    @SuppressWarnings({"unchecked"})
    public List<HappeningsDTO> getPreviousHappenings()
    {
        PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
        Query recentHappeningsQuery = persistenceManager.newQuery( Happening.class );
        recentHappeningsQuery.setRange( 0, 5 );
        recentHappeningsQuery.setOrdering( "date DESC" );
        List<Happening> happenings = (List<Happening>) recentHappeningsQuery.execute();

        List<HappeningsDTO> happeningsDTOs = new LinkedList<HappeningsDTO>();
        for ( Happening happening : happenings )
        {
            HappeningsDTO happeningsDTO = new HappeningsDTO();
            happeningsDTO.setName( happening.getName() );
            happeningsDTO.setDetails( happening.getDetails() );
            happeningsDTO.setDate( happening.getDate() );
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
        happening.setDate( new Date() );
        persistenceManager.makePersistent( happening );
        persistenceManager.close();

        HappeningsDTO happeningsDTO = new HappeningsDTO();
        happeningsDTO.setName( happening.getName() );
        happeningsDTO.setDetails( happening.getDetails() );
        happeningsDTO.setDate( happening.getDate() );
        return happeningsDTO;
    }
}