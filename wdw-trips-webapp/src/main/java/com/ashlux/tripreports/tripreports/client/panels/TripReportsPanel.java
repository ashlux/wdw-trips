package com.ashlux.tripreports.tripreports.client.panels;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.PanelListenerAdapter;

abstract public class TripReportsPanel
    extends Panel
{
    private Panel viewPanel;

    protected TripReportsPanel()
    {
        addListener( new PanelListenerAdapter()
        {
            public void onActivate( Panel panel )
            {
                TripReportsPanel.this.onActivate();
            }

            @Override
            public void onDeactivate( Panel panel )
            {
                TripReportsPanel.this.onDeactivate();
            }
        } );
    }

    public abstract String getName();

    public abstract Panel getViewPanel();

    protected void onActivate()
    {
        if ( viewPanel == null )
        {
            viewPanel = getViewPanel();
        }
        viewPanel.show();
    }

    private void onDeactivate()
    {
        if ( viewPanel != null )
        {
            viewPanel.hide();
        }
    }
}
