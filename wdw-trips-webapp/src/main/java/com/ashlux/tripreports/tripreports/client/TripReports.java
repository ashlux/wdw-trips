package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

public class TripReports
    implements EntryPoint
{
    private VerticalPanel previousHappeningsVerticalPanel = new VerticalPanel();

    private TextBox whoAreYouTextBox = new TextBox();

    private TextArea whatsHappeningTextArea = new TextArea();

    public void onModuleLoad()
    {
        @SuppressWarnings({"GwtToHtmlReferences"})
        RootPanel rootPanel = RootPanel.get( "slot" );
        rootPanel.setWidth( "100%" );
        rootPanel.add( createHeaderWidget() );
        rootPanel.add( createWorkspacePanel() );
        rootPanel.add( previousHappeningsVerticalPanel );

        // get most recent happenings
        TripReportsService.App.getInstance().getPreviousHappenings( new PreviousHappeningsAsyncCallback(previousHappeningsVerticalPanel) );
    }

    private Widget createHeaderWidget()
    {
        Label logoLabel = new Label( "WDW Trip Reports" );
        logoLabel.setWidth( "100%" );
        logoLabel.setHorizontalAlignment( Label.ALIGN_LEFT );

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setWidth( "100%" );
        verticalPanel.setHorizontalAlignment( VerticalPanel.ALIGN_RIGHT );
        verticalPanel.setVerticalAlignment( VerticalPanel.ALIGN_MIDDLE );
        verticalPanel.add( new Label( "username" ) );

        HorizontalPanel linkHorizontalPanel = new HorizontalPanel();
        linkHorizontalPanel.add( new Label( "My profile" ) );
        linkHorizontalPanel.add( new Label( ", " ) );
        linkHorizontalPanel.add( new Label( "Settings" ) );
        linkHorizontalPanel.add( new Label( ", " ) );
        linkHorizontalPanel.add( new Label( "Logout" ) );
        verticalPanel.add( linkHorizontalPanel );

        DockPanel headerDockPanel = new DockPanel();
        headerDockPanel.add( logoLabel, DockPanel.WEST );
        headerDockPanel.add( verticalPanel, DockPanel.EAST );

        headerDockPanel.setWidth( "100%" );

        return headerDockPanel;
    }

    private Widget createWorkspacePanel()
    {
        TabPanel workspaceTapPanel = new TabPanel();
        workspaceTapPanel.setAnimationEnabled( true );
        workspaceTapPanel.setWidth( "100%" );
        workspaceTapPanel.add( createWhatsHappeningWidget(), "What's happening?" );
        workspaceTapPanel.add( new Label( "Content2" ), "Your trips" );
        workspaceTapPanel.add( new Label( "Content3" ), "Search" );
        workspaceTapPanel.add( new Label( "Content4" ), "Settings" );
        return workspaceTapPanel;
    }

    private Widget createWhatsHappeningWidget()
    {
        Grid grid = new Grid( 3, 2 );
        grid.setSize( "200px", "300px" );

        Label whoAreYouLabel = new Label( "Who are you?" );
        whoAreYouLabel.setHorizontalAlignment( Label.ALIGN_RIGHT );
        grid.setWidget( 0, 0, whoAreYouLabel );
        grid.setWidget( 0, 1, whoAreYouTextBox );

        Label whatsHappeningLabel = new Label( "What's happening?" );
        whatsHappeningLabel.setHorizontalAlignment( Label.ALIGN_RIGHT );
        grid.setWidget( 1, 0, whatsHappeningLabel );
        grid.setWidget( 1, 1, whatsHappeningTextArea );

        final Button updateButton = new Button( "Update" );
        updateButton.addClickHandler( new UpdateButtonClickHandler() );

        grid.setWidget( 2, 1, updateButton );

        return grid;
    }

    public class UpdateButtonClickHandler
        implements ClickHandler
    {
        public void onClick( ClickEvent clickEvent )
        {
            TripReportsService.App.getInstance().addHappening( whoAreYouTextBox.getText(),
                                                               whatsHappeningTextArea.getText(),
                                                               new AddHappeningAsyncCallback(previousHappeningsVerticalPanel) );
        }
    }

    public class AddHappeningAsyncCallback
        implements AsyncCallback<HappeningsDTO>
    {
        Panel panel;

        public AddHappeningAsyncCallback( Panel panel )
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
        Panel panel;

        public PreviousHappeningsAsyncCallback( Panel panel )
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

    private void addPreviousPosting( HappeningsDTO happeningsDTO, Panel panel )
    {
        panel.add(
            new Label( "Posted by " + happeningsDTO.getName() + " on " + happeningsDTO.getDate() ) );
        panel.add( new Label( happeningsDTO.getDetails() ) );
        panel.add( new Label( "---------------------------------------------------" ) );
    }
}
