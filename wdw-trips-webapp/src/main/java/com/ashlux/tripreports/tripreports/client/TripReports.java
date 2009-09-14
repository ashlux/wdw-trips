package com.ashlux.tripreports.tripreports.client;

import com.ashlux.tripreports.tripreports.client.panels.RecentHappeningsPanel;
import com.ashlux.tripreports.tripreports.client.panels.WhatsHappeningPanel;
import com.google.gwt.core.client.EntryPoint;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.FitLayout;

public class TripReports
    implements EntryPoint
{
    private TabPanel tabPanel = new TabPanel();

    private RecentHappeningsPanel recentHappeningsPanel = new RecentHappeningsPanel();

    private WhatsHappeningPanel whatsHappeningPanel = new WhatsHappeningPanel();

    public void onModuleLoad()
    {
        Panel panel = new Panel();
        panel.setBorder( false );
        panel.setPaddings( 15 );
        panel.setLayout( new FitLayout() );

        panel.add( createTabPanel() );

        new Viewport( panel );
    }

    private Panel createTabPanel()
    {
        Panel panel = new Panel();
        panel.setBorder( false );
        panel.setPaddings( 5 );
        panel.setLayout( new FitLayout() );

        tabPanel.setTabPosition( Position.TOP );
        tabPanel.setResizeTabs( false );
        tabPanel.setMinTabWidth( 115 );
        tabPanel.setTabWidth( 135 );
        tabPanel.setActiveTab( 1 ); // What's happening?

        recentHappeningsPanel.setTitle( recentHappeningsPanel.getName() );
        recentHappeningsPanel.add( recentHappeningsPanel.getViewPanel() );
        recentHappeningsPanel.setAutoScroll( true );
        recentHappeningsPanel.setPaddings( 15 );

        whatsHappeningPanel.setTitle( whatsHappeningPanel.getName() );
        whatsHappeningPanel.add( whatsHappeningPanel.getViewPanel() );
        whatsHappeningPanel.setAutoScroll( true );
        whatsHappeningPanel.setPaddings( 15 );

        tabPanel.add( recentHappeningsPanel );
        tabPanel.add( whatsHappeningPanel );
        panel.add( tabPanel );

        return panel;
    }
}
