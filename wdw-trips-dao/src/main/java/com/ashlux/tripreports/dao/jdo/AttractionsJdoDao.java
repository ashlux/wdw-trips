package com.ashlux.tripreports.dao.jdo;

import com.ashlux.tripreports.dao.AttractionsDao;
import com.ashlux.tripreports.domain.Attraction;
import com.ashlux.tripreports.domain.Happening;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.jdo.support.JdoDaoSupport;

public class AttractionsJdoDao
    extends JdoDaoSupport
    implements AttractionsDao
{
    @SuppressWarnings({"unchecked"})
    public List<Attraction> getAttractions( String park, boolean includeClosed )
    {
        PersistenceManager persistenceManager = getPersistenceManager();
        Query attractionsQuery = persistenceManager.newQuery( Happening.class );
        attractionsQuery.setOrdering( "name ASC" );
        if ( !includeClosed )
        {
            attractionsQuery.setFilter( "open = true" );
        }
        List<Attraction> attractions =
            (List<Attraction>) persistenceManager.detachCopyAll( (Collection) attractionsQuery.execute() );
        releasePersistenceManager( persistenceManager );
        return attractions;
    }

    public void addAttraction( Attraction attraction )
    {
        PersistenceManager persistenceManager = getPersistenceManager();
        persistenceManager.makePersistent( attraction );
        releasePersistenceManager( persistenceManager );
    }
}
