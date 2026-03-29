package iterator;

import model.Team;
import java.util.List;
import java.util.NoSuchElementException;

public class TeamIterator implements CustomIterator<Team> {
    private final List<Team> teams;
    private int position = 0;

    public TeamIterator(List<Team> teams) {
        if (teams == null) throw new IllegalArgumentException("List cannot be null");
        this.teams = teams;
    }

    @Override
    public boolean hasNext() { return position < teams.size(); }

    @Override
    public Team next() {
        if (!hasNext()) throw new NoSuchElementException("No more teams");
        return teams.get(position++);
    }
}
