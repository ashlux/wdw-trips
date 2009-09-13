package com.ashlux.tripreports.tripreports.server.business.happening;

import com.ashlux.tripreports.dao.HappeningDao;
import com.ashlux.tripreports.dao.jdo.HappeningJdoDao;
import com.ashlux.tripreports.domain.Happening;

import java.util.Date;
import java.util.List;

public class HappeningBusinessImpl
    implements HappeningBusiness
{
    private HappeningDao happeningDao;

    public Happening saveHappening( String name, String details )
    {
        Happening happening = new Happening();
        happening.setDate( new Date() );
        happening.setName( name );
        happening.setDetails( details );

        happeningDao.saveHappening( happening );
        return happening;
    }

    public List<Happening> getRecentHappenings( )
    {
        return happeningDao.getRecentHappenings( 0, 5 );
    }

    public void setHappeningDao( HappeningDao happeningDao )
    {
        this.happeningDao = happeningDao;
    }
}
