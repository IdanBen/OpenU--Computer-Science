import java.util.ArrayList;
import java.util.Collections;

public class Question {
	private String question;
	private ArrayList<String> answers;
	private String correctAnswer;
	
	public Question(String question, String correctAnswer, String answer2, String answer3, String answer4) {
		this.question = question;
		this.correctAnswer = correctAnswer; //by default the correct answer is the first one
		this.answers = new ArrayList<String>();
		this.answers.add(correctAnswer);
		this.answers.add(answer2);
		this.answers.add(answer3);
		this.answers.add(answer4);
		Collections.shuffle(answers);
		
	}
	//getter for question
	public String getQuestion() {
		return question;
	}
	
	//getter for the correct answer
    public String getCorrectAnswer() {
    	return correctAnswer;
    }
	
    //getter for possible answers
    public ArrayList<String> getAnswers() {
        return answers;
    }
	

}
	
