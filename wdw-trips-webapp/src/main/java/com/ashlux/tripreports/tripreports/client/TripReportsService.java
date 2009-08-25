package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.RemoteService;
import com.ashlux.tripreports.domain.Happening;

import java.util.List;

public interface TripReportsService
    extends RemoteService
{
    HappeningsDTO addHappening( String name, String message );

    List<HappeningsDTO> getPreviousHappenings();

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
