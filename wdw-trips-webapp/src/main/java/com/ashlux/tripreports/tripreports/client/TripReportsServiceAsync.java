package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TripReportsServiceAsync
{
    void getMessage( String msg, AsyncCallback<String> async );
}
