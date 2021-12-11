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
    static List<BlazeInfo> blazeList = new LinkedList<>();
    public static void register() {
        WorldRenderEvents.END.register((context) -> {
            if (SbChecks.isCatacombs && blazeList.size() != 0) {
                BlazeInfo lowestBlaze = blazeList.get(0);
                BlazeInfo highestBlaze = blazeList.get(blazeList.size() - 1);
                RenderUtils.renderSolidBox((float) lowestBlaze.pos.x - 0.5f, (float) lowestBlaze.pos.y, (float) lowestBlaze.pos.z - 0.5f, 1, 2, 1, context, 0, 255, 0, 0.7f);
                RenderUtils.renderSolidBox((float) highestBlaze.pos.x - 0.5f, (float) highestBlaze.pos.y, (float) highestBlaze.pos.z - 0.5f, 1, 2, 1, context, 255, 0, 0, 0.7f);
            }
        });
    }
    public static void blazeSolver() {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        blazeList.clear();
        if (minecraftClient == null || minecraftClient.world == null || minecraftClient.player == null) return;
        minecraftClient.world.getEntitiesByClass(ArmorStandEntity.class, minecraftClient.player.getBoundingBox().expand(20, 70, 20), entity -> {
            return entity.hasCustomName() && entity.getCustomName().getString().contains("Blaze");
        }).forEach(entity -> {
            String name = entity.getCustomName().getString();
            try {
                BlazeInfo info = new BlazeInfo();
                info.pos.set(entity.getX(), entity.getY(), entity.getZ());
                info.health = Integer.parseInt(name.substring(name.indexOf("e") + 3, name.indexOf("/") - 2));
                blazeList.add(info);
            } catch (NumberFormatException ignored) {
                System.out.println("blaze number format invalid");
            }
        });
        Collections.sort(blazeList);
    }
}