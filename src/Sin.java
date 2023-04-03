/**
 * Sin is a class that represents Sinusoidal Functions
 * @author John McCormick
 */
public class Sin extends Function {

    /**
     * Field storing the operand of the sin function.
     */
    private final Function operand;

    /**
     * Getter method for the operand of the Sin function
     * @return the Operand function
     */
    public Function getOperand() {
        return operand;
    }

    /**
     * The constructor for the Sin class
     * @param operand the operand of the sinusoidal function
     */
    public Sin(Function operand) {
        this.operand = operand;
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
        return Math.sin(getOperand().value(input));
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
     * Overriding the toString() method to output the string representation of the Sinusoidal function
     * @return string representation of sinusoidal function ("Sin[operand]")
     */
    @Override
    public String toString() {
        return "Sin[" + getOperand().toString() + "]";
    }


}
