package ralseiii.skyfabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.MinecraftClient;
import ralseiii.skyfabric.config.Config;
import ralseiii.skyfabric.utils.SbChecks;
import ralseiii.skyfabric.solvers.dungeon.entity.BlazeSolver;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class Skyfabric implements ClientModInitializer {
    private static int tickCounter = 0;
    public Config.ModConfig config;
    @Override
    public void onInitializeClient() {
        // register config file
        AutoConfig.register(Config.ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Config.ModConfig.class).getConfig();

    }

    public static void onTick() {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (minecraftClient == null) return;
        tickCounter++;
        if (tickCounter % 60 == 0) {
            if (minecraftClient.world != null && !minecraftClient.isInSingleplayer()) {
                SbChecks.checkSkyblock();
                if (SbChecks.isCatacombs)
                    BlazeSolver.blazeSolver();
            }
            tickCounter = 0;
        }
    }
}
