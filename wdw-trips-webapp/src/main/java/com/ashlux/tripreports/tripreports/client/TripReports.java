package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
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

        RootPanel.get( "slot" ).add( happeningVerticalPanel );

        TripReportsService.App.getInstance().getPreviousHappenings( new PreviousHappeningsAsyncCallback() );
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
