package ralseiii.skyfabric.solvers.dungeon.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class BlazeSolver {
    public static int highestHealth = 0;
    public static int lowestHealth = 0;
    public static void blazeSolver() {
        highestHealth = 0;
        lowestHealth = 1234567890;
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (minecraftClient == null || minecraftClient.world == null) return;
        minecraftClient.world.getEntitiesByClass(ArmorStandEntity.class, minecraftClient.player.getBoundingBox().expand(20), entity -> {
            return entity.hasCustomName() && entity.getCustomName().getString().contains("Blaze");
        }).forEach(entity -> {
            String blazeName = entity.getCustomName().getString();
            String healthAsString = blazeName.substring(blazeName.indexOf("e") + 3, blazeName.indexOf("/") - 2);
            if (Integer.parseInt(healthAsString) > highestHealth) {
                highestHealth = Integer.parseInt(healthAsString);
            }
            if (Integer.parseInt(healthAsString) < lowestHealth) {
                lowestHealth = Integer.parseInt(healthAsString);
            }
        });
        // it would be nicer if it could be done in the same loop
        minecraftClient.world.getEntitiesByClass(ArmorStandEntity.class, minecraftClient.player.getBoundingBox().expand(20), entity -> {
            return entity.hasCustomName() && entity.getCustomName().getString().contains("Blaze");
        }).forEach(entity -> {
            String blazeName = entity.getCustomName().getString();
            String healthAsString = blazeName.substring(blazeName.indexOf("e") + 3, blazeName.indexOf("/") - 2);
            if (blazeName.contains("x")) blazeName = blazeName.substring(4);
            if (Integer.parseInt(healthAsString) == highestHealth) {
                entity.setCustomName(Text.of(Formatting.BLUE + "Higher " + blazeName));
                return;
            }
            if (Integer.parseInt(healthAsString) == lowestHealth) {
                entity.setCustomName(Text.of(Formatting.GREEN + "Lowest " + blazeName));
                return;
            }
            entity.setCustomName(Text.of(Formatting.RED + blazeName));
        });
    }
}