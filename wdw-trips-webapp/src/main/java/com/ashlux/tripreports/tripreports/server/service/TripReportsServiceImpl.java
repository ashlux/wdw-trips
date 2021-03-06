package com.ashlux.tripreports.tripreports.server.service;

import com.ashlux.tripreports.domain.Happening;
import com.ashlux.tripreports.tripreports.client.TripReportsService;
import com.ashlux.tripreports.tripreports.client.vo.HappeningBean;
import com.ashlux.tripreports.tripreports.server.business.happening.HappeningBusiness;
import com.ashlux.tripreports.tripreports.server.business.happening.HappeningBusinessImpl;
import com.ashlux.tripreports.tripreports.server.business.happening.HappeningBeanFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;

public class TripReportsServiceImpl
    extends RemoteServiceServlet
    implements TripReportsService
{
    private HappeningBusiness happeningBusiness;
    
    @SuppressWarnings({"unchecked"})
    public List<HappeningBean> getPreviousHappenings()
    {
        List<Happening> happenings = happeningBusiness.getRecentHappenings();
        return HappeningBeanFactory.transform( happenings );
    }

    public HappeningBean addHappening( String name, String message )
    {
        Happening happening = happeningBusiness.saveHappening( name, message );
        return HappeningBeanFactory.transform( happening );
    }

    public void setHappeningBusiness( HappeningBusiness happeningBusiness )
    {
        this.happeningBusiness = happeningBusiness;
    }
}