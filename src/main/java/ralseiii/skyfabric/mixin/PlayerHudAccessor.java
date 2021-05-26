package ralseiii.skyfabric.mixin;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

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
