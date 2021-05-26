package ralseiii.skyfabric.solvers.dungeon.chat;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

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
