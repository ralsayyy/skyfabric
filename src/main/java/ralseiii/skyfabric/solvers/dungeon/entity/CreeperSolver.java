package ralseiii.skyfabric.solvers.dungeon.entity;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.mob.CreeperEntity;
import ralseiii.skyfabric.utils.Position;
import ralseiii.skyfabric.utils.RenderUtils;
import ralseiii.skyfabric.utils.SbAreas;
import ralseiii.skyfabric.utils.SbChecks;

class LineCoords {
    public Position startPos = new Position();
    public Position endPos = new Position();
}

public class CreeperSolver {
    static Position entityPos;

    public static boolean drawLines = false;
    static LineCoords[] lineCoords = {
            new LineCoords(),
            new LineCoords(),
            new LineCoords(),
            new LineCoords(),
    };

    public static void register() {
        WorldRenderEvents.END.register(ctx -> {
            if (drawLines && SbChecks.currentArea == SbAreas.DUNGEON) {
                for (var l : lineCoords) {
                    if (l.startPos.y != 0.0f)
                        RenderUtils.renderSolidLine((float) l.startPos.x, (float) l.startPos.y, (float) l.endPos.z, (float) l.endPos.x, (float) l.endPos.y, (float) l.endPos.z, ctx, 255, 0, 0, 0);
                }
            }
        });
    }
    public static void solve() {
        var client = MinecraftClient.getInstance();
        if (client == null || client.world == null || client.player == null) return;
        client.world.getEntitiesByClass(CreeperEntity.class, client.player.getBoundingBox().expand(20, 20, 20), entity -> {
            return !entity.isInvisible();
        }).forEach(entity -> {
            entityPos.set(entity.getX(), entity.getY(), entity.getZ());
            lineCoords[0].startPos.set(entityPos);
            lineCoords[0].endPos.set(entityPos);
            lineCoords[0].endPos.y += 10;
            drawLines = true;
        });


    }
}
