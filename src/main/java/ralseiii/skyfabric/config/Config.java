package ralseiii.skyfabric.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

public class Config {
    public static class DungeonSolverConfigDropdown {
        public Boolean Trivia = true;
        public Boolean Three_Weirdos = true;
        public Boolean HigherSolver = true;
    }

    public static class UiElementPosition {
        public int x = 0;
        public int y = 0;
    }

    public static class DwarvenSolverConfigDropdown {
        public Boolean Fetchur = true;
    }

    public static class DungeonHudConfigDropdown {
        public Boolean Enabled;
        @ConfigEntry.Gui.CollapsibleObject
        public UiElementPosition uiElementPosition = new UiElementPosition();
    }

    public static class DungeonMapConfigDropdown {
        public Boolean Enabled = true;
        @ConfigEntry.Gui.CollapsibleObject
        public UiElementPosition uiElementPosition = new UiElementPosition();

    }

    public static class DungeonConfigDropdown {
        //@ConfigEntry.Gui.CollapsibleObject
        //public DungeonHudConfigDropdown dungeonHudConfigDropdown = new DungeonHudConfigDropdown();
        @ConfigEntry.Gui.CollapsibleObject
        public DungeonMapConfigDropdown dungeonMapConfigDropdown = new DungeonMapConfigDropdown();
    }


    public static class SolverConfigDropdown {
        @ConfigEntry.Gui.CollapsibleObject
        public DungeonSolverConfigDropdown dungeonSolverConfigDropdown = new DungeonSolverConfigDropdown();
        @ConfigEntry.Gui.CollapsibleObject
        public DwarvenSolverConfigDropdown dwarvenSolverConfigDropdown = new DwarvenSolverConfigDropdown();
    }

    public static class ItemInfo {
        public Boolean bazaarPrice = true;
        public Boolean lowestBin = true;
        public Boolean sbItemId = true;
    }
}
