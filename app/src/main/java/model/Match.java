package model;

public class Match implements SoccerEntity {
    private String homeTeam;
    private String awayTeam;
    private String score;
    private String competition;
    private String date;
    private String stadium;

    public Match(String homeTeam, String awayTeam, String score, String competition, String date, String stadium) {
        if (homeTeam == null || awayTeam == null) throw new IllegalArgumentException("Teams required");
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.competition = competition;
        this.date = date;
        this.stadium = stadium;
    }

    @Override public String getName() { return homeTeam + " vs " + awayTeam; }
    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public String getScore() { return score; }
    public String getCompetition() { return competition; }
    public String getDate() { return date; }
    public String getStadium() { return stadium; }
}