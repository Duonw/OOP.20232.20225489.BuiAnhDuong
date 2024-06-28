package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton pen;

    @FXML
    private RadioButton eraser;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Color paintColor = Color.BLACK;

        if (this.eraser.isSelected()) {
            paintColor = Color.WHITE;
        }
        Circle newCircle = new Circle(event.getX(),
                event.getY(), 4, paintColor);
        drawingAreaPane.getChildren().add(newCircle);}

    @FXML
    void eraserSelected(ActionEvent event) {
        this.eraser.setSelected(true);
    }

    @FXML
    void penSelected(ActionEvent event) {
        this.pen.setSelected(true);
    }
}
