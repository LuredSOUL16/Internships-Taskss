import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Question {
    private String text;              
    private List<String> options;     
    private int correctAnswerIndex;   

    public Question(String text, List<String> options, int correctAnswerIndex) {
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public void display() {
        System.out.println("\n" + text);
        for (int i = 0; i <= 3; i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public boolean checkAnswer(int userChoice) {
        return (userChoice - 1) == correctAnswerIndex;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "Which language is used for Android development?",
                Arrays.asList("Python", "Kotlin", "C#", "Ruby"),
                1
        ));

        questions.add(new Question(
                "Which company developed Java?",
                Arrays.asList("Microsoft", "Sun Microsystems", "Google", "Oracle"),
                1
        ));

        questions.add(new Question(
                "Which collection class is used to store key-value pairs?",
                Arrays.asList("ArrayList", "HashMap", "HashSet", "LinkedList"),
                1
        ));

        questions.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                Arrays.asList("extends", "implements", "inherit", "super"),
                0
        ));

        questions.add(new Question(
                "Which method is the entry point in Java?",
                Arrays.asList("start()", "run()", "main()", "execute()"),
                2
        ));

        Collections.shuffle(questions);

               int score = 0;

        for (Question q : questions) {
            q.display();
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();

            if (q.checkAnswer(userChoice)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong!");
            }
        }

        System.out.println("\nQuiz Over ");
        System.out.println("Your score is : " + score + "/" + 5);
    }
}
