package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.FitLayout;

import java.util.List;

public class TripReports
    implements EntryPoint
{
    private VerticalPanel previousHappeningsVerticalPanel = new VerticalPanel();

    private TextBox whoAreYouTextBox = new TextBox();

    private TextArea whatsHappeningTextArea = new TextArea();

    public void onModuleLoad()
    {
        Panel panel = new Panel();
        panel.setBorder( false );
        panel.setPaddings( 15 );
        panel.setLayout( new FitLayout() );

        panel.add( createHeaderWidget() );
//        panel.add( createWorkspacePanel() );
//        panel.add( previousHappeningsVerticalPanel );
//        panel.add( new com.gwtext.client.widgets.Button( "BUTTON" ) );
//        panel.add( new Button( "BUTTON" ) );

        new Viewport( panel );

        // get most recent happenings, callback loads them
        TripReportsService.App.getInstance().getPreviousHappenings(
            new PreviousHappeningsAsyncCallback( previousHappeningsVerticalPanel ) );
    }

    private Widget createHeaderWidget()
    {
        Panel panel = new Panel();
//        panel.add( new Label("WDW Trip Reports") );
        return panel;
    }


    private Widget createWorkspacePanel()
    {
        TabPanel workspaceTapPanel = new TabPanel();
        workspaceTapPanel.setAnimationEnabled( true );
        workspaceTapPanel.setWidth( "100%" );
        workspaceTapPanel.add( createWhatsHappeningWidget(), "What's happening?" );
        workspaceTapPanel.selectTab( 0 );
        workspaceTapPanel.add( new Label( "Content2" ), "Your trips" );
        workspaceTapPanel.add( new Label( "Content3" ), "Search" );
        workspaceTapPanel.add( new Label( "Content4" ), "Settings" );
        return workspaceTapPanel;
    }

    private Widget createWhatsHappeningWidget()
    {
        Grid grid = new Grid( 3, 2 );
        grid.getColumnFormatter().setWidth( 0, "130" );

        Label whoAreYouLabel = new Label( "Who are you?" );
        grid.setWidget( 0, 0, whoAreYouLabel );
        grid.setWidget( 0, 1, whoAreYouTextBox );
        whoAreYouTextBox.setWidth( "100%" );

        Label whatsHappeningLabel = new Label( "What's happening?" );
        grid.setWidget( 1, 0, whatsHappeningLabel );
        grid.setWidget( 1, 1, whatsHappeningTextArea );
        whatsHappeningTextArea.setWidth( "100%" );
        whatsHappeningTextArea.setHeight( "70" );

        final Button updateButton = new Button( "Update" );
        updateButton.addClickHandler( new UpdateButtonClickHandler() );

        grid.setWidget( 2, 1, updateButton );
        grid.getCellFormatter().setHorizontalAlignment( 2, 1, HasHorizontalAlignment.ALIGN_RIGHT );

        return grid;
    }

    public class UpdateButtonClickHandler
        implements ClickHandler
    {
        public void onClick( ClickEvent clickEvent )
        {
            TripReportsService.App.getInstance().addHappening( whoAreYouTextBox.getText(),
                                                               whatsHappeningTextArea.getText(),
                                                               new AddHappeningAsyncCallback(
                                                                   previousHappeningsVerticalPanel ) );
        }
    }

    public class AddHappeningAsyncCallback
        implements AsyncCallback<HappeningsDTO>
    {
        com.google.gwt.user.client.ui.Panel panel;

        public AddHappeningAsyncCallback( com.google.gwt.user.client.ui.Panel panel )
        {
            this.panel = panel;
        }


        public void onFailure( Throwable throwable )
        {
            // ignore failure
        }

        public void onSuccess( HappeningsDTO happeningsDTO )
        {
            addPreviousPosting( happeningsDTO, panel );
        }
    }

    public class PreviousHappeningsAsyncCallback
        implements AsyncCallback<List<HappeningsDTO>>
    {
        com.google.gwt.user.client.ui.Panel panel;

        public PreviousHappeningsAsyncCallback( com.google.gwt.user.client.ui.Panel panel )
        {
            this.panel = panel;
        }

        public void onFailure( Throwable throwable )
        {
            // ignore failures for now
        }

        public void onSuccess( List<HappeningsDTO> happeningsDTOs )
        {
            for ( HappeningsDTO happeningsDTO : happeningsDTOs )
            {
                addPreviousPosting( happeningsDTO, panel );
            }
        }
    }

    private void addPreviousPosting( HappeningsDTO happeningsDTO, com.google.gwt.user.client.ui.Panel panel )
    {
        panel.add( new Label( "Posted by " + happeningsDTO.getName() + " on " + happeningsDTO.getDate() ) );
        panel.add( new Label( happeningsDTO.getDetails() ) );
        panel.add( new Label( "---------------------------------------------------" ) );
    }
}
