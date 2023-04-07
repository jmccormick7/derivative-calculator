import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FunctionGraph extends Canvas {

    private Function function;
    private double startX;
    private double endX;

    public FunctionGraph(Function function, double startX, double endX, double width, double height) {
        super(width, height);
        this.function = function;
        this.startX = startX;
        this.endX = endX;
        draw();
    }

    public void draw() {
        Canvas canvas = new Canvas();
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