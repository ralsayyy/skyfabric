package ralseiii.skyfabric.solvers.dungeon.chat;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.BlockPos;
import ralseiii.skyfabric.utils.RenderUtils;

public class ThreeWeirdos {
    public static boolean renderOverlay = false;
    public static double chestX = 0;
    public static double chestZ = 0;
    public static void register() {
        WorldRenderEvents.END.register((ctx) -> {
            if (ThreeWeirdos.renderOverlay)
                RenderUtils.drawBox((float) chestX, 69, (float) chestZ, 1, 1, 1, ctx, 0, 255, 0, 0.5f);
        });
    }
    public static Boolean threeWeirdosSolver(String message) {

        var isCorrectAnswer = message.contains("The reward is not in my chest!") ||
                message.contains("The reward isn't in any of our chests.") ||
                message.contains("My chest doesn't have the reward. We are all telling the truth.") ||
                message.contains("My chest has the reward and I'm telling the truth!") ||
                message.contains("At least one of them is lying, and the reward is not in") ||
                message.contains("Both of them are telling the truth. Also, ");
        if (isCorrectAnswer) {
            var client = MinecraftClient.getInstance();
            if (client == null || client.world == null) return false;
            client.world.getEntitiesByClass(ArmorStandEntity.class, client.player.getBoundingBox().expand(7, 0, 7), entity -> {
                return entity.hasCustomName() && entity.getCustomName().getString().contains(message.substring(message.indexOf("]") + 4, message.indexOf(":") - 3));
            }).forEach(entity -> {
                var npcLocation = new BlockPos(entity.getX(), 69, entity.getZ());
                if (client.world.getBlockState(npcLocation.east()).getBlock() == Blocks.CHEST) {
                    chestX = npcLocation.east().getX();
                    chestZ = npcLocation.east().getZ();
                } else if (client.world.getBlockState(npcLocation.west()).getBlock() == Blocks.CHEST) {
                    chestX = npcLocation.west().getX();
                    chestZ = npcLocation.west().getZ();
                } else if (client.world.getBlockState(npcLocation.south()).getBlock() == Blocks.CHEST) {
                    chestX = npcLocation.south().getX();
                    chestZ = npcLocation.south().getZ();
                } else if (client.world.getBlockState(npcLocation.north()).getBlock() == Blocks.CHEST) {
                    chestX = npcLocation.north().getX();
                    chestZ = npcLocation.north().getZ();
                }
                renderOverlay = true;
            });
        }
        return isCorrectAnswer;
    }
}
