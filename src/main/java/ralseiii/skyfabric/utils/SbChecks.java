package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import ralseiii.skyfabric.mining.CHWaypoints;
import ralseiii.skyfabric.mixin.PlayerHudAccessor;
import ralseiii.skyfabric.solvers.dungeon.chat.ThreeWeirdos;
import ralseiii.skyfabric.solvers.dungeon.entity.CreeperSolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
// originally from skyblocker

public class SbChecks {
    public static Boolean isSkyblock = false;
    public static int currentArea = 3;
    public static Boolean checkSkyblock() {
        var client = MinecraftClient.getInstance();
        if (client == null || client.world == null || client.isInSingleplayer()) return false;
        var scoreboard = client.world.getScoreboard();
        if (scoreboard == null) return false;
        var objective = scoreboard.getObjectiveForSlot(1);
        if (objective == null) return false;
        List<String> list = new ArrayList<>();

        var scores = scoreboard.getAllPlayerScores(objective);
        var listScoreboard = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#"))
                .collect(Collectors.toList());
        if (listScoreboard.size() > 15) {
            scores = Lists.newArrayList(Iterables.skip(listScoreboard, scores.size() - 15));
        } else {
            scores = listScoreboard;
        }

        for (ScoreboardPlayerScore score : scores) {
            var team = scoreboard.getPlayerTeam(score.getPlayerName());
            if (team == null) return false;
            var text = team.getPrefix().getString() + team.getSuffix().getString();
            if (text.trim().length() > 0)
                list.add(text);
        }

        list.add(objective.getDisplayName().getString());
        Collections.reverse(list);
        if (list.get(list.size() - 1).equals("www.hypixel.net")) {
            isSkyblock = list.get(0).contains("SKYBLOCK");
        }

        var areaString = "";
        if (isSkyblock) {
            var playerListEntryList = client.player.networkHandler.getPlayerList();
            for (var entry : playerListEntryList) {
                var playerNameTextSiblingList = ((PlayerHudAccessor) client.inGameHud.getPlayerListHud()).invokeGetPlayerName(entry).getSiblings();
                if (playerNameTextSiblingList != null && !playerNameTextSiblingList.isEmpty()) {
                    var firstPart = playerNameTextSiblingList.get(0).getString();
                    if (firstPart.contains("Area: ") || firstPart.contains("Dungeon: ")) {
                        areaString = playerNameTextSiblingList.get(1).getString();
                        if (areaString.contains("Dwarven Mines")) currentArea = SbAreas.DWARVEN_MINES;
                        else if (areaString.contains("Crystal Hollows")) currentArea = SbAreas.CRYSTAL_HOLLOWS;
                        else if (areaString.contains("Catacombs")) currentArea = SbAreas.DUNGEON;
                        else if (areaString.contains("Dungeon Hub")) currentArea = SbAreas.DUNGEON_HUB;
                        else currentArea = SbAreas.OTHER;
                    }
                }
            }
        }



        if (currentArea != SbAreas.DUNGEON) {
            ThreeWeirdos.renderOverlay = false;
            CreeperSolver.reset();
        }
        if (currentArea != SbAreas.CRYSTAL_HOLLOWS) CHWaypoints.reset();

        return true;
    }
}
