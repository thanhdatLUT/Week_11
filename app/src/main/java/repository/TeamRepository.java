package repository;

import java.util.List;
import model.Team;

public class TeamRepository extends Repository<Team> {

    public List<Team> filterByLeague(String league) {
        return filter(t -> t.getLeague().equalsIgnoreCase(league));
    }
}