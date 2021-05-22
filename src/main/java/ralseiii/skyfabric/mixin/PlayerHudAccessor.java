package ralseiii.skyfabric.mixin;

import com.google.common.collect.Ordering;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PlayerListHud.class)
public interface PlayerHudAccessor {
    @Accessor
    Ordering<PlayerListEntry> getENTRY_ORDERING();
    @Invoker("getPlayerName")
    public Text invokeGetPlayerName(PlayerListEntry playerListEntry);
}
