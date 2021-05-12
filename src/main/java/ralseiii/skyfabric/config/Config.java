package ralseiii.skyfabric.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class Config {
    public static ModConfig config;

    @me.shedaniel.autoconfig.annotation.Config(name = "skyfabric")
    public static class ModConfig implements ConfigData {
        Boolean Enabled = true;

        @ConfigEntry.Gui.CollapsibleObject
        public SolverConfigDropdown solverConfigDropdown;

        public Boolean dungeonHud = true;
        public Boolean dungeonMap = true;

        class DungeonSolverConfigDropdown {
            Boolean Trivia = true;
            Boolean Three_Weirdos = true;
            Boolean HigherSolver = true;
        }

        class DwarvenSolverConfigDropdown {
            Boolean Fetchur = true;
        }

        class SolverConfigDropdown {
            @ConfigEntry.Gui.CollapsibleObject
            public DungeonSolverConfigDropdown dungeonSolverConfigDropdown;
            @ConfigEntry.Gui.CollapsibleObject
            public DwarvenSolverConfigDropdown dwarvenSolverConfigDropdown;
        }
    }

}
