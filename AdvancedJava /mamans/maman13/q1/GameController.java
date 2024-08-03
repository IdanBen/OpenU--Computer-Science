import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class GameController {

    @FXML
    private Button btnSubmit;
    
    @FXML
    private Button btnNext;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private RadioButton option3;

    @FXML
    private RadioButton option4;

    @FXML
    private ToggleGroup optionsGroup;

    @FXML
    private TextField questionText;
    
    private Game game;
    
    //initializes the game
    @FXML
    public void initialize() {
        game = new Game();
        showNextQuestion();
        btnSubmit.setDisable(false);

    }


    //submits the user's answer and displays whether it was right or wrong
    @FXML
    void submitPressed(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) optionsGroup.getSelectedToggle();
        String selectedAnswer = selectedRadioButton.getText();
        
        if (game.checkAnswer(selectedAnswer)) {
            questionText.setText("Correct!");
        } else {
            questionText.setText("Incorrect!");
        }
        disableAnsBtns();
        btnSubmit.setDisable(true);
    }
    
    //loads the next question if there is one, in case there isn't shows the score
    @FXML
    void nextPressed(ActionEvent event) {
    	enableAnsBtns();
    	btnSubmit.setDisable(false);
    	if(game.hasNextQuestion()) {
    		game.moveToNextQuestion();
    		showNextQuestion();
    	}
    	else {
    		btnSubmit.setDisable(true);
    		double grade = game.calculateGrade();
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Score");
    		alert.setContentText("Game Over! Your grade: " +grade+ "%");
    		alert.setHeaderText("Score");
    		alert.showAndWait();
            game.restartGame();
            initialize();
    	}
    }
    
    //loads the question to the screen
    private void showNextQuestion() {
        Question currentQuestion = game.getCurrentQuestion();
        questionText.setText(currentQuestion.getQuestion());
        option1.setText(currentQuestion.getAnswers().get(0));
        option2.setText(currentQuestion.getAnswers().get(1));
        option3.setText(currentQuestion.getAnswers().get(2));
        option4.setText(currentQuestion.getAnswers().get(3));
    }
    
    //disables the answer buttons
    private void disableAnsBtns() {
        option1.setDisable(true);
        option2.setDisable(true);
        option3.setDisable(true);
        option4.setDisable(true);
    }
    
    //enables the answer buttons
    private void enableAnsBtns() {
        option1.setDisable(false);
        option2.setDisable(false);
        option3.setDisable(false);
        option4.setDisable(false);
    }

}
