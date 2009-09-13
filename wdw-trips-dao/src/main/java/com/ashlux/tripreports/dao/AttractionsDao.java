package com.ashlux.tripreports.dao;

import com.ashlux.tripreports.domain.Attraction;

import java.util.List;

public interface AttractionsDao
{
    List<Attraction> getAttractions( String park, boolean includeClosed );

    void addAttraction( Attraction attraction );
}
