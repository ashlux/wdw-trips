package com.ashlux.tripreports.tripreports.client.panels;

import com.ashlux.tripreports.tripreports.client.TripReportsService;
import com.ashlux.tripreports.tripreports.client.vo.HappeningBean;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.AnchorLayout;
import com.gwtext.client.widgets.layout.RowLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecentHappeningsPanel
    extends TripReportsPanel
{
    private final String NAME = "Recent happenings";

    final Panel yourTripsPanel = new Panel();

    public String getName()
    {
        return NAME;
    }

    public Panel getViewPanel()
    {
        yourTripsPanel.setLayout( new RowLayout() );
        yourTripsPanel.setBorder( false );
        yourTripsPanel.setLayout( new AnchorLayout() );
        yourTripsPanel.setWidth( "100%" );

        TripReportsService.App.getInstance().getPreviousHappenings( new AsyncCallback<List<HappeningBean>>()
        {
            public void onFailure( Throwable throwable )
            {
                MessageBox.alert( "Failed to get list of happenings." );
            }

            public void onSuccess( List<HappeningBean> happeningBeans )
            {
                displayHappenings( happeningBeans, yourTripsPanel );
            }
        } );

        return yourTripsPanel;
    }

    private void displayHappenings( List<HappeningBean> happeningBeans, Panel panel )
    {
        Collections.sort( happeningBeans, new Comparator<HappeningBean>()
        {
            public int compare( HappeningBean happeningBean, HappeningBean happeningBean1 )
            {
                return happeningBean.getDate().compareTo( happeningBean1.getDate() );
            }
        } );

        for ( int c = happeningBeans.size(); c >= 0; ++c )
        {
            addHappeningToPanel( happeningBeans.get( c ), panel );
        }
    }

    private void addHappeningToPanel( HappeningBean happeningBean, Panel panel )
    {
        Panel happeningPanel = new Panel();
        happeningPanel.setTitle( "Happening by " + happeningBean.getName() + " on " + happeningBean.getDate() );
        happeningPanel.setCollapsible( true );
        happeningPanel.setAutoScroll( true );
        happeningPanel.setHtml( happeningBean.getDetails() );

        Panel spacingPanel = new Panel();
        spacingPanel.setBorder( false );
        spacingPanel.setHeight( 20 );

        panel.insert( 0, happeningPanel );
        panel.insert( 0, spacingPanel );
    }
}
