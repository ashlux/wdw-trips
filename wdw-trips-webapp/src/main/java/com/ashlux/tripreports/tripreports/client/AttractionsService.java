package com.ashlux.tripreports.tripreports.client;

import com.ashlux.tripreports.tripreports.client.vo.AttractionBean;
import com.ashlux.tripreports.tripreports.client.vo.Park;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import java.util.List;

public interface AttractionsService
    extends RemoteService
{

    AttractionBean addAttraction( AttractionBean attraction );

    List<AttractionBean> getAttractions( Park park );

    public static class App
    {
        private static TripReportsServiceAsync app = null;

        public static synchronized TripReportsServiceAsync getInstance()
        {
            if ( app == null )
            {
                app = (TripReportsServiceAsync) GWT.create( AttractionsService.class );
                ( (ServiceDefTarget) app ).setServiceEntryPoint(
                    GWT.getModuleBaseURL() + "com.ashlux.tripreports.tripreports.TripReports/AttractionsService" );
            }
            return app;
        }
    }
}