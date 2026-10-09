import java.util.ArrayList;
import java.util.List;

class Student {
    private final String name;

    Student(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

abstract class Question {
    protected String text;
    protected String correctAnswer;
    protected int marks;

    Question(String text, String correctAnswer, int marks) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.marks = marks;
    }

    abstract boolean checkAnswer(String answer);

    int getMarks() {
        return marks;
    }

    String getText() {
        return text;
    }
}

class MCQQuestion extends Question {
    private final String[] options;

    MCQQuestion(String text, String[] options,
                String correctAnswer, int marks) {
        super(text, correctAnswer, marks);
        this.options = options;
    }

    void displayOptions() {
        for (String option : options) {
            System.out.println(option);
        }
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class TrueFalseQuestion extends Question {
    TrueFalseQuestion(String text, String correctAnswer, int marks) {
        super(text, correctAnswer, marks);
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class ShortAnswerQuestion extends Question {
    ShortAnswerQuestion(String text, String correctAnswer, int marks) {
        super(text, correctAnswer, marks);
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class Attempt {
    private final Student student;
    private final List<Question> questions;
    private final List<String> answers = new ArrayList<>();
    private boolean submitted = false;

    Attempt(Student student, List<Question> questions) {
        this.student = student;
        this.questions = new ArrayList<>(questions);
    }

    void answerQuestion(int index, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers after submission.");
            return;
        }

        if (index < 0 || index >= questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }

        while (answers.size() < questions.size()) {
            answers.add("");
        }

        answers.set(index, answer);
        System.out.println("Answer recorded for question " + (index + 1));
    }

    void submit() {
        if (submitted) {
            System.out.println("Exam already submitted.");
            return;
        }

        submitted = true;
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            String answer = i < answers.size() ? answers.get(i) : "";

            if (questions.get(i).checkAnswer(answer)) {
                score += questions.get(i).getMarks();
            }
        }

        System.out.println("\nStudent: " + student.getName());
        System.out.println("Exam submitted successfully.");
        System.out.println("Score: " + score + " / "
                + totalMarks());
    }

    private int totalMarks() {
        int total = 0;
        for (Question question : questions) {
            total += question.getMarks();
        }
        return total;
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("Varun");

        List<Question> questions = new ArrayList<>();

        questions.add(new MCQQuestion(
                "Which language is object-oriented?",
                new String[]{"A. HTML", "B. Java", "C. CSS"},
                "B", 2));

        questions.add(new TrueFalseQuestion(
                "Java supports inheritance.", "true", 2));

        questions.add(new ShortAnswerQuestion(
                "Which keyword creates an object in Java?",
                "new", 3));

        Attempt attempt = new Attempt(student, questions);

        System.out.println("ONLINE EXAMINATION");

        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". "
                    + questions.get(i).getText());

            if (questions.get(i) instanceof MCQQuestion) {
                ((MCQQuestion) questions.get(i)).displayOptions();
            }
        }

        attempt.answerQuestion(0, "B");
        attempt.answerQuestion(1, "true");
        attempt.answerQuestion(2, "new");

        attempt.submit();

        // Answers cannot be changed after submission.
        attempt.answerQuestion(0, "A");
    }
}
