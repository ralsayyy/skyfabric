package ralseiii.skyfabric.mining;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;
import ralseiii.skyfabric.utils.*;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

import java.util.LinkedList;
import java.util.List;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static net.minecraft.command.argument.ColorArgumentType.color;


class CHWaypoint {
    public String color;
    public Position pos;

    public CHWaypoint(String c, Position p) {
        color = c;
        pos = p;
    }
}
public class CHWaypoints {
    public static List<CHWaypoint> waypointList = new LinkedList<>();
    static ObjectSize s = new ObjectSize();

    static void addWaypoint(int x, int y, int z, String color) {
        waypointList.add(new CHWaypoint(color, new Position(x, y, z)));
    }

    public static void reset() {
        waypointList.clear();
        waypointList.add(new CHWaypoint("#00ff00", new Position(513, 72, 513)));
    }

    public static void register() {
        // add position of nucleus to waypoint list
        waypointList.add(new CHWaypoint("#00ff00", new Position(513, 72, 513)));
        s.set(1, 512, 1);

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("sfchwp")
                            .then(ClientCommandManager.argument("x", integer())
                                    .then(ClientCommandManager.argument("y", integer())
                                            .then(ClientCommandManager.argument("z", integer())
                                                    .then(ClientCommandManager.argument("color", string())
                                                            .executes(ctx -> {
                                                                addWaypoint(getInteger(ctx, "x"), getInteger(ctx, "y"), getInteger(ctx, "z"), getString(ctx, "color"));
                                                                return 0;
                                                            })))))

            );
        });

        WorldRenderEvents.END.register((ctx) -> {
            if (SbChecks.currentArea == SbAreas.CRYSTAL_HOLLOWS) {
                for (var wp : waypointList) {
                    var client = MinecraftClient.getInstance();
                    if (client.player.getPos().distanceTo(new Vec3d(wp.pos.x, wp.pos.y, wp.pos.z)) > 15) {
                        RenderUtils.drawBox(wp.pos, s, ctx, 0, 255, 0, 0.5f, false);
                    }
                }
            }
        });


    }
}
