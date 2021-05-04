package ralseiii.skyfabric.solvers.dungeon.chat;

public class ThreeWeirdos {
    public static Boolean threeWeirdosSolver(String message) {
        return  message.contains("The reward is not in my chest!") ||
                message.contains("The reward isn't in any of our chests.") ||
                message.contains("My chest doesn't have the reward. We are all telling the truth.") ||
                message.contains("My chest has the reward and I'm telling the truth!") ||
                message.contains("At least one of them is lying, and the reward is not in") ||
                message.contains("Both of them are telling the truth. Also, ");
    }
}
