// Abstrakt klass som representerar en fråga i frågesporten
public abstract class AbstractQuestion {
    protected String questionText;
    protected String[] options;
    protected int correctAnswerIndex;

    // Konstruktor
    public AbstractQuestion(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Abstrakt metod för att visa frågan
    public abstract void displayQuestion();

    // Kontrollerar om svaret är korrekt
    public boolean isCorrect(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}

// Klass som ärver från AbstractQuestion och implementerar en vanlig fråga
public class Question extends AbstractQuestion {

    // Konstruktor som tar frågetext, alternativ och rätt svar
    public Question(String questionText, String[] options, int correctAnswerIndex) {
        super(questionText, options, correctAnswerIndex);
    }

    // Implementerar displayQuestion för att visa frågan och dess alternativ
    @Override
    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Huvudklassen som representerar själva spelet
public class QuizGame {
    private List<AbstractQuestion> questions; // Lista med alla frågor
    private int score; // Spelarens poäng

    // Konstruktor som initierar frågelistan och spelarens poäng
    public QuizGame() {
        questions = new ArrayList<>();
        score = 0;
    }

    // Lägger till frågor i frågelistan
    public void addQuestions() {
        questions.add(new Question("Vilken är huvudstaden i Sverige?", new String[]{"Stockholm", "Göteborg", "Malmö"}, 0));
        questions.add(new Question("Hur många ben har en spindel?", new String[]{"6", "8", "10"}, 1));
        questions.add(new Question("Vilket årtionde skickades människan till månen första gången?", new String[]{"1950-talet", "1960-talet", "1970-talet"}, 1));
        questions.add(new Question("Vad heter grundaren av Microsoft?", new String[]{"Steve Jobs", "Bill Gates", "Elon Musk"}, 1));
        questions.add(new Question("Vilken planet är känd som den röda planeten?", new String[]{"Mars", "Venus", "Jupiter"}, 0));
    }

    // Startar själva frågesporten
    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (AbstractQuestion question : questions) {
            question.displayQuestion();
            System.out.print("Skriv in ditt svar (1-3): ");
            int answer = scanner.nextInt() - 1; // Justera för index (0-baserad)

            if (question.isCorrect(answer)) {
                System.out.println("Rätt svar!\n");
                score++;
            } else {
                System.out.println("Fel svar. Rätt svar var: " + (question.correctAnswerIndex + 1) + " - " + question.options[question.correctAnswerIndex] + "\n");
            }
        }
        displayScore();
    }

    // Visar spelarens slutpoäng
    public void displayScore() {
        System.out.println("Spelet är slut! Din slutpoäng är: " + score + " av " + questions.size());
    }

    // Main-metoden som startar spelet
    public static void main(String[] args) {
        QuizGame quizGame = new QuizGame();
        quizGame.addQuestions();
        quizGame.startQuiz();
    }
}
