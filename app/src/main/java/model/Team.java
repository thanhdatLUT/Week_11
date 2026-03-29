package model;

public class Team implements SoccerEntity {
    private String name;
    private String country;
    private String league;
    private String stadium;
    private int foundedYear;

    public Team(String name, String country, String league, String stadium, int foundedYear) {
        this.name = name;
        this.country = country;
        this.league = league;
        this.stadium = stadium;
        this.foundedYear = foundedYear;
    }

    @Override
    public String getName() { return name; }

    public String getCountry() { return country; }
    public String getLeague() { return league; }
    public String getStadium() { return stadium; }
    public int getFoundedYear() { return foundedYear; }
}