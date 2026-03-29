package com.example.week11;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Comparator;

import iterator.TeamIterator;
import model.Match;
import model.Player;
import model.Team;
import repository.MatchRepository;
import repository.PlayerRepository;
import repository.TeamRepository;
import util.DataProvider;

public class MainActivity extends AppCompatActivity {

    private LinearLayout outputContainer;
    private TeamRepository teamRepo;
    private PlayerRepository playerRepo;
    private MatchRepository matchRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        outputContainer = findViewById(R.id.outputContainer);

        DataProvider dataProvider = new DataProvider();

        teamRepo = new TeamRepository();
        playerRepo = new PlayerRepository();
        matchRepo = new MatchRepository();

        // Lambda: method reference forEach
        dataProvider.createSampleTeams().forEach(teamRepo::add);
        dataProvider.createSamplePlayers().forEach(playerRepo::add);
        dataProvider.createSampleMatches().forEach(matchRepo::add);

        // Example 1: filterByLeague (lambda inside filter)
        addHeader("=== La Liga Teams ===");
        for (Team t : teamRepo.filterByLeague("La Liga")) {
            Log.d("APP", t.getName());
            addItem(t.getName());
        }

        // Example 2: filterByTeam (lambda inside filter)
        addHeader("=== Players in Bayern Munich ===");
        for (Player p : playerRepo.filterByTeam("Bayern Munich")) {
            Log.d("APP", p.getName());
            addItem(p.getName());
        }

        // Example 3: filterByTeam on matches (lambda inside filter)
        addHeader("=== Matches for FC Barcelona ===");
        for (Match m : matchRepo.filterByTeam("FC Barcelona")) {
            String line = m.getHomeTeam() + " vs " + m.getAwayTeam() + " : " + m.getScore();
            Log.d("APP", line);
            addItem(line);
        }

        // Example 4: TeamIterator (CustomIterator<T>)
        addHeader("=== Iterating Teams ===");
        TeamIterator iterator = new TeamIterator(teamRepo.getAll());
        while (iterator.hasNext()) {
            Team t = iterator.next();
            Log.d("APP", t.getName());
            addItem(t.getName());
        }

        // Example 5: Streams API + lambda to transform data
        addHeader("=== All Teams via Stream ===");
        teamRepo.getAll()
                .stream()
                .map(t -> t.getName() + " (" + t.getLeague() + ")")
                .forEach(s -> {
                    Log.d("APP", s);
                    addItem(s);
                });

        // Example 6: Comparator with lambda to sort
        addHeader("=== Teams Sorted A-Z ===");
        teamRepo.getAll()
                .stream()
                .sorted(Comparator.comparing(Team::getName))
                .forEach(t -> {
                    Log.d("APP", t.getName());
                    addItem(t.getName());
                });

        // Example 7: Click handler with lambda
        Button filterBtn = findViewById(R.id.filterBtn);
        filterBtn.setOnClickListener(v -> {
            outputContainer.removeAllViews();

            addHeader("=== All Teams ===");
            teamRepo.getAll().forEach(t -> addItem(t.getName()));

            addHeader("=== All Players ===");
            playerRepo.getAll().forEach(p -> addItem(p.getName()));

            addHeader("=== All Matches ===");
            matchRepo.getAll().forEach(m -> addItem(m.getName()));
        });
    }

    private void addHeader(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(16f);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextColor(Color.parseColor("#1565C0"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 24, 0, 4);
        tv.setLayoutParams(params);
        outputContainer.addView(tv);
    }

    private void addItem(String text) {
        TextView tv = new TextView(this);
        tv.setText("• " + text);
        tv.setTextSize(14f);
        tv.setTextColor(Color.parseColor("#212121"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 2, 0, 2);
        tv.setLayoutParams(params);
        outputContainer.addView(tv);
    }
}