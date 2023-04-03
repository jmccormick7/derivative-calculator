/**
 * Exp is a class that extends polynomial and represents exponential functions
 * @author John McCormick
 */

public class Exp extends Function{

    /**
     * Field that stores the operand of the Exponential function
     */
    private final Function operand;

    /**
     * Constructor of the Exp class
     * @param operand operand function
     */
    public Exp(Function operand) {
        this.operand = operand;
    }

    /**
     * Getter method for the operand of the function
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
     * @throws UnsupportedOperationException
     */
    public double value() throws UnsupportedOperationException {
        return 0;
    }

    /**
     *
     * @return
     */
    public Function simplify(){
        return new Variable();
    }
}
