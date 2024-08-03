import java.util.List;

public class Game {
    private QuestionPool questionPool;
    private List<Question> currentQuestions;
    private int currentQuestionIndex;
    private int correctAnswersCount;

    
    public Game() {
        questionPool = new QuestionPool();
        restartGame();
    }

    //restarts the game
    public void restartGame() {
        currentQuestions = questionPool.getQuestions();
        currentQuestionIndex = 0;
        correctAnswersCount = 0;
    }

    //returns the current question
    public Question getCurrentQuestion() {
        return currentQuestions.get(currentQuestionIndex);
    }

    //gets user's answer and checks whether it's true or false, also serves as counter for score
    public boolean checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(getCurrentQuestion().getCorrectAnswer())) {
            correctAnswersCount++;
            return true;
        }
        return false;
    }

    //returns counter of correct answers
    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    //checks if there are more questions
    public boolean hasNextQuestion() {
        return currentQuestionIndex < currentQuestions.size() - 1;
    }

    //controls the questions index
    public void moveToNextQuestion() {
        if (hasNextQuestion()) {
            currentQuestionIndex++;
        }
    }

    //returns the user final grade
    public double calculateGrade() {
        return ((double) correctAnswersCount / currentQuestions.size()) * 100;
    }
}
