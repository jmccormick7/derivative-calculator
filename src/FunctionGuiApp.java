import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Separator;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Orientation;


public class FunctionGuiApp extends Application {
    private TextField inputField;
    private TextField valueField;
    private Button valueEnterButton;
    private Label functionLabel;
    private Label valueLabel;
    private Label derivativeLabel;
    private Canvas functionCanvas;
    private Button enterButton;
    private Function function;
    private Label derivativeValLabel;
    private Label functiontitlelabel;
    private Label derivativetitlelabel;
    private Canvas derivativeCanvas;
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
        functiontitlelabel = new Label();
        derivativetitlelabel = new Label();

        functionCanvas = new Canvas(400, 400);
        derivativeCanvas = new Canvas(400, 400);

        enterButton = new Button("Enter Function");
        enterButton.setOnAction(e -> {
            try {
                String input = inputField.getText();
                function = FunctionCreator.createFunction(input);

                functionLabel.setText("Function: " + function.toString());
                valueLabel.setText("Value at: ");
                derivativeLabel.setText("Derivative: " + function.derivative().toString());
                derivativetitlelabel.setText("Graph of derivative: " + function.derivative().toString());
                functiontitlelabel.setText("Graph of function: " + function.toString());

                draw(function, functionCanvas);
                draw(function.derivative(), derivativeCanvas);
            } catch (Exception ex) {
                functionLabel.setText("Error: " + ex.getMessage());
                valueLabel.setText("");
                derivativeLabel.setText("");
                ex.printStackTrace();
            }
        });
        valueField = new TextField();
        derivativeValLabel = new Label("");

        valueEnterButton = new Button("Enter Value for X");
        valueEnterButton.setOnAction (e -> {
            try {
                String input = valueField.getText();
                double inputVal = Double.parseDouble(input);
                double outputVal = function.value(inputVal);
                double derVal = function.derivative().value(inputVal);
                valueLabel.setText("Value at " + inputVal + ": " + outputVal);
                derivativeValLabel.setText("Value of Derivative at " + inputVal + ": " + derVal);
            } catch (Exception ex) {
                valueLabel.setText("Error: inputted value was not a number");
                ex.printStackTrace();
            }
        });

        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setMinWidth(100);
        Separator separator2 = new Separator(Orientation.VERTICAL);
        separator2.setMinWidth(100);
        Separator separator3 = new Separator(Orientation.VERTICAL);
        separator3.setMinWidth(100);
        Separator separator4 = new Separator(Orientation.VERTICAL);
        separator4.setMinWidth(10);
        Separator separator5 = new Separator(Orientation.HORIZONTAL);
        separator5.setMinHeight(10);


        VBox functionGraphs = new VBox();
        VBox derivativeGraphs = new VBox();
        functionGraphs.getChildren().addAll(functiontitlelabel, functionCanvas);
        derivativeGraphs.getChildren().addAll(derivativetitlelabel, derivativeCanvas);

        VBox topGadgets = new VBox();
        topGadgets.getChildren().addAll(inputField, enterButton, functionLabel, valueField, valueEnterButton, derivativeLabel, valueLabel,
                derivativeValLabel);

        HBox above = new HBox();
        above.getChildren().addAll(separator4, topGadgets);


        HBox canvasArea = new HBox();
        canvasArea.getChildren().addAll(separator2, functionGraphs, separator, derivativeGraphs, separator3);

        root.getChildren().addAll(separator5, above, canvasArea);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Function and Derivative Viewer");
        stage.show();
    }

    public void draw(Function function, Canvas canvas) {
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
            double xStep = xRange / (canvas.getWidth() * 10); // Increase the resolution by a factor of 10

            double x = xMin;
            double y = function.value(x);

            // Loop through the x values, and draw the function only for valid values
            for (x = xMin + xStep; x <= xMax; x += xStep) {
                double newY = function.value(x);
                if (!Double.isNaN(newY) && !Double.isInfinite(newY)) {
                    double xPixel = (x - xMin) / xRange * canvas.getWidth();
                    double yPixel = canvas.getHeight() - (y - yMin) / yRange * canvas.getHeight();

                    double newXPixel = (x - xMin) / xRange * canvas.getWidth();
                    double newYPixel = canvas.getHeight() - (newY - yMin) / yRange * canvas.getHeight();

                    // Draw the line segment only if both endpoints have valid y values
                    if (!Double.isNaN(y) && !Double.isInfinite(y)) {
                        gc = canvas.getGraphicsContext2D();
                        gc.setStroke(Color.BLUE);
                        gc.setLineWidth(1);
                        gc.strokeLine(xPixel, yPixel, newXPixel, newYPixel);
                    }
                    y = newY;
                } else {
                    y = newY;
                }
            }
        }
    }
}