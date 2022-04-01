package ralseiii.skyfabric.config;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@me.shedaniel.autoconfig.annotation.Config(name = "skyfabric")
public class ModConfig implements ConfigData {
    Boolean Enabled = true;

    @ConfigEntry.Gui.CollapsibleObject
    public Config.SolverConfigDropdown solverConfigDropdown = new Config.SolverConfigDropdown();
    @ConfigEntry.Gui.CollapsibleObject
    public Config.DungeonConfigDropdown dungeonConfigDropdown = new Config.DungeonConfigDropdown();
    @ConfigEntry.Gui.CollapsibleObject
    public Config.ItemInfo itemInfo = new Config.ItemInfo();
}
