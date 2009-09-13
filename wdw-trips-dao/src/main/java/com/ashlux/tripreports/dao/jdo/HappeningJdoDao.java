package com.ashlux.tripreports.dao.jdo;

import com.ashlux.tripreports.dao.HappeningDao;
import com.ashlux.tripreports.domain.Happening;
import com.ashlux.tripreports.domain.Attraction;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;
import java.util.Collection;

public class HappeningJdoDao    
    implements HappeningDao
{
    @SuppressWarnings({"unchecked"})
    public List<Happening> getRecentHappenings( int offset, int limit )
    {
        PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
        Query recentHappeningsQuery = persistenceManager.newQuery( Happening.class );
        recentHappeningsQuery.setRange( offset, offset + limit );
        recentHappeningsQuery.setOrdering( "date DESC" );
        List<Happening> happenings =
            (List<Happening>) persistenceManager.detachCopyAll( (Collection) recentHappeningsQuery.execute() );
        persistenceManager.close();
        return happenings;
    }

    public void saveHappening( Happening happening )
    {
        PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
        persistenceManager.makePersistent( happening );
        persistenceManager.close();
    }
}
