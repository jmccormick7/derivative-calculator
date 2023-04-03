/**
 * Cos is a class that represents Cosine functions
 * @author John McCormick
 */
public class Cos extends Function{

    /**
     * Field to store the operand of the cosine function
     */
    private final Function operand;

    /**
     * Constructor for the Cos class.
     * @param operand the operand of the cosine function
     */
    public Cos(Function operand) {
        this.operand = operand;
    }

    /**
     * Getter method for the operand of the cosine function
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

    /**
     * Overriding the toString function to output the string representation of the cosine function
     * @return string representation of the cosine function ("Cos[operand]")
     */
    @Override
    public String toString() {
        return "Cos[" + getOperand().toString() + "]";
    }
}

