package com.ashlux.tripreports.tripreports.server.service;

import com.ashlux.tripreports.tripreports.client.AttractionsService;
import com.ashlux.tripreports.tripreports.client.vo.AttractionBean;
import com.ashlux.tripreports.tripreports.client.vo.Park;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;

public class AttractionsServiceImpl
    extends RemoteServiceServlet
    implements AttractionsService
{
    public AttractionBean addAttraction( AttractionBean attraction )
    {
        return null;
    }

    public List<AttractionBean> getAttractions( Park park )
    {
        return null;
    }
}