package ralseiii.skyfabric;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import ralseiii.skyfabric.Event.ItemTooltipEvent;
import ralseiii.skyfabric.config.ModConfig;
import ralseiii.skyfabric.hud.dungeon.DungeonMap;
import ralseiii.skyfabric.solvers.dungeon.chat.ThreeWeirdos;
import ralseiii.skyfabric.solvers.dwarven.PuzzlerSolver;
import ralseiii.skyfabric.utils.SbChecks;
import ralseiii.skyfabric.solvers.dungeon.entity.BlazeSolver;
import ralseiii.skyfabric.utils.api.ApiThread;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Skyfabric implements ClientModInitializer {
    private static int tickCounter = 0;
    private static int apiTickCounter = (5 * 60 * 20);
    private static final ApiThread apiThread = new ApiThread();
    @Override
    public void onInitializeClient() {
        // register config file
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        DungeonMap.register();
        ThreeWeirdos.register();
        PuzzlerSolver.register();
        BlazeSolver.register();
        apiThread.start();
        ItemTooltipCallback.EVENT.register((itemStack, context, lines) -> ItemTooltipEvent.onItemTooltip(itemStack, lines));
        // CreeperSolver.register();
    }

    public static void onTick() {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (minecraftClient == null) return;
        tickCounter++; apiTickCounter++;
        if (tickCounter / 60 == 1) {
            if (minecraftClient.world != null && !minecraftClient.isInSingleplayer()) {
                SbChecks.checkSkyblock();
               if (SbChecks.isCatacombs) {
                   BlazeSolver.blazeSolver();
                   // CreeperSolver.solve();
               }
            }
            tickCounter = 0;
        }
        if (apiTickCounter / (5 * 60 * 20) == 1) {
            synchronized (apiThread.notify) {
                apiThread.notify.notify();
            }
            apiTickCounter = 0;
        }
    }
}
