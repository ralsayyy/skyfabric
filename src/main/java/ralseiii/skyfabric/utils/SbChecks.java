package ralseiii.skyfabric.utils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
// originally from skyblocker
public class SbChecks {
    public static Boolean isSkyblock = true;
    public static Boolean isCatacombs = true;
    public static void checkSkyblock() {
        List<String> list = new ArrayList<>();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;
        if (client.world == null) return;
        Scoreboard scoreboard = client.world.getScoreboard();
        if (scoreboard == null) return;
        ScoreboardObjective objective = scoreboard.getObjectiveForSlot(1);
        if (objective == null) return;
        Collection<ScoreboardPlayerScore> scores = scoreboard.getAllPlayerScores(objective);
        List<ScoreboardPlayerScore> listScoreboard = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#"))
                .collect(Collectors.toList());
        if (listScoreboard.size() > 15) {
            scores = Lists.newArrayList(Iterables.skip(listScoreboard, scores.size() - 15));
        } else {
            scores = listScoreboard;
        }

        for (ScoreboardPlayerScore score : scores) {
            Team team = scoreboard.getPlayerTeam(score.getPlayerName());
            if (team == null) return;
            String text = team.getPrefix().getString() + team.getSuffix().getString();
            if (text.trim().length() > 0)
                list.add(text);
        }

        list.add(objective.getDisplayName().getString());
        Collections.reverse(list);

        String scoreboardString = list.toString();
        if (list.get(list.size() - 1).equals("www.hypixel.net")) {
            if (list.get(0).toString().contains("SKYBLOCK")) {
                isSkyblock = true;
                if (scoreboardString.contains("The Catacombs")) {
                    isCatacombs = true;
                }
            }

        }
    }
}
