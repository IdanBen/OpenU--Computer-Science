import java.util.ArrayList;

public class QuestionPool  {
	
	private ArrayList<Question> questions;
	
	public QuestionPool() {
		questions = new ArrayList<>();
        Question q1 = new Question("What is the appropriate data for the field: isWinner ?", "boolean", "double", "string", "int");
        Question q2 = new Question("Which method can be used to find the length of a string?", "length()",
        		"getSize()", "getLength()", "len()");
        Question q3 = new Question("Which method can be used to return a string in upper case letters?",
        		"toUpperCase()", "touppercase()", "upperCase()","upIt()");
        Question q4 = new Question("Which operator can be used to compare two values?", "==", "=", "<>", "><");
        Question q5 = new Question("Which operator is used to multiply numbers?", "*", "x", "#","$");
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
	}
	
	//getter for questions
    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
