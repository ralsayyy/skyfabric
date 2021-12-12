package ralseiii.skyfabric.hud;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;
import ralseiii.skyfabric.config.ModConfig;
import ralseiii.skyfabric.utils.Position;
import ralseiii.skyfabric.utils.SbChecks;

public class CrystalHollowsMap {
    public static Identifier identifier = new Identifier("skyfabric:textures/ui/ch.png");
    public static void register() {
        HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> {
            if (SbChecks.isCrystalHollows) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client != null && client.player != null && client.options != null && !client.options.debugEnabled && AutoConfig.getConfigHolder(ModConfig.class).getConfig().dungeonConfigDropdown.dungeonMapConfigDropdown.Enabled) {
                    matrixStack.push();
                    matrixStack.scale(1f, 1f, 1f);
                    client.getTextureManager().bindTexture(identifier);
                    DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, 125, 125, 125, 125);
                    matrixStack.pop();
                    matrixStack.push();
                    matrixStack.scale(1f, 1f, 1f);
                    client.getTextureManager().bindTexture(new Identifier("skyfabric:textures/ui/arrow.png"));
                    Position pos = new Position();
                    pos.x = client.player.getX();
                    pos.y = client.player.getY();
                    pos.z = client.player.getZ();
                    DrawableHelper.drawTexture(matrixStack, (int) Math.round(Math.max(0, Math.min(pos.x - 200, 624)) / 4.992), (int) Math.round(Math.max(0, Math.min(pos.z - 204, 624)) / 4.992), 0, 0, 8, 8, 8, 8);
                    matrixStack.pop();
                }
            }
            }));
    }
}
