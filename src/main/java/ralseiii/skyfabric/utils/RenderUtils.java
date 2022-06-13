package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.render.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Matrix4f;
import ralseiii.skyfabric.utils.Position;

public class RenderUtils {
    public static void drawBox(Position pos, WorldRenderContext renderer, int r, int g, int b, float alpha, boolean depthTest) {
        drawBox((float) pos.x, (float) pos.y, (float) pos.z, 1, 1, 1, renderer, r, g, b, alpha, depthTest);
    }
    public static void drawBox(Position pos, ObjectSize size, WorldRenderContext renderer, int r, int g, int b, float alpha, boolean depthTest) {
        drawBox((float) pos.x, (float) pos.y, (float) pos.z, size.width, size.height, size.depth, renderer, r, g, b, alpha, depthTest);
    }

    public static void drawBox(float x, float y, float z, float width, float height, float depth, WorldRenderContext renderer, int r, int g, int b, float alpha) {
        drawBox(x,y,z,width,height,depth,renderer,r,g,b,alpha,true);
    }
    public static void drawBox(float x, float y, float z, float width, float height, float depth, WorldRenderContext renderer, int r, int g, int b, float alpha, boolean depthTest) {
        Camera camera = renderer.camera();
        RenderSystem.lineWidth(2.0f);
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        if (depthTest) RenderSystem.enableDepthTest(); else RenderSystem.disableDepthTest();
        Vec3d camPos = camera.getPos();
        renderer.matrixStack().push();
        renderer.matrixStack().translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f model = renderer.matrixStack().peek().getPositionMatrix();

        float maxX = x + width;
        float maxY = y + height;
        float maxZ = z + depth;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        float realGreen = ((float) g)/ 255;
        float realRed = ((float) r)/ 255;
        float realBlue = ((float) b)/ 255;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

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
        if (!depthTest) RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        renderer.matrixStack().pop();
    }

    public static void renderSolidLine(float x, float y, float z, float endX, float endY, float endZ, WorldRenderContext renderer, int r, int g, int b, float alpha) {
        Camera camera = renderer.camera();
        RenderSystem.disableTexture();
        RenderSystem.lineWidth(2.0f);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        Vec3d camPos = camera.getPos();
        renderer.matrixStack().push();
        renderer.matrixStack().translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f model = renderer.matrixStack().peek().getPositionMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        float realGreen = ((float) g)/ 255;
        float realRed = ((float) r)/ 255;
        float realBlue = ((float) b)/ 255;

        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

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
