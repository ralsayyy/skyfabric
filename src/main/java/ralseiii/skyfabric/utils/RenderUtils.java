package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    public static void renderSolidBox(float x, float y, float z, float width, float height, float depth, WorldRenderContext renderer, int r, int g, int b, float alpha) {
        Camera camera = BlockEntityRenderDispatcher.INSTANCE.camera;
        RenderSystem.lineWidth(2.0f);
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        Vec3d camPos = camera.getPos();
        renderer.matrixStack().push();
        renderer.matrixStack().translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f model = renderer.matrixStack().peek().getModel();

        float maxX = x + width;
        float maxY = y + height;
        float maxZ = z + depth;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        float realGreen = ((float) g)/ 255;
        float realRed = ((float) r)/ 255;
        float realBlue = ((float) b)/ 255;

        bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(model, x, y, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, y, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, y, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, x, y, maxZ).color(realRed, realGreen, realBlue, alpha).next();

        bufferBuilder.vertex(model, x, maxY, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, x, maxY, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, z).color(realRed, realGreen, realBlue, alpha).next();

        bufferBuilder.vertex(model, x, y, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, x, maxY, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, y, z).color(realRed, realGreen, realBlue, alpha).next();

        bufferBuilder.vertex(model, maxX, y, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, y, maxZ).color(realRed, realGreen, realBlue, alpha).next();

        bufferBuilder.vertex(model, x, y, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, y, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, x, maxY, maxZ).color(realRed, realGreen, realBlue, alpha).next();

        bufferBuilder.vertex(model, x, y, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, x, y, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, x, maxY, maxZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, x, maxY, z).color(realRed, realGreen, realBlue, alpha).next();
        tessellator.draw();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        renderer.matrixStack().pop();
    }

    public static void renderSolidLine(float x, float y, float z, float endX, float endY, float endZ, WorldRenderContext renderer, int r, int g, int b, float alpha) {
        Camera camera = BlockEntityRenderDispatcher.INSTANCE.camera;
        RenderSystem.disableTexture();
        RenderSystem.lineWidth(2.0f);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        Vec3d camPos = camera.getPos();
        renderer.matrixStack().push();
        renderer.matrixStack().translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f model = renderer.matrixStack().peek().getModel();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        float realGreen = ((float) g)/ 255;
        float realRed = ((float) r)/ 255;
        float realBlue = ((float) b)/ 255;

        bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(model, x, y, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, x, y + 0.1f, z).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, endX, endY, endZ).color(realRed, realGreen, realBlue, alpha).next();
        bufferBuilder.vertex(model, endX, endY + 0.1f, endZ).color(realRed, realGreen, realBlue, alpha).next();
        tessellator.draw();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        renderer.matrixStack().pop();
    }
}
