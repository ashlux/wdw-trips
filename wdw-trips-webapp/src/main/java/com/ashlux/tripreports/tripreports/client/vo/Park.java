package com.ashlux.tripreports.tripreports.client.vo;

public enum Park
{
    MAGIC_KINGDOM( "MK", "Magic Kingdom" ),
    ANIMAL_KINGDOM( "AK", "Animal Kingdom" ),
    HOLLYWOOD_STUDIOS( "DHS", "Disney's Hollywood Studios" ),
    EPCOT( "EP", "EPCOT" ),
    BLIZZARD_BEACH( "BB", "Blizzard Beach" ),
    TYPHOON_LAGOON( "TL", "Typhoon Lagoon" );

    private String abbreviation;

    private String name;

    Park( String name )
    {
        this.abbreviation = name;
        this.name = name;
    }

    Park( String abbreviation, String name )
    {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public String getName()
    {
        return name;
    }
}
