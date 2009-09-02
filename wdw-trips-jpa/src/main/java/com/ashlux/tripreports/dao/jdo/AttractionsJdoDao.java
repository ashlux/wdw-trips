package com.ashlux.tripreports.dao.jdo;

import com.ashlux.tripreports.dao.AttractionsDao;
import com.ashlux.tripreports.domain.Attraction;
import com.ashlux.tripreports.domain.Happening;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.Collection;
import java.util.List;

public class AttractionsJdoDao
    implements AttractionsDao
{
    @SuppressWarnings({"unchecked"})
    public List<Attraction> getAttractions( String park, boolean includeClosed )
    {
        PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
        Query attractionsQuery = persistenceManager.newQuery( Happening.class );
        attractionsQuery.setOrdering( "name ASC" );
        if ( !includeClosed )
        {
            attractionsQuery.setFilter( "open = true" );
        }
        List<Attraction> attractions =
            (List<Attraction>) persistenceManager.detachCopyAll( (Collection) attractionsQuery.execute() );
        persistenceManager.close();
        return attractions;
    }

    public void addAttraction( Attraction attraction )
    {
        PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
        persistenceManager.makePersistent( attraction );
        persistenceManager.close();
    }
}
