import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class mmn11q2Controller {

    @FXML
    private Button btn;

    @FXML
    private Canvas canv;

    private static final int CELL_SIZE = 10;
    private static final double FILL_PERCENTAGE = 0.1;
    private final int MATRIX_SIZE = 20;

    @FXML
    public void initialize() {


    }

    @FXML
    void btnPressed(ActionEvent event) {
        GraphicsContext gc = canv.getGraphicsContext2D();
		double cnvsWidth = canv.getWidth();
		double cnvsHeight = canv.getHeight();
		gc.clearRect(0, 0, cnvsWidth, cnvsHeight);
        gc.setStroke(Color.BLACK);
        
        double offsetX = (canv.getWidth() - (MATRIX_SIZE * (CELL_SIZE + 1))) / 2;
        double offsetY = (canv.getHeight() - (MATRIX_SIZE * (CELL_SIZE + 1))) / 2;

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                double x = offsetX + j * (CELL_SIZE + 1); // add offsetX for centering
                double y = offsetY + i * (CELL_SIZE + 1); // add offsetY for centering
                gc.strokeRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
        
        int totalCells = MATRIX_SIZE * MATRIX_SIZE;
        int cellsToFill = (int) (totalCells * FILL_PERCENTAGE);

        Random random = new Random();

        for (int i = 0; i < cellsToFill; i++) {
            int x = random.nextInt(MATRIX_SIZE);
            int y = random.nextInt(MATRIX_SIZE);
            double cellX = offsetX + x * (CELL_SIZE + 1);
            double cellY = offsetY + y * (CELL_SIZE + 1);
            gc.setFill(Color.BLACK);
            gc.fillRect(cellX, cellY, CELL_SIZE, CELL_SIZE);
        }

    }

}
