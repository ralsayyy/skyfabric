package ralseiii.skyfabric.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.network.message.MessageSender;
import net.minecraft.network.message.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ralseiii.skyfabric.solvers.dungeon.chat.ThreeWeirdos;
import ralseiii.skyfabric.solvers.dungeon.chat.triviaQuiz;
import ralseiii.skyfabric.solvers.dwarven.PuzzlerSolver;
import ralseiii.skyfabric.utils.SbAreas;
import ralseiii.skyfabric.utils.SbChecks;
import ralseiii.skyfabric.solvers.dwarven.FetchurSolver;

import java.util.UUID;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

@Mixin(ChatHudListener.class)
public class ChatHudListenerMixin {
    @Shadow @Final private MinecraftClient client;
    public Boolean nextQuestion = false;

    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    public void onMessage(MessageType type, Text message, MessageSender sender, CallbackInfo ci) {
        if (SbChecks.isSkyblock) {
            var msg = message.getString();
            // puzzler and fetchur
            // TODO: Implement a puzzler solver
            if (msg.contains("[NPC]")) {
                if (msg.contains("Fetchur")) {
                    var fetchurItemText = FetchurSolver.fetchurSolver(msg);
                    if (!fetchurItemText.getString().equals("")) {
                        client.player.sendMessage(message, false);
                        client.player.sendMessage(Text.of("§8[Skyfabric]§r: Fetchur wants §a[" + fetchurItemText.getString() + "]§r"), false);
                        ci.cancel();
                    }
                } else if (msg.contains("Puzzler") && !msg.contains("Come") && !msg.contains("Nice!")) {
                    client.player.sendMessage(message, false);
                    PuzzlerSolver.solve(msg);
                    ci.cancel();
                } else if (msg.contains("Puzzler") && msg.contains("Nice!")) {
                    client.player.sendMessage(message, false);
                    PuzzlerSolver.renderOverlay = false;
                    ci.cancel();
                }
            }
            if (SbChecks.currentArea == SbAreas.DUNGEON) {
                // three weirdos
                if (msg.contains("[NPC]")) {
                    var hasReward = ThreeWeirdos.threeWeirdosSolver(msg);
                    if (hasReward) {
                        var rewardChestName = msg;
                        client.player.sendMessage(message, false);
                        rewardChestName = rewardChestName.substring(rewardChestName.indexOf("]") + 2);
                        rewardChestName = rewardChestName.substring(0, rewardChestName.indexOf(":"));
                        client.player.sendMessage(Text.of("§8[Skyfabric]§r: " + rewardChestName + " has the reward."), false);
                        ci.cancel();
                    }
                }
                if (msg.contains("wasn't fooled by") || msg.contains("was fooled by")) {
                    ThreeWeirdos.renderOverlay = false;
                }
                // Trivia question
                if (msg.contains("Question #") && !msg.contains(":")) {
                    nextQuestion = true;
                    client.player.sendMessage(message, false);
                    ci.cancel();
                }

                if (nextQuestion && !msg.contains("Question #")) {
                    triviaQuiz.question = msg.trim();
                    nextQuestion = false;
                    client.player.sendMessage(message, false);
                    ci.cancel();
                }
                // trivia answers
                if (msg.contains("ⓐ")&& !msg.contains("thinks")) {
                    triviaQuiz.answerA = msg.substring(msg.indexOf("ⓐ"));
                    client.player.sendMessage(message, false);
                    ci.cancel();
                }

                if (msg.contains("ⓑ") && !msg.contains("thinks")) {
                    triviaQuiz.answerB = msg.substring(msg.indexOf("ⓑ"));
                    client.player.sendMessage(message, false);
                    ci.cancel();
                }

                if (msg.contains("ⓒ") && !msg.contains("thinks")) {
                    triviaQuiz.answerC = msg.substring(msg.indexOf("ⓒ"));
                    client.player.sendMessage(message, false);
                    client.player.sendMessage(Text.of("§8[Skyfabric]§r: The correct answer is " + triviaQuiz.triviaQuizSolver() + "."), false);
                    ci.cancel();
                }

            }
        }
    }
}
