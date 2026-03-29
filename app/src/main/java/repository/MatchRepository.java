package repository;

import model.Match;
import java.util.List;

public class MatchRepository extends Repository<Match> {
    public List<Match> filterByTeam(String teamName) {
        return filter(match ->
                match.getHomeTeam().equalsIgnoreCase(teamName) ||
                        match.getAwayTeam().equalsIgnoreCase(teamName)
        );
    }
}
