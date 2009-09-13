package com.ashlux.tripreports.dao;

import com.ashlux.tripreports.domain.Happening;

import java.util.List;

public interface HappeningDao
{
    /**
     * Retrieve records starting at the offset-record and retrieve limit records.
     *
     * Limit = 0 results in zero records retrieved.
     *
     * @param offset start point
     * @param limit number of records to pull back
     * @return list of records, not null
     */
    List<Happening> getRecentHappenings( int offset, int limit );

    void saveHappening( Happening happening );
}
