package com.ashlux.tripreports.tripreports.client;

import com.ashlux.tripreports.tripreports.client.panels.TripReportsPanel;
import com.gwtext.client.widgets.Panel;

public class TripReportsPanelListenerTest
    extends TripReportsClientTestCase
{
    public void testOnActivate_unexpectedPanel()
    {
        try
        {
            Panel panel = new Panel();
            new TripReportsPanelListener().onActivate( panel );
            fail( "Should have had an exception thrown, Panel is of the wrong type." );
        }
        catch ( IllegalArgumentException e )
        {
            assertTrue( true );
        }
    }

    public void testOnActivate_behavior()
    {
        final PanelStub panel = new PanelStub();
        assertFalse( panel.showCalled );
        TripReportsPanelStub tripReportsPanel = new TripReportsPanelStub();
        tripReportsPanel.viewPanel = panel;
        TripReportsPanelListener tripReportsPanelListener = new TripReportsPanelListener();
        tripReportsPanelListener.onActivate( tripReportsPanel );

        assertTrue( panel.showCalled );
    }

    public class PanelStub
        extends Panel
    {
        boolean showCalled = false;

        @Override
        public void show()
        {
            super.show();
            showCalled = true;
        }
    }

    public class TripReportsPanelStub
        extends TripReportsPanel
    {
        Panel viewPanel;

        public String getName()
        {
            return "PANEL NAME";
        }

        public Panel getViewPanel()
        {
            return viewPanel;
        }
    }
}
