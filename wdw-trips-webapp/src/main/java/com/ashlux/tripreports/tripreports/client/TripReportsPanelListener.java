package com.ashlux.tripreports.tripreports.client;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.PanelListenerAdapter;

public class TripReportsPanelListener
    extends PanelListenerAdapter
{
    @Override
    public void onActivate( Panel panel )
    {
        if ( !( panel instanceof TripReportsPanel ) )
        {
            throw new IllegalArgumentException( "Panel must be a TripReportsPanel." );
        }

        TripReportsPanel tripReportsPanel = (TripReportsPanel) panel;
        Panel viewPanel = tripReportsPanel.getViewPanel();
        viewPanel.show();
    }
}
