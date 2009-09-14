package com.ashlux.tripreports.tripreports.client.panels;

import com.gwtext.client.widgets.Panel;

abstract public class TripReportsPanel
    extends Panel
{
    public abstract String getName();

    public abstract Panel getViewPanel();
}
