package com.ashlux.tripreports.tripreports.server.business.happening;

import com.ashlux.tripreports.domain.Happening;

import java.util.List;

public interface HappeningBusiness
{
    public Happening saveHappening( String name, String details );

    public List<Happening> getRecentHappenings();
}
