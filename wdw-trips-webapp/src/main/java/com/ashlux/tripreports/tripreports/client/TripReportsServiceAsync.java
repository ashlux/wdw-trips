package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface TripReportsServiceAsync
{
    void addHappening( String name, String message, AsyncCallback<HappeningsDTO> asyncCallback );

    void getPreviousHappenings( AsyncCallback<List<HappeningsDTO>> asyncCallback );
}
