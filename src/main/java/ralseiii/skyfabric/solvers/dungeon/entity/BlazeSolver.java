package ralseiii.skyfabric.solvers.dungeon.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

public class BlazeSolver {
    public static int highestHealth = 0;
    public static int lowestHealth = 0;
    public static void blazeSolver() {
        highestHealth = 0;
        lowestHealth = 1234567890;
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (minecraftClient == null || minecraftClient.world == null || minecraftClient.player == null) return;
        minecraftClient.world.getEntitiesByClass(ArmorStandEntity.class, minecraftClient.player.getBoundingBox().expand(20, 70, 20), entity -> {
            return entity.hasCustomName() && entity.getCustomName().getString().contains("Blaze");
        }).forEach(entity -> {
            String blazeName = entity.getCustomName().getString();
            String healthAsString = blazeName.substring(blazeName.indexOf("e") + 3, blazeName.indexOf("/") - 2);
            if (blazeName.contains("x")) blazeName = blazeName.substring(4);
            try {
                int health = Integer.parseInt(healthAsString);
                if (health > highestHealth) {
                    highestHealth = health;
                }
                if (health < lowestHealth) {
                    lowestHealth = health;
                }
            } catch (java.lang.NumberFormatException exception) {
            }
        });
        minecraftClient.world.getEntitiesByClass(ArmorStandEntity.class, minecraftClient.player.getBoundingBox().expand(20, 70, 20), entity -> {
            return entity.hasCustomName() && entity.getCustomName().getString().contains("Blaze");
        }).forEach(entity -> {
            String blazeName = entity.getCustomName().getString();
            String healthAsString = blazeName.substring(blazeName.indexOf("e") + 3, blazeName.indexOf("/") - 2);
            if (blazeName.contains("x")) blazeName = blazeName.substring(4);
            try {
                int health = Integer.parseInt(healthAsString);
                if (health == highestHealth) {
                    entity.setCustomName(Text.of(Formatting.BLUE + "Highest " + blazeName));
                    return;
                }
                if (health == lowestHealth) {
                    entity.setCustomName(Text.of(Formatting.GREEN + "Lowest " + blazeName));
                    return;
                }
            } catch (java.lang.NumberFormatException exception) {
                return;
            }

            entity.setCustomName(Text.of(Formatting.RED + blazeName));
        });
    }
}