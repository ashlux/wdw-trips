package com.ashlux.tripreports.tripreports.client.panels;

import com.ashlux.tripreports.tripreports.client.TripReportsService;
import com.ashlux.tripreports.tripreports.client.vo.HappeningBean;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.AnchorLayout;
import com.gwtext.client.widgets.layout.RowLayout;

import java.util.List;

public class RecentHappeningsPanel
    extends TripReportsPanel
{
    private final String NAME = "Recent happenings";

    public String getName()
    {
        return NAME;
    }

    public Panel getViewPanel()
    {
        final Panel yourTripsPanel = new Panel();
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
                for ( HappeningBean happeningBean : happeningBeans )
                {
                    addHappeningToPanel( happeningBean, yourTripsPanel );
                }
            }
        } );

        return yourTripsPanel;
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

        panel.add( happeningPanel );
        panel.add( spacingPanel );
    }
}
