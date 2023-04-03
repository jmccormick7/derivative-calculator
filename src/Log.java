/**
 * Log is a class that represents Logarithmic functions
 * @author John McCormick
 */
public class Log extends Function {

    /**
     * Field that holds the operand of the Logarithmic function
     */
    private final Function operand;

    /**
     * Constructor for the Log class
     * @param operand operand of the Logarithmic funciton.
     */
    public Log(Function operand) {
        this.operand = operand;
    }

    /**
     * Getter method for the operand of the Logarithmic function
     * @return the operand function
     */
    public Function getOperand() {
        return operand;
    }

    /**
     *
     * @return
     */
    public Function derivative() {
        return null;
    }

    /**
     *
     * @param input a double at which to evaluate the function
     * @return
     */
    public double value(double input) {
        return 0;
    }

    /**
     *
     * @return
     */
    public double value() {
        return 0;
    }

    public Function simplify(){
        return new Variable();
    }
}

