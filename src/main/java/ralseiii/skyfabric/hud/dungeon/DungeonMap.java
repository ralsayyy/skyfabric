package ralseiii.skyfabric.hud.dungeon;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.MaterialColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import ralseiii.skyfabric.config.Config;
import ralseiii.skyfabric.utils.PlayerUtils;
import ralseiii.skyfabric.utils.SbChecks;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

public class DungeonMap {
    public static void registerHUD() {
        /*HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> {
            if (SbChecks.isCatacombs && Config.config.dungeonMap) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client == null || client.player == null || client.world == null) return;
                ItemStack item = client.player.inventory.main.get(8);
                CompoundTag tag = item.getTag();

                if (tag != null && tag.contains("map")) {
                    MapState state = FilledMapItem.getMapState(item, client.world);
                    // convert the map into a texture
                    NativeImage mapImage = new NativeImage(128, 128, true);
                    if (state == null) return;
                    for (int y = 0; y < 128; y++) {
                        for (int x = 0; x < 128; x++) {
                            if (state.colors[x + y * 128] != 0)
                                mapImage.setPixelColor(x, y, MaterialColor.COLORS[(state.colors[x + y * 128] & 255) / 4].getRenderColor((state.colors[x + y * 128] & 255) & 3));
                        }
                    }
                    TextureManager textureManager = client.getTextureManager();
                    if (textureManager == null) return;
                    // register it with the same id to prevent memory leak
                    if (!PlayerUtils.hasRegisteredMapTexture) {
                        PlayerUtils.mapBackedTexture = new NativeImageBackedTexture(mapImage);
                        PlayerUtils.mapTextureId = textureManager.registerDynamicTexture("aaaaaaaa", PlayerUtils.mapBackedTexture);
                        PlayerUtils.hasRegisteredMapTexture = true;
                    } else {
                        textureManager.registerTexture(PlayerUtils.mapTextureId, new NativeImageBackedTexture(mapImage));
                    }
                    matrixStack.push();
                    matrixStack.scale(1, 1, 0);
                    textureManager.getTexture(PlayerUtils.mapTextureId);
                    textureManager.bindTexture(PlayerUtils.mapTextureId);
                    DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, 128, 128, 128, 128);
                    matrixStack.pop();
                }
            }
        }));*/
    }
}
