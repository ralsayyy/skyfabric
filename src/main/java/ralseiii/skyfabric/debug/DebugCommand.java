package ralseiii.skyfabric.debug;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import ralseiii.skyfabric.utils.SbAreas;
import ralseiii.skyfabric.utils.SbChecks;

public class DebugCommand {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("sfdebug").
                            then(ClientCommandManager.argument("feature", StringArgumentType.string())
                                    .executes(ctx -> {
                                        run(StringArgumentType.getString(ctx, "feature"));
                                        return 0;
                                    }))
                            .executes(context -> {
                                run("general");
                                return 0;
                            })
            );
        });

    }

    public static void run(String feature) {
        var client = MinecraftClient.getInstance();
        client.player.sendMessage(Text.of("§l§nSkyfabric debug info§r"));
        client.player.sendMessage(Text.of("currentArea: " + SbChecks.currentArea + " (" + SbAreas.areaToString(SbChecks.currentArea) + ")" ));
        client.player.sendMessage(Text.of("isSkyblock: " + SbChecks.isSkyblock));
    }
}
