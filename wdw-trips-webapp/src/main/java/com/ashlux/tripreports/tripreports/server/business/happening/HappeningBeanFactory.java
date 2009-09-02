package com.ashlux.tripreports.tripreports.server.business.happening;

import com.ashlux.tripreports.domain.Happening;
import com.ashlux.tripreports.tripreports.client.vo.HappeningBean;

import java.util.LinkedList;
import java.util.List;

public class HappeningBeanFactory
{
    private HappeningBeanFactory()
    {
    }

    public static HappeningBean transform( Happening happening )
    {
        HappeningBean happeningBean = new HappeningBean();
        happeningBean.setName( happening.getName() );
        happeningBean.setDetails( happening.getDetails() );
        happeningBean.setDate( happening.getDate() );
        return happeningBean;
    }

    public static List<HappeningBean> transform( List<Happening> happenings )
    {
        List<HappeningBean> happeningBeans = new LinkedList<HappeningBean>();
        for ( Happening happening : happenings )
        {
            happeningBeans.add( transform( happening ) );
        }
        return happeningBeans;
    }
}
