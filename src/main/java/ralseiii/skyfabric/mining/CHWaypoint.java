package ralseiii.skyfabric.mining;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.math.Vec3d;
import ralseiii.skyfabric.utils.ObjectSize;
import ralseiii.skyfabric.utils.Position;
import ralseiii.skyfabric.utils.RenderUtils;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

import java.util.LinkedList;
import java.util.List;
import ralseiii.skyfabric.utils.SbChecks;

public class CHWaypoint {
    public static List<Position> waypointList = new LinkedList<Position>();
    public static ObjectSize s = new ObjectSize();

    public static void register() {
        // add position of nucleus to waypoint list
        Position nucleusPos = new Position();
        nucleusPos.set(513, 72, 513);
        waypointList.add(nucleusPos);
        s.set(1, 512, 1);

        WorldRenderEvents.END.register((context) -> {
            if (SbChecks.isCrystalHollows) {
                for (Position p : waypointList) {
                    if (MinecraftClient.getInstance().player.getPos().distanceTo(new Vec3d(p.x, p.y, p.z)) > 15)
                        RenderUtils.drawBox(p, s, context, 0, 255, 0, 0.5f, false);
                }
            }
        });


    }
}
