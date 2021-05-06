package ralseiii.skyfabric.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ralseiii.skyfabric.solvers.dungeon.chat.ThreeWeirdos;
import ralseiii.skyfabric.solvers.dungeon.chat.triviaQuiz;
import ralseiii.skyfabric.utils.SbChecks;
import ralseiii.skyfabric.solvers.dwarven.FetchurSolver;

import java.util.List;
import java.util.UUID;

@Mixin(ChatHudListener.class)
public class ChatHudListenerMixin {
    @Shadow @Final private MinecraftClient client;
    public Boolean nextQuestion = false;

    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    public void onMessage(MessageType messageType, Text message, UUID senderUuid, CallbackInfo ci) {
        if (SbChecks.isSkyblock) {
            String msg = message.getString();
            // puzzler and fetchur
            // TODO: Implement a puzzler solver
            if (!SbChecks.isCatacombs && msg.contains("[NPC]")) {
                if (msg.contains("Fetchur")) {
                    MinecraftClient minecraftClient = MinecraftClient.getInstance();
                    String fetchurItem = FetchurSolver.fetchurSolver(msg);
                    if (!fetchurItem.equals("")) {
                        minecraftClient.player.sendMessage(message, false);
                        minecraftClient.player.sendMessage(Text.of("§8[Skyfabric]§r: Fetchur wants §a[" + fetchurItem + "]§r"), false);
                        ci.cancel();
                    }
                }
            }
            if (SbChecks.isCatacombs) {
                // three weirdos
                if (msg.contains("[NPC]")) {
                    MinecraftClient minecraftClient = MinecraftClient.getInstance();
                    minecraftClient.player.sendMessage(message, false);
                    Boolean hasReward = ThreeWeirdos.threeWeirdosSolver(msg);
                    if (hasReward) {
                        String rewardChestName = msg;
                        rewardChestName = rewardChestName.substring(rewardChestName.indexOf("]") + 2);
                        rewardChestName = rewardChestName.substring(0, rewardChestName.indexOf(":"));
                        minecraftClient.player.sendMessage(Text.of("§8[Skyfabric]§r: " + rewardChestName + " has the reward."), false);
                    }
                    ci.cancel();
                }
                // Trivia question
                if (msg.contains("Question #") && !msg.contains(":")) {
                    nextQuestion = true;
                    client.player.sendMessage(Text.of(msg), false);
                    ci.cancel();
                }

                if (nextQuestion && !msg.contains("Question #")) {
                    triviaQuiz.question = msg;
                    nextQuestion = false;
                    client.player.sendMessage(Text.of(msg), false);
                    ci.cancel();
                }
                // trivia answers
                if (msg.contains("ⓐ")) {
                    triviaQuiz.answerA = msg;
                    client.player.sendMessage(Text.of(msg), false);
                    ci.cancel();
                }

                if (msg.contains("ⓑ")) {
                    triviaQuiz.answerB = msg;
                    client.player.sendMessage(Text.of(msg), false);
                    ci.cancel();
                }

                if (msg.contains("ⓒ")) {
                    triviaQuiz.answerC = msg;
                    client.player.sendMessage(Text.of(msg), false);
                    client.player.sendMessage(Text.of("§8[Skyfabric]§r: The correct answer is " + triviaQuiz.triviaQuizSolver() + "."), false);
                    ci.cancel();
                }

            }
        }
    }
}
