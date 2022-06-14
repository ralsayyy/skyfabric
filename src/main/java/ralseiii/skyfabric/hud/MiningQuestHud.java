package ralseiii.skyfabric.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.Text;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import ralseiii.skyfabric.mixin.PlayerHudAccessor;
import ralseiii.skyfabric.utils.Position;
import ralseiii.skyfabric.utils.SbChecks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MiningQuestHud {
    static List<String> questList = new ArrayList<>();
    static String[] quests = {
            " Crystal Hunter",
            " Gemstone Collector",
            "Titanium",
            "Mithril",
            "Hard Stone Miner",
            "Ice Walker Slayer",
            "Yog Slayer",
            "Team Treasurite Member Slayer",
            "Sludge Slayer",
            "Boss Corleone Slayer",
            "Star Sentry Puncher",
            "2x Mithril Powder Collector",
            "Chest Looter",
            "Thyst Slayer"

    };
    static String mithrilPowderString = "";
    static String gemstonePowderString = "";

    public static void register() {
        HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> {
            if (!SbChecks.isCrystalHollows) return;
        }));
    }

    static boolean checkIsQuest(String str) {
        for (var s : quests) {
            if (str.contains(s)) return true;
        }
        return false;
    }

    public static void update() {
        if (!SbChecks.isCrystalHollows) return;
        List<String> tempQuestList = new ArrayList<>();
        var client = MinecraftClient.getInstance();
        if (client == null || client.world == null) return;
        var scoreboard = client.world.getScoreboard();
        if (client.player == null) return;
        var playerListEntryList = client.player.networkHandler.getPlayerList();
        for (var entry : playerListEntryList) {
            var playerNameTextSiblingList = ((PlayerHudAccessor) client.inGameHud.getPlayerListHud()).invokeGetPlayerName(entry).getSiblings();
            if (playerNameTextSiblingList != null && !playerNameTextSiblingList.isEmpty()) {
                var s = playerNameTextSiblingList.get(0).getString();
                if (s.contains("Mithril Powder")) {
                    mithrilPowderString = s;
                } else if (s.contains("Gemstone Powder")) {
                    gemstonePowderString = s;
                } else if (checkIsQuest(s)) {
                    tempQuestList.add(s);
                }
            }
        }
        questList = tempQuestList;
    }
}
