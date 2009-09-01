package com.ashlux.tripreports.tripreports.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ashlux.tripreports.tripreports.client.vo.HappeningBean;

import java.util.List;

public interface TripReportsServiceAsync
{
    void addHappening( String name, String message, AsyncCallback<HappeningBean> asyncCallback );

    void getPreviousHappenings( AsyncCallback<List<HappeningBean>> asyncCallback );
}
