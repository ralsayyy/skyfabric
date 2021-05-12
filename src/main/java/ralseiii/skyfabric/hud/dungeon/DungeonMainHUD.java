package ralseiii.skyfabric.hud.dungeon;


import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import ralseiii.skyfabric.config.Config;
import ralseiii.skyfabric.utils.PlayerUtils;
import ralseiii.skyfabric.utils.SbChecks;

public class DungeonMainHUD {
        public static void registerHUD() {
                HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> {
                        Config.ModConfig config = AutoConfig.getConfigHolder(Config.ModConfig.class).getConfig();
                        if (SbChecks.isCatacombs && config.dungeonHud) {
                                MinecraftClient client = MinecraftClient.getInstance();
                                int xpLevel = PlayerUtils.getExpLevel();
                                String ultimateCountdownText;
                                matrixStack.push();
                                matrixStack.scale(1.0f, 1.0f, 1.0f);
                                // draw the bg
                                client.getTextureManager().bindTexture(new Identifier("skyfabric:textures/ui/bg.png"));
                                DrawableHelper.drawTexture(matrixStack, 0, 0, 1, 1, 64, 64, 64, 64);
                                matrixStack.pop();
                                matrixStack.push();
                                // ultimate ability countdown
                                client.getTextureManager().bindTexture(new Identifier("minecraft:textures/mob_effect/strength.png"));
                                DrawableHelper.drawTexture(matrixStack, 0, 0, 1, 1, 16, 16, 16, 16);
                                if (xpLevel == 0) ultimateCountdownText = "Ready!";
                                else ultimateCountdownText = String.valueOf(xpLevel);
                                client.textRenderer.drawWithShadow(matrixStack, ultimateCountdownText, 0, 18, 0xffffff);
                                matrixStack.pop();
                                matrixStack.push();
                        }
                }));
        }
}