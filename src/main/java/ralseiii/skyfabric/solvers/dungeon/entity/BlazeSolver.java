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
        System.out.println("a");
        lowestHealth = 1234567890;
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (minecraftClient == null || minecraftClient.world == null) return;
        minecraftClient.world.getEntitiesByClass(ArmorStandEntity.class, minecraftClient.player.getBoundingBox().expand(20, 40, 20), entity -> {
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