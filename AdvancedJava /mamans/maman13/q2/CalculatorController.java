import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CalculatorController {


    @FXML
    private Text savedNumbers;

    @FXML
    private TextField textField;
    
    private String firstNum = "";
    private String currentNum = "";
    private String calcType;
    
    
    public void calcSetup(String calcType) {
    	this.calcType = calcType;
    	firstNum = currentNum;
    	currentNum = "";
    	savedNumbers.setText(firstNum + " " + calcType);
    	
    }
    
    public void addNumber(String num) {
    	currentNum += num;
    	updateTextField();
    	
    }
    
    public void updateTextField() {
    	textField.setText(currentNum);
    }

        @FXML
        void btn0Pressed(ActionEvent event) {

            if(!currentNum.equals("")){
                addNumber("0");
            }
        }

        @FXML
        void btn1Pressed(ActionEvent event) {
        	addNumber("1");

        }

        @FXML
        void btn2Pressed(ActionEvent event) {
        	addNumber("2");

        }

        @FXML
        void btn3Pressed(ActionEvent event) {
        	addNumber("3");

        }

        @FXML
        void btn4Pressed(ActionEvent event) {
        	addNumber("4");

        }

        @FXML
        void btn5Pressed(ActionEvent event) {
        	addNumber("5");

        }

        @FXML
        void btn6Pressed(ActionEvent event) {
        	addNumber("6");

        }

        @FXML
        void btn7Pressed(ActionEvent event) {
        	addNumber("7");

        }

        @FXML
        void btn8Pressed(ActionEvent event) {
        	addNumber("8");

        }

        @FXML
        void btn9Pressed(ActionEvent event) {
        	addNumber("9");

        }

        @FXML
        void calculatePressed(ActionEvent event) {
            double firstNumDub = Double.parseDouble(firstNum);
            double secondNumDub = Double.parseDouble(currentNum);

            switch (calcType) {
                case "+" -> {
                    double calculatedNumber = firstNumDub + secondNumDub;
                    savedNumbers.setText(firstNum + " + " + currentNum + " = " + calculatedNumber);
                    textField.setText(String.valueOf(calculatedNumber));
                }
                case "-" -> {
                    double calculatedNumber = firstNumDub - secondNumDub;
                    savedNumbers.setText(firstNum + " - " + currentNum + " = " + calculatedNumber);
                    textField.setText(String.valueOf(calculatedNumber));
                }
                case "/" -> {
                    double calculatedNumber = firstNumDub / secondNumDub;
                    savedNumbers.setText(firstNum + " / " + currentNum + " = " + calculatedNumber);
                    textField.setText(String.valueOf(calculatedNumber));
                }
                case "*" -> {
                    double calculatedNumber = firstNumDub * secondNumDub;
                    savedNumbers.setText(firstNum + " * " + currentNum + " = " + calculatedNumber);
                    textField.setText(String.valueOf(calculatedNumber));
                }
            }

        }

        @FXML
        void dblPressed(ActionEvent event) {
        	if(currentNum.length() == 0){
        		addNumber("0.");
        	}
        	
        	else if(!currentNum.contains(".")) {
        		addNumber(".");
        	}
        }

        @FXML
        void dividePressed(ActionEvent event) {
            calcSetup("/");


        }
        
        @FXML
        void addPressed(ActionEvent event) {

            calcSetup("+");

        }

        @FXML
        void minusPressed(ActionEvent event) {

            calcSetup("-");

        }

        @FXML
        void multiPressed(ActionEvent event) {
            calcSetup("*");


        }
        
        @FXML
        void clearPressed(ActionEvent event) {
            currentNum = "";
            textField.setText("");
            savedNumbers.setText("");
        }
        
        @FXML
        void changeSignPressed(ActionEvent event) {
            String num = textField.getText();
            double newNum = Double.valueOf(num);
            textField.setText(String.valueOf(-newNum));
            

        	
        }
    }
