package ralseiii.skyfabric.hud;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import com.mojang.blaze3d.systems.RenderSystem;
// import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;
import ralseiii.skyfabric.config.ModConfig;
import ralseiii.skyfabric.utils.SbAreas;
import ralseiii.skyfabric.utils.SbChecks;

public class CrystalHollowsMap {
    public static Identifier identifier = new Identifier("skyfabric:textures/ui/ch.png");
    public static Identifier arrowId = new Identifier("skyfabric:textures/ui/arrow.png");
    public static void register() {
        HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> {
            if (SbChecks.currentArea == SbAreas.CRYSTAL_HOLLOWS) {
                var client = MinecraftClient.getInstance();
                if (client != null && client.player != null && client.options != null && !client.options.debugEnabled && /* AutoConfig.getConfigHolder(ModConfig.class).getConfig().dungeonConfigDropdown.dungeonMapConfigDropdown.Enabled*/ true) {
                    var tessellator = Tessellator.getInstance();
                    matrixStack.push();
                    matrixStack.scale(1f, 1f, 1f);
                    RenderSystem.setShaderTexture(0, identifier);
                    DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, 125, 125, 125, 125);
                    matrixStack.pop();
                    matrixStack.push();
                    var x = client.player.getX();
                    var z = client.player.getZ();
                    matrixStack.translate((Math.round(Math.max(0, Math.min(x - 200, 624)) / 4.992)), (int) Math.round(Math.max(0, Math.min(z - 204, 624)) / 4.992), 0);
                    matrixStack.multiply(new Quaternion(new Vec3f(0, 0, 1), client.player.getHeadYaw() + 180.0f, true));
                    matrixStack.scale(1.5f, 1.5f, 1.5f);
                    matrixStack.translate(-0.125, 0.125f, 0.0f);
                    client.getTextureManager().bindTexture(arrowId);
                    RenderSystem.setShaderTexture(0, arrowId);
                    var bufferBuilder = tessellator.getBuffer();
                    var model = matrixStack.peek().getPositionMatrix();
                    RenderSystem.enableTexture();
                    // why do both VertexFormat and VertexFormats exist
                    bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
                    bufferBuilder.vertex(model, -8, -8, 100).texture(0, 0).next();
                    bufferBuilder.vertex(model, -8, 8, 100).texture(0, 0.5f).next();
                    bufferBuilder.vertex(model, 8, 4, 100).texture(0.5f, 0.5f).next();
                    bufferBuilder.vertex(model, 8, 4, 100 ).texture(0.5f, 0).next();
                    tessellator.draw();
                    matrixStack.pop();
                }
            }
            }));
    }
}
