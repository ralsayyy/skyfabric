package ralseiii.skyfabric.solvers.dungeon.entity;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.mob.CreeperEntity;
import ralseiii.skyfabric.utils.RenderUtils;

class LineCoords {
    public float x;
    public float y;
    public float z;
    public float endX;
    public float endY;
    public float endZ;
}

public class CreeperSolver {
    public static double entityX;
    public static double entityY;
    public static double entityZ;

    public static boolean drawLines = false;
    public static LineCoords[] lineCoords = {
            new LineCoords(),
            new LineCoords(),
            new LineCoords(),
            new LineCoords(),
    };

    public static void register() {
        WorldRenderEvents.END.register(context -> {
            if (drawLines) {
                for (LineCoords l : lineCoords) {
                    if (l.y == 0.0f)
                        RenderUtils.renderSolidLine(l.x, l.y, l.z, l.endX, l.endY, l.endZ, context, 255, 0, 0, 0);
                }
            }
        });
    }
    public static void solve() {
        MinecraftClient client = MinecraftClient.getInstance();
        client.world.getEntitiesByClass(CreeperEntity.class, client.player.getBoundingBox().expand(20, 20, 20), entity -> {
            return !entity.isInvisible();
        }).forEach(entity -> {
            entityX = entity.getX();
            entityY = entity.getY();
            entityZ = entity.getZ();
            lineCoords[0].x = (float) entityX;
            lineCoords[0].y = (float) entityY;
            lineCoords[0].z = (float) entityZ;
            lineCoords[0].endX = (float) entityX;
            lineCoords[0].endY = (float) entityY + 10;
            lineCoords[0].endZ = (float) entityZ;
            drawLines = true;
        });


    }
}
