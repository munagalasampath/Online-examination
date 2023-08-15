import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Other user-related methods
}

class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    // Getters and setters
    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

}

class OnlineExaminationSystem {
    private User currentUser;
    private int[] selectedAnswers;
    private int remainingTime;

    public OnlineExaminationSystem(User currentUser, Question[] questions, int examDuration) {
        this.currentUser = currentUser;
        this.selectedAnswers = new int[questions.length];
        this.remainingTime = examDuration;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void login(String username, String password) {
        if (currentUser == null) {
            if (username.equals("testuser") && password.equals("password")) {
                currentUser = new User(username, password);
            }
        }
    }

    public void selectAnswer(int questionIndex, int selectedOption) {
        selectedAnswers[questionIndex] = selectedOption;
    }

    public void startTimer() {
        System.out.println("Timer started for " + remainingTime + " seconds.");        
    }

    public void closeSession() {
    }
}

public class javaclass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User user = new User("testuser", "password");
        Question[] questions = {
            new Question("What is 2 + 3?", new String[]{"3", "4", "5", "6"}, 1),
            new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Venus", "Jupiter"}, 1)
        };

        OnlineExaminationSystem examSystem = new OnlineExaminationSystem(user, questions, 60);

        System.out.println("Welcome to the Online Examination System!");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        examSystem.login(username, password);

        if (examSystem.getCurrentUser() != null) {
            System.out.println("Login successful!");
            examSystem.startTimer();

            for (int i = 0; i < questions.length; i++) {
                System.out.println("Question " + (i + 1) + ": " + questions[i].getQuestionText());
                String[] options = questions[i].getOptions();
                for (int j = 0; j < options.length; j++) {
                    System.out.println((j + 1) + ". " + options[j]);
                }

                System.out.print("Enter your answer (1-" + options.length + "): ");
                int selectedOption = scanner.nextInt();
                examSystem.selectAnswer(i, selectedOption - 1);
            }

            examSystem.closeSession();

            System.out.println("Exam submitted successfully.");
            System.out.println("Logout from the system.");
        } else {
            System.out.println("Login failed. Exit...");
        }

        scanner.close();
    }
}
