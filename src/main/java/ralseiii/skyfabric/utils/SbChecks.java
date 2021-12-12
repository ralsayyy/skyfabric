package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.text.Text;
import ralseiii.skyfabric.mixin.PlayerHudAccessor;
import ralseiii.skyfabric.solvers.dungeon.chat.ThreeWeirdos;

import java.util.*;
import java.util.stream.Collectors;
// originally from skyblocker

public class SbChecks {
    public static Boolean isSkyblock = false;
    public static Boolean isCatacombs = false;
    public static Boolean isCrystalHollows = false;
    public static Boolean checkSkyblock() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null || client.isInSingleplayer()) return false;
        Scoreboard scoreboard = client.world.getScoreboard();
        if (scoreboard == null) return false;
        ScoreboardObjective objective = scoreboard.getObjectiveForSlot(1);
        if (objective == null) return false;
        List<String> list = new ArrayList<>();

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
            if (team == null) return false;
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
                isCatacombs = scoreboardString.contains("The Catacombs");
                if (scoreboardString.contains("Precursor Remnants") ||
                    scoreboardString.contains("Khazad-dûm") ||
                    scoreboardString.contains("Jungle") ||
                    scoreboardString.contains("Mithril Deposits") ||
                    scoreboardString.contains("Goblin Holdout") ||
                    scoreboardString.contains("Goblin Queen's Den") ||
                    scoreboardString.contains("Lost Precursor City") ||
                    scoreboardString.contains("Crystal Nucleus") ||
                    scoreboardString.contains("Crystal Hollows") ||
                    scoreboardString.contains("Magma Fields") ||
                    scoreboardString.contains("Fairy Grotto") ||
                    scoreboardString.contains("Dragon's Lair"))
                    isCrystalHollows = true;
                if (!isCatacombs)
                    ThreeWeirdos.renderOverlay = false;
            } else {
                isSkyblock = false;
                isCatacombs = false;
                ThreeWeirdos.renderOverlay = false;
            }
        }

        // check player list for amount of secrets
        /*if (!isCatacombs) return;
        if (client.player == null) return;
        Collection<PlayerListEntry> playerListEntryList = client.player.networkHandler.getPlayerList();
        for (PlayerListEntry entry : playerListEntryList) {

            List<Text> playerNameTextSiblingList = ((PlayerHudAccessor) client.inGameHud.getPlayerListHud()).invokeGetPlayerName(entry).getSiblings();
            if (playerNameTextSiblingList != null && !playerNameTextSiblingList.isEmpty()) {
                // System.out.println(playerNameTextSiblingList.get(0));
                ScoreboardObjective scoreboardObjective2 = scoreboard.getObjectiveForSlot(1);
                if (playerNameTextSiblingList.get(0).getString().contains("Secrets found:") && scoreboardObjective2 != null) {
                    // System.out.println(playerNameTextSiblingList.get(0));
                    PlayerUtils.secretsAmount = String.valueOf(scoreboard.getPlayerScore(entry.getProfile().getName(), scoreboardObjective2).getScore());
                    System.out.println(playerNameTextSiblingList.get(0).getString()")
                    // PlayerUtils.secretsAmount = playerNameTextSiblingList.get(0).getString().substring(playerNameTextSiblingList.get(0).getString().indexOf(" "));
                }
            }
        }*/
    return true;
    }
}
