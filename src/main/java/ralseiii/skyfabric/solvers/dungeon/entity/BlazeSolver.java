package ralseiii.skyfabric.solvers.dungeon.entity;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.ArmorStandEntity;
import ralseiii.skyfabric.utils.Position;
import ralseiii.skyfabric.utils.RenderUtils;
import ralseiii.skyfabric.utils.SbChecks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

class BlazeInfo implements Comparable<BlazeInfo> {
    Integer health = 0;
    Position pos = new Position();

    @Override
    public int compareTo(BlazeInfo o) {
        return health.compareTo(o.health);
    }
}

public class BlazeSolver {
    static Boolean isHigher = false;
    static List<BlazeInfo> blazeList = new LinkedList<>();
    public static void register() {
        WorldRenderEvents.END.register((context) -> {
            if (SbChecks.isCatacombs && blazeList.size() != 0) {
                BlazeInfo blaze;
                BlazeInfo blaze2;
                if (isHigher) {
                    blaze = blazeList.get(0);
                    blaze2 = blazeList.get(blazeList.size() - 1);
                } else {
                    blaze = blazeList.get(blazeList.size() - 1);
                    blaze2 = blazeList.get(0);
                }

                RenderUtils.drawBox((float) blaze.pos.x - 0.5f, (float) blaze.pos.y, (float) blaze.pos.z - 0.5f, 1, 2, 1, context, 0, 255, 0, 0.7f);
                RenderUtils.drawBox((float) blaze2.pos.x - 0.5f, (float) blaze2.pos.y, (float) blaze2.pos.z - 0.5f, 1, 2, 1, context, 255, 0, 0, 0.7f);

            }
        });
    }
    public static void blazeSolver() {
        var client = MinecraftClient.getInstance();
        if (client == null || client.world == null || client.player == null) return;
        blazeList.clear();
        client.world.getEntitiesByClass(ArmorStandEntity.class, client.player.getBoundingBox().expand(20, 70, 20), entity -> {
            return entity.hasCustomName() && entity.getCustomName().getString().contains("Blaze");
        }).forEach(entity -> {
            var name = entity.getCustomName().getString();
            try {
                var info = new BlazeInfo();
                info.pos.set(entity.getX(), entity.getY(), entity.getZ());
                info.health = Integer.parseInt(name.substring(name.indexOf("e") + 3, name.indexOf("/") - 2));
                blazeList.add(info);
            } catch (NumberFormatException ignored) {
                System.out.println("blaze number format invalid");
            }
        });

        if (blazeList.isEmpty()) return;
        Collections.sort(blazeList);

        // decide whether to highlight the highest or lowest blaze
        List<Position> pList = new LinkedList<>();
        for (var b : blazeList) {
            pList.add(b.pos);
        }
        Collections.sort(pList);
        isHigher = !(pList.get(pList.size() - 1).y == 112);
    }
}