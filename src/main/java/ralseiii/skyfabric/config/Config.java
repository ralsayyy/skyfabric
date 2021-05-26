package ralseiii.skyfabric.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class Config {
    public static ModConfig config;

    @me.shedaniel.autoconfig.annotation.Config(name = "skyfabric")
    public static class ModConfig implements ConfigData {
        public Boolean Enabled = true;

        @ConfigEntry.Gui.CollapsibleObject
        public SolverConfigDropdown solverConfigDropdown;
        //@ConfigEntry.Gui.CollapsibleObject
        //public DungeonConfigDropdown dungeonConfigDropdown;

        //public Boolean dungeonHud = true;
        //public Boolean dungeonMap = true;

        public class DungeonSolverConfigDropdown {
            public Boolean Trivia = true;
            public Boolean Three_Weirdos = true;
            public Boolean HigherSolver = true;
        }

        public class UiElementPosition {
            public int x;
            public int y;
        }

        public class DwarvenSolverConfigDropdown {
            public Boolean Fetchur = true;
        }

        public class DungeonHudConfigDropdown {
            public Boolean Enabled;
            @ConfigEntry.Gui.CollapsibleObject
            public UiElementPosition uiElementPosition;
        }

        public class DungeonMapConfigDropdown {
            public Boolean Enabled;
            @ConfigEntry.Gui.CollapsibleObject
            public UiElementPosition uiElementPosition;

        }

        public class DungeonConfigDropdown {
            @ConfigEntry.Gui.CollapsibleObject
            public DungeonHudConfigDropdown dungeonHudConfigDropdown;
            @ConfigEntry.Gui.CollapsibleObject
            public DungeonMapConfigDropdown dungeonMapConfigDropdown;
        }



        public class SolverConfigDropdown {
            @ConfigEntry.Gui.CollapsibleObject
            public DungeonSolverConfigDropdown dungeonSolverConfigDropdown;
            @ConfigEntry.Gui.CollapsibleObject
            public DwarvenSolverConfigDropdown dwarvenSolverConfigDropdown;
        }
    }

}
