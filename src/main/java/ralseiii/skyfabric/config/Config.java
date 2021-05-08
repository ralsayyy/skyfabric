package ralseiii.skyfabric.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class Config {
    @me.shedaniel.autoconfig.annotation.Config(name = "skyfabric")
    public static class ModConfig implements ConfigData {
        Boolean Enabled = true;

        @ConfigEntry.Gui.CollapsibleObject
        SolverConfigDropdown solverConfigDropdown;

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
            DungeonSolverConfigDropdown dungeonSolverConfigDropdown;
            @ConfigEntry.Gui.CollapsibleObject
            DwarvenSolverConfigDropdown dwarvenSolverConfigDropdown;
        }
    }

}
