package com.ashlux.tripreports.tripreports.client;

import com.ashlux.tripreports.tripreports.client.vo.AttractionBean;
import com.ashlux.tripreports.tripreports.client.vo.Park;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface AttractionsServiceAsync
{
    void addAttraction( AttractionBean attraction, AsyncCallback<AttractionBean> asyncCallback );

    void getAttractions( Park park, AsyncCallback<List<AttractionBean>> asyncCallback );
}