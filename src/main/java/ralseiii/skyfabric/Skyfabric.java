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
import ralseiii.skyfabric.hud.dungeon.DungeonMainHUD;
import ralseiii.skyfabric.hud.dungeon.DungeonMap;
import ralseiii.skyfabric.solvers.dungeon.chat.ThreeWeirdos;
import ralseiii.skyfabric.solvers.dungeon.entity.CreeperSolver;
import ralseiii.skyfabric.solvers.dwarven.PuzzlerSolver;
import ralseiii.skyfabric.utils.SbChecks;
import ralseiii.skyfabric.solvers.dungeon.entity.BlazeSolver;
import ralseiii.skyfabric.utils.api.Bazaar;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ralseiii.skyfabric.utils.api.auctions.LowestBin;

@Environment(EnvType.CLIENT)
public class Skyfabric implements ClientModInitializer {
    private static int tickCounter = 0;
    private static final Bazaar bazaar = new Bazaar();
    private static final LowestBin lowestBin = new LowestBin();
    private static int apiTickCounter = (5 * 60 * 20);
    @Override
    public void onInitializeClient() {
        // register config file
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        // DungeonMainHUD.registerHUD();
        DungeonMap.registerHUD();
        ThreeWeirdos.register();
        PuzzlerSolver.register();
        BlazeSolver.register();
        bazaar.start();
        lowestBin.start();
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
            synchronized (bazaar.notifyObject) {
                bazaar.notifyObject.notify();
            }
            synchronized (lowestBin.notifyObject) {
                lowestBin.notifyObject.notify();
            }
            apiTickCounter = 0;
        }
    }
}
