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
import ralseiii.skyfabric.utils.SbAreas;

import java.util.*;
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
            if (list.get(0).contains("SKYBLOCK")) {
                isSkyblock = true;
            } else {
                isSkyblock = false;
            }
        }

        var areaString = "";
        if (isSkyblock) {
            var playerListEntryList = client.player.networkHandler.getPlayerList();
            for (var entry : playerListEntryList) {
                var playerNameTextSiblingList = ((PlayerHudAccessor) client.inGameHud.getPlayerListHud()).invokeGetPlayerName(entry).getSiblings();
                if (playerNameTextSiblingList != null && !playerNameTextSiblingList.isEmpty()) {
                    if (playerNameTextSiblingList.get(0).getString().contains("Area: ")) {
                        areaString = playerNameTextSiblingList.get(1).getString();
                        if (areaString.contains("Dwarven Mines")) currentArea = SbAreas.DWARVEN_MINES;
                        else if (areaString.contains("Crystal Hollows")) currentArea = SbAreas.CRYSTAL_HOLLOWS;
                        else if (currentArea != SbAreas.DUNGEON) currentArea = SbAreas.OTHER;
                    }
                }
            }
        }

        if (currentArea != SbAreas.DUNGEON) ThreeWeirdos.renderOverlay = false;

        return true;
    }
}
