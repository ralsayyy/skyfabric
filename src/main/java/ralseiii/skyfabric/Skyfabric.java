package ralseiii.skyfabric;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import ralseiii.skyfabric.utils.SbChecks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Skyfabric implements ClientModInitializer {
    private static int tickCounter = 0;
    @Override
    public void onInitializeClient() {

    }

    public static void onTick() {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (minecraftClient == null) return;
        tickCounter++;
        if (tickCounter % 60 == 0) {
            if (minecraftClient.world != null && !minecraftClient.isInSingleplayer()) {
                SbChecks.checkSkyblock();
            }
            tickCounter = 0;
        }
    }
}
