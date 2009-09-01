package com.ashlux.tripreports.tripreports.server.service;

import com.ashlux.tripreports.domain.Happening;
import com.ashlux.tripreports.tripreports.client.vo.HappeningBean;
import com.ashlux.tripreports.tripreports.server.factory.HappeningBeanFactory;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class HappeningBeanFactoryTest
{
    @Test
    public void testTransform_single()
    {
        Happening happening = new Happening();
        happening.setDate( new Date() );
        happening.setName( "Ash Lux" );
        happening.setDetails( "These are details." );

        HappeningBean happeningDTO = HappeningBeanFactory.transform( happening );

        assertEquals( happeningDTO.getDate(), happening.getDate() );
        assertEquals( happeningDTO.getDetails(), happening.getDetails() );
        assertEquals( happeningDTO.getName(), happening.getName() );
    }

    @Test
    public void testTransform_list()
    {
        Happening happening = new Happening();
        happening.setDate( new Date( 0 ) );
        happening.setName( "Ash Lux" );
        happening.setDetails( "These are details." );
        Happening happening2 = new Happening();
        happening2.setDate( new Date( 1000 ) );
        happening2.setName( "Ash Lux2" );
        happening2.setDetails( "These are details2." );
        List<Happening> happenings = new LinkedList<Happening>();

        List<HappeningBean> happeningBeans = HappeningBeanFactory.transform( happenings );

        assertEquals( happeningBeans.size(), 2 );
        assertEquals( happeningBeans.get( 0 ).getName(), happening.getName() );
        assertEquals( happeningBeans.get( 1 ).getName(), happening2.getName() );
    }
}
