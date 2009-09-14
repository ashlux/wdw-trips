package com.ashlux.tripreports.tripreports.client.panels;

import com.ashlux.tripreports.tripreports.client.TripReportsService;
import com.ashlux.tripreports.tripreports.client.vo.HappeningBean;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextArea;
import com.gwtext.client.widgets.form.TextField;

public class WhatsHappeningPanel
    extends TripReportsPanel
{
    private static final String NAME = "What's happening?";

    private FormPanel formPanel = new FormPanel();

    private TextField whoAreYouTextField = new TextField( "Who are you?", "name" );

    private TextArea whatsHappeningTextArea = new TextArea( "What's happening?", "name" );

    public String getName()
    {
        return NAME;
    }

    public Panel getViewPanel()
    {
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
        implements AsyncCallback<HappeningBean>
    {
        public void onFailure( Throwable throwable )
        {
            MessageBox.alert( "Failed to send new happening. Please try again." );
        }

        public void onSuccess( HappeningBean happeningBean )
        {
            MessageBox.alert( "Successfully sent new happening!" );
        }
    }
}
