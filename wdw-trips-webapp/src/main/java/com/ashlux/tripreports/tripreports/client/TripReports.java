package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextArea;
import com.gwtext.client.widgets.form.TextField;
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
        panel.setHeight( "100%" );

        panel.add( createTabPanel() );

        RootPanel.get().add( panel );
    }

    private Panel createTabPanel()
    {
        Panel panel = new Panel();
        panel.setBorder( false );
        panel.setPaddings( 15 );
        panel.setLayout( new FitLayout() );
        panel.setHeight( "100%" );

        tabPanel.setTabPosition( Position.TOP );
        tabPanel.setResizeTabs( false );
        tabPanel.setMinTabWidth( 115 );
        tabPanel.setTabWidth( 135 );
        tabPanel.setActiveTab( 0 );
        tabPanel.setHeight( "100%" );

        Panel whatsHappeningPanel = new Panel();
        whatsHappeningPanel.setTitle( "What's happening?" );
        whatsHappeningPanel.setAutoScroll( true );
        whatsHappeningPanel.add( createWhatsHappeningForm() );
        whatsHappeningPanel.setHeight( "100%" );

        Panel yourTripsPanel = new Panel();
        yourTripsPanel.setTitle( "Your trips" );
        yourTripsPanel.setAutoScroll( true );
        yourTripsPanel.add( createYourTrips() );
        yourTripsPanel.setHeight( "100%" );

        tabPanel.add( whatsHappeningPanel );
        tabPanel.add( yourTripsPanel );
        panel.add( tabPanel );

        return panel;
    }

    private Widget createYourTrips()
    {
        yourTripsPanel = new Panel();
        yourTripsPanel.setLayout( new RowLayout() );
        yourTripsPanel.setBorder( true );
        yourTripsPanel.setTitle( "Things that have happened on your trip" );
        yourTripsPanel.setWidth( "100%" );


        TripReportsService.App.getInstance().getPreviousHappenings( new AsyncCallback<List<HappeningsDTO>>()
        {
            public void onFailure( Throwable throwable )
            {
                MessageBox.alert( "Failed to get list of happenings." );
            }

            public void onSuccess( List<HappeningsDTO> happeningsDTOs )
            {
                MessageBox.alert( "Successfully got list of happenings." );
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
        happeningPanel.setClosable( true );
        happeningPanel.setAutoScroll( true );
        happeningPanel.setHtml( happeningsDTO.getDetails() );
        panel.add( happeningPanel );
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
                MessageBox.alert( "Clicked update happening." );
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
