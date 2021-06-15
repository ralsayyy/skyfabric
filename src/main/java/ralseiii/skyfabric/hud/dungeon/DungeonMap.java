package ralseiii.skyfabric.hud.dungeon;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapState;
import net.minecraft.nbt.CompoundTag;
import ralseiii.skyfabric.config.ModConfig;
import ralseiii.skyfabric.utils.SbChecks;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

public class DungeonMap {
    public static void registerHUD() {
        HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> {
            if (SbChecks.isCatacombs && AutoConfig.getConfigHolder(ModConfig.class).getConfig().dungeonConfigDropdown.dungeonMapConfigDropdown.Enabled) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client == null || client.player == null || client.world == null) return;
                ItemStack item = client.player.inventory.main.get(8);
                CompoundTag tag = item.getTag();
                if (tag != null && tag.contains("map")) {
                    MapState state = FilledMapItem.getMapState(item, client.world);
                    if (state == null) return;
                    VertexConsumerProvider.Immediate vertices = client.getBufferBuilders().getEffectVertexConsumers();
                    matrixStack.push();
                    matrixStack.scale(1, 1, 0);
                    matrixStack.translate(AutoConfig.getConfigHolder(ModConfig.class).getConfig().dungeonConfigDropdown.dungeonMapConfigDropdown.uiElementPosition.x, AutoConfig.getConfigHolder(ModConfig.class).getConfig().dungeonConfigDropdown.dungeonMapConfigDropdown.uiElementPosition.y, 0);
                    client.gameRenderer.getMapRenderer().draw(matrixStack, vertices, state, false, 15728880);
                    vertices.draw();
                    matrixStack.pop();
                }
            }
        }));
    }
}
