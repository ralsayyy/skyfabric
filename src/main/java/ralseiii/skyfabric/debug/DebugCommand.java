package ralseiii.skyfabric.debug;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import ralseiii.skyfabric.utils.SbChecks;

public class DebugCommand {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("sfdebug").executes(context -> {
                        run();
                        return 0;
                    })
            );
        });

    }

    public static void run() {
        var client = MinecraftClient.getInstance();
        client.player.sendMessage(Text.of("§l§nSkyfabric debug info§r"));
        client.player.sendMessage(Text.of("current area: " + SbChecks.currentArea));
    }
}
