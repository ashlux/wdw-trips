package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.RemoteService;

public interface TripReportsService
    extends RemoteService
{
    // Sample interface method of remote interface
    String getMessage( String msg );

    /**
     * Utility/Convenience class.
     * Use TripReportsService.App.getInstance () to access static instance of TripReportsServiceAsync
     */
    public static class App
    {
        private static TripReportsServiceAsync app = null;

        public static synchronized TripReportsServiceAsync getInstance()
        {
            if ( app == null )
            {
                app = (TripReportsServiceAsync) GWT.create( TripReportsService.class );
                ( (ServiceDefTarget) app ).setServiceEntryPoint(
                    GWT.getModuleBaseURL() + "com.ashlux.tripreports.tripreports.TripReports/TripReportsService" );
            }
            return app;
        }
    }
}
