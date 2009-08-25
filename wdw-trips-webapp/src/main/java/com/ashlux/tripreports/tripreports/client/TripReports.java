package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable;

import java.util.List;

public class TripReports
    implements EntryPoint
{
    private VerticalPanel previousHappeningsVerticalPanel = new VerticalPanel();

    private TextBox whoAreYouTextBox = new TextBox();

    private TextArea whatsHappeningTextArea = new TextArea();

    public void onModuleLoad()
    {
        RootPanel rootPanel = RootPanel.get( "slot" );
        rootPanel.setWidth( "100%" );
        rootPanel.add( createHeaderWidget() );
        rootPanel.add( createWorkspacePanel() );

        // get most recent happenings
        TripReportsService.App.getInstance().getPreviousHappenings( new PreviousHappeningsAsyncCallback() );
    }

    private Widget createHeaderWidget()
    {
        Label logoLabel = new Label( "WDW Trip Reports" );
        logoLabel.setHorizontalAlignment( Label.ALIGN_LEFT );

        VerticalPanel verticalPanel = new VerticalPanel();
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
        final VerticalPanel happeningVerticalPanel = new VerticalPanel();

        final VerticalPanel whatsHappeningPanel = new VerticalPanel();

        final HorizontalPanel whoAreYouInputPanel = new HorizontalPanel();
        whoAreYouInputPanel.add( new Label( "Who are you?" ) );
        whoAreYouInputPanel.add( whoAreYouTextBox );
        whatsHappeningPanel.add( whoAreYouInputPanel );

        final HorizontalPanel whatsHappeningInputPanel = new HorizontalPanel();
        whatsHappeningInputPanel.add( new Label( "What's happening?" ) );
        whatsHappeningInputPanel.add( whatsHappeningTextArea );
        whatsHappeningPanel.add( whatsHappeningInputPanel );

        final Button updateButton = new Button( "Update" );
        whatsHappeningPanel.add( updateButton );

        updateButton.addClickListener( new UpdateButtonClickListener() );

        happeningVerticalPanel.add( whatsHappeningPanel );
        happeningVerticalPanel.add( previousHappeningsVerticalPanel );

        return happeningVerticalPanel;
    }

    public class UpdateButtonClickListener
        implements ClickListener
    {
        public void onClick( Widget sender )
        {
            TripReportsService.App.getInstance().addHappening( whoAreYouTextBox.getText(),
                                                               whatsHappeningTextArea.getText(),
                                                               new AddHappeningAsyncCallback() );
        }
    }

    public class AddHappeningAsyncCallback
        implements AsyncCallback<HappeningsDTO>
    {

        public void onFailure( Throwable throwable )
        {
            // ignore failure
        }

        public void onSuccess( HappeningsDTO happeningsDTO )
        {
            previousHappeningsVerticalPanel.add( new Label( "Posted by " + happeningsDTO.getName() ) );
            previousHappeningsVerticalPanel.add( new Label( happeningsDTO.getDetails() ) );
            previousHappeningsVerticalPanel.add( new Label( "---------------------------------------------------" ) );
        }
    }

    public class PreviousHappeningsAsyncCallback
        implements AsyncCallback<List<HappeningsDTO>>
    {
        public void onFailure( Throwable throwable )
        {
            // ignore failures for now
        }

        public void onSuccess( List<HappeningsDTO> happeningsDTOs )
        {
            for ( HappeningsDTO happeningsDTO : happeningsDTOs )
            {
                previousHappeningsVerticalPanel.add( new Label( "Posted by " + happeningsDTO.getName() ) );
                previousHappeningsVerticalPanel.add( new Label( happeningsDTO.getDetails() ) );
                previousHappeningsVerticalPanel.add(
                    new Label( "---------------------------------------------------" ) );
            }
        }
    }
}
