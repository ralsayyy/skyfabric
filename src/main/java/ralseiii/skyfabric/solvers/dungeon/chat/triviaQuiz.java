package ralseiii.skyfabric.solvers.dungeon.chat;

class TriviaQuestion {
    public String question;
    public String[] correctAnswers;
    public TriviaQuestion(String _question, String[] _correctAnswers) {
        question = _question;
        correctAnswers = _correctAnswers;
    }
}

public class triviaQuiz {
    public static String question;

    public static String answerA;
    public static String answerB;
    public static String answerC;

    public static String triviaQuizSolver() {
        String correctAnswer = "(Unknown)";
        TriviaQuestion[] questions = {
                new TriviaQuestion("What is the status of The Watcher?", new String[]{"Stalker"}),
                new TriviaQuestion("What is the status of Bonzo?", new String[]{"New Necromancer"}),
                new TriviaQuestion("What is the status of Scarf?", new String[]{"Apprentice Necromancer"}),
                new TriviaQuestion("What is the status of The Professor?", new String[]{"Professor"}),
                new TriviaQuestion("What is the status of Thorn?", new String[]{"Shaman Necromancer"}),
                new TriviaQuestion("What is the status of Livid?", new String[]{"Master Necromancer"}),
                new TriviaQuestion("What is the status of Sadan?", new String[]{"Necromancer Lord"}),
                new TriviaQuestion("What is the status of Necron?", new String[]{"Wither Lord"}),
                new TriviaQuestion("How many total Fairy Souls are there?", new String[]{"227 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in Spider's Den?", new String[]{"19 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in The End?", new String[]{"12 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in The Farming Islands?", new String[]{"20 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in Blazing Fortress?", new String[]{"19 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in The Park?", new String[]{"11 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in Jerry's Workshop?", new String[]{"5 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in Hub?", new String[]{"79 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in The Hub?", new String[]{"79 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in Gold Mine?", new String[]{"12 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in Deep Caverns?", new String[]{"21 Fairy Souls"}),
                new TriviaQuestion("How many Fairy Souls are there in Dungeon Hub?", new String[]{"7 Fairy Souls"}),
                new TriviaQuestion("Which brother is on the Spider's Den?", new String[]{"Rick"}),
                new TriviaQuestion("What is the name of Rick's brother?", new String[]{"Pat"}),
                new TriviaQuestion("What is the name of the Painter in the Hub?", new String[]{"Marco"}),
                new TriviaQuestion("What is the name of the person that upgrades pets?", new String[]{"Kat"}),
                new TriviaQuestion("What is the name of the lady of the Nether?", new String[]{"Elle"}),
                new TriviaQuestion("Which villager in the Village gives you a Rogue Sword?", new String[]{"Jamie"}),
                new TriviaQuestion("How many unique minions are there?", new String[]{"53 Minions"}),
                new TriviaQuestion("Which of these enemies does not spawn in the Spider's Den?", new String[]{
                        "Zombie Spider",
                        "Cave Spider",
                        "Wither Skeleton",
                        "Dashing Spooder",
                        "Broodfather",
                        "Night Spider"
                }),
                new TriviaQuestion("Which of these monsters only spawns at night?", new String[]{
                        "Zombie Villager",
                        "Ghast"
                }),
                new TriviaQuestion("Which of these is not a dragon in The End?", new String[]{
                        "Zoomer Dragon",
                        "Weak Dragon",
                        "Stonk Dragon",
                        "Holy Dragon",
                        "Boomer Dragon",
                        "Booger Dragon",
                        "Older Dragon",
                        "Elder Dragon",
                        "Stable Dragon",
                        "Professor Dragon"
                }),
        };
        TriviaLoop:
        for (TriviaQuestion triviaQuestion : questions) {
            if (triviaQuestion.question.contains(question)) {
                for (int i2 = 0; i2 < triviaQuestion.correctAnswers.length; i2++) {
                    if (answerA.contains(triviaQuestion.correctAnswers[i2])) {
                        correctAnswer = "(A)";
                        break TriviaLoop;
                    }
                    if (answerB.contains(triviaQuestion.correctAnswers[i2])) {
                        correctAnswer = "(B)";
                        break TriviaLoop;
                    }
                    if (answerC.contains(triviaQuestion.correctAnswers[i2])) {
                        correctAnswer = "(C)";
                        break TriviaLoop;
                    }
                }
            }
        }
        return correctAnswer; 
    }
}
