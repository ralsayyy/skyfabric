package ralseiii.skyfabric.hud.dungeon;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
// import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapState;
import net.minecraft.nbt.NbtCompound;
import ralseiii.skyfabric.config.ModConfig;
import ralseiii.skyfabric.utils.SbAreas;
import ralseiii.skyfabric.utils.SbChecks;



public class DungeonMap {
    public static void register() {
        HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> {
            if (SbChecks.currentArea == SbAreas.DUNGEON && /* AutoConfig.getConfigHolder(ModConfig.class).getConfig().dungeonConfigDropdown.dungeonMapConfigDropdown.Enabled */ true) {
                var client = MinecraftClient.getInstance();
                if (client == null || client.player == null || client.world == null) return;
                var item = client.player.getInventory().main.get(8);
                var tag = item.getNbt();
                if (tag != null && tag.contains("map")) {
                    var state = FilledMapItem.getMapState(FilledMapItem.getMapId(item), client.world);
                    if (state == null) return;
                    var verticesImmediate = client.getBufferBuilders().getEffectVertexConsumers();
                    var vertices = client.getBufferBuilders().getEffectVertexConsumers();
                    matrixStack.push();
                    matrixStack.scale(1, 1, 0);
                    matrixStack.translate(/* AutoConfig.getConfigHolder(ModConfig.class).getConfig().dungeonConfigDropdown.dungeonMapConfigDropdown.uiElementPosition.x */ 0, /* AutoConfig.getConfigHolder(ModConfig.class).getConfig().dungeonConfigDropdown.dungeonMapConfigDropdown.uiElementPosition.y */ 0, 0);
                    client.gameRenderer.getMapRenderer().draw(matrixStack, vertices, FilledMapItem.getMapId(item), state, false, 15728880);

                    verticesImmediate.draw();

                    matrixStack.pop();
                }
            }
        }));
    }
}
