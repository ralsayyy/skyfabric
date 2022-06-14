package ralseiii.skyfabric.mining;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.math.Vec3d;
import ralseiii.skyfabric.utils.*;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

import java.util.LinkedList;
import java.util.List;

public class CHWaypoint {
    public static List<Position> waypointList = new LinkedList<>();
    public static ObjectSize s = new ObjectSize();

    public static void register() {
        // add position of nucleus to waypoint list
        var nucleusPos = new Position();
        nucleusPos.set(513, 72, 513);
        waypointList.add(nucleusPos);
        s.set(1, 512, 1);

        WorldRenderEvents.END.register((ctx) -> {
            if (SbChecks.currentArea == SbAreas.CRYSTAL_HOLLOWS) {
                for (var p : waypointList) {
                    var client = MinecraftClient.getInstance();
                    if (client.player.getPos().distanceTo(new Vec3d(p.x, p.y, p.z)) > 15) {
                        RenderUtils.drawBox(p, s, ctx, 0, 255, 0, 0.5f, false);
                    }
                }
            }
        });


    }
}
