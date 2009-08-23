package com.ashlux.tripreports.tripreports.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ashlux.tripreports.tripreports.client.TripReportsService;

public class TripReportsServiceImpl
    extends RemoteServiceServlet
    implements TripReportsService
{
    // Implementation of sample interface method
    public String getMessage( String msg )
    {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}