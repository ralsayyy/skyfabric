package ralseiii.skyfabric.mixin;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ralseiii.skyfabric.overlay.DungeonChestProfitOverlay;
import ralseiii.skyfabric.utils.SbAreas;
import ralseiii.skyfabric.utils.SbChecks;

@Mixin(GenericContainerScreen.class)
public abstract class GenericContainerScreenMixin extends HandledScreen<GenericContainerScreenHandler> {
    @Shadow
    @Final
    private int rows;
    @Final
    protected Text playerInventoryTitle;



    public GenericContainerScreenMixin(GenericContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "drawBackground", at = @At("TAIL"))
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        // if (!SbChecks.isSkyblock) return;
        var ts = title.getString();
        if ((SbChecks.currentArea == SbAreas.DUNGEON || SbChecks.currentArea == SbAreas.DUNGEON_HUB) && (
                ts.contains("Wood") ||
                        ts.contains("Gold") ||
                        ts.contains("Diamond") ||
                        ts.contains("Emerald") ||
                        ts.contains("Obsidian") ||
                        ts.contains("Bedrock")
                )) {
            DungeonChestProfitOverlay.render(matrices, title.getString(), handler.getInventory(), rows);
        }
    }
}
