package com.ashlux.tripreports.dao.jdo;

import com.ashlux.tripreports.dao.HappeningDao;
import com.ashlux.tripreports.domain.Happening;
import org.springframework.orm.jdo.support.JdoDaoSupport;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.Collection;
import java.util.List;

public class HappeningJdoDao
    extends JdoDaoSupport
    implements HappeningDao
{
    @SuppressWarnings({"unchecked"})
    public List<Happening> getRecentHappenings( int offset, int limit )
    {
        PersistenceManager persistenceManager = getPersistenceManager();
        Query recentHappeningsQuery = persistenceManager.newQuery( Happening.class );
        recentHappeningsQuery.setRange( offset, offset + limit );
        recentHappeningsQuery.setOrdering( "date DESC" );
        List<Happening> happenings =
            (List<Happening>) persistenceManager.detachCopyAll( (Collection) recentHappeningsQuery.execute() );
        releasePersistenceManager( persistenceManager );
        return happenings;
    }

    public void saveHappening( Happening happening )
    {
        PersistenceManager persistenceManager = getPersistenceManager();
        persistenceManager.makePersistent( happening );
        releasePersistenceManager( persistenceManager );
    }
}
