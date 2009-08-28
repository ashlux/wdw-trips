package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextArea;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.AnchorLayout;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.RowLayout;

import java.util.List;

public class TripReports
    implements EntryPoint
{
    private TextField whoAreYouTextField = new TextField( "Who are you?", "name" );

    private TextArea whatsHappeningTextArea = new TextArea( "What's happening?", "name" );

    private Panel yourTripsPanel;

    private TabPanel tabPanel = new TabPanel();

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

        Panel recentHappeningsPanel = new Panel();
        recentHappeningsPanel.setTitle( "Recent happenings" );
        recentHappeningsPanel.setAutoScroll( true );
        recentHappeningsPanel.add( recentHappeningsPanel() );
        recentHappeningsPanel.setPaddings( 15 );

        Panel whatsHappeningPanel = new Panel();
        whatsHappeningPanel.setTitle( "What's happening?" );
        whatsHappeningPanel.setAutoScroll( true );
        whatsHappeningPanel.add( createWhatsHappeningForm() );
        whatsHappeningPanel.setPaddings( 15 );

        tabPanel.add( recentHappeningsPanel );
        tabPanel.add( whatsHappeningPanel );
        panel.add( tabPanel );

        return panel;
    }

    private Widget recentHappeningsPanel()
    {
        yourTripsPanel = new Panel();
        yourTripsPanel.setLayout( new RowLayout() );
        yourTripsPanel.setBorder( false );
        yourTripsPanel.setLayout( new AnchorLayout() );
        yourTripsPanel.setWidth( "100%" );

        TripReportsService.App.getInstance().getPreviousHappenings( new AsyncCallback<List<HappeningsDTO>>()
        {
            public void onFailure( Throwable throwable )
            {
                MessageBox.alert( "Failed to get list of happenings." );
            }

            public void onSuccess( List<HappeningsDTO> happeningsDTOs )
            {
                for ( HappeningsDTO happeningsDTO : happeningsDTOs )
                {
                    addHappeningToPanel( happeningsDTO, yourTripsPanel );
                }
            }
        } );

        return yourTripsPanel;
    }

    private void addHappeningToPanel( HappeningsDTO happeningsDTO, Panel panel )
    {
        Panel happeningPanel = new Panel();
        happeningPanel.setTitle( "Happening by " + happeningsDTO.getName() + " on " + happeningsDTO.getDate() );
        happeningPanel.setCollapsible( true );
        happeningPanel.setAutoScroll( true );
        happeningPanel.setHtml( happeningsDTO.getDetails() );

        Panel spacingPanel = new Panel();
        spacingPanel.setBorder( false );
        spacingPanel.setHeight( 20 );

        panel.add( happeningPanel );
        panel.add( spacingPanel );
    }

    private Panel createWhatsHappeningForm()
    {
        final FormPanel formPanel = new FormPanel();
        formPanel.setFrame( true );
        formPanel.setBorder( true );
        formPanel.setTitle( "What's happening on your trip?" );
        formPanel.setWidth( "100%" );
        formPanel.setLabelWidth( 175 );

        whoAreYouTextField.setWidth( "100%" );
        formPanel.add( whoAreYouTextField );

        whatsHappeningTextArea.setWidth( "100%" );
        whatsHappeningTextArea.setHeight( 100 );
        formPanel.add( whatsHappeningTextArea );

        Button updateButton = new Button( "Update happening" );
        updateButton.addListener( new ButtonListenerAdapter()
        {
            @Override
            public void onClick( Button button, EventObject eventObject )
            {
                TripReportsService.App.getInstance().addHappening( whoAreYouTextField.getText(),
                                                                   whatsHappeningTextArea.getText(),
                                                                   new SendNewHappeningAsyncCallback() );
            }
        } );
        formPanel.addButton( updateButton );
        return formPanel;
    }

    public class SendNewHappeningAsyncCallback
        implements AsyncCallback<HappeningsDTO>
    {
        public void onFailure( Throwable throwable )
        {
            MessageBox.alert( "Failed to send new happening. Please try again." );
        }

        public void onSuccess( HappeningsDTO happeningsDTO )
        {
            MessageBox.alert( "Successfully sent new happening!" );
            addHappeningToPanel( happeningsDTO, yourTripsPanel );
        }
    }
}
