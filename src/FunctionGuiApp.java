import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class FunctionGuiApp extends Application {
    private TextField inputField;
    private Label functionLabel;
    private Label valueLabel;
    private Label derivativeLabel;
    private Canvas canvas;
    private Button enterButton;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();

        inputField = new TextField();
        inputField.setPromptText("Enter function");

        functionLabel = new Label();
        valueLabel = new Label();
        derivativeLabel = new Label();

        canvas = new Canvas(400, 400);

        enterButton = new Button("Enter");
        enterButton.setOnAction(e -> {
            try {
                String input = inputField.getText();
                Function function = FunctionCreator.createFunction(input);

                functionLabel.setText("Function: " + function.toString());
                valueLabel.setText("Value at 1: " + function.value(1));
                derivativeLabel.setText("Derivative: " + function.derivative().toString());

                draw(function);
            } catch (Exception ex) {
                functionLabel.setText("Error: " + ex.getMessage());
                valueLabel.setText("");
                derivativeLabel.setText("");
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(inputField, functionLabel, valueLabel, derivativeLabel, canvas, enterButton);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Function and Derivative Viewer");
        stage.show();
    }

    public void draw(Function function) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2);
        gc.strokeLine(canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight());

        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.setLineDashes(5);
        gc.strokeLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2);
        gc.strokeLine(canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight());
        gc.setLineDashes(null);

        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);

        if (function != null) {
            double xMin = -10;
            double xMax = 10;
            double yMin = -10;
            double yMax = 10;
            double xRange = xMax - xMin;
            double yRange = yMax - yMin;
            double xStep = xRange / canvas.getWidth();

            double x = xMin;
            double y = function.value(x);

            double xPixel = (x - xMin) / xRange * canvas.getWidth();
            double yPixel = canvas.getHeight() - (y - yMin) / yRange * canvas.getHeight();

            gc.beginPath();
            gc.moveTo(xPixel, yPixel);

            for (x = xMin + xStep; x <= xMax; x += xStep) {
                y = function.value(x);
                xPixel = (x - xMin) / xRange * canvas.getWidth();
                yPixel = canvas.getHeight() - (y - yMin) / yRange * canvas.getHeight();
                gc.lineTo(xPixel, yPixel);
            }
            gc.stroke();
        }
    }
}