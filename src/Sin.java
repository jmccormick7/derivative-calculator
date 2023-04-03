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
     * Overriding the derivative method so that it returns the derivative of a sinusoidal function
     * @return the function that is the derivative of the sin function
     */
    public Function derivative() {
        if(getOperand() instanceof Number) {
            return new Number(0);
        }
        return new BinaryOp(getOperand().derivative(), BinaryOp.Op.MULT, new Cos(getOperand())).simplify();
    }

    /**
     * Overriding the value(input) function so that it gives the value of the sin function at a given x value
     * @param input a double at which to evaluate the function
     * @return the value of the function.
     */
    public double value(double input) {
        return Math.sin(getOperand().value(input));
    }

    /**
     * Overriding the value() function so that it gives the value of a sinusoidal expression
     * @return the value of the expression
     * @throws UnsupportedOperationException when there is a variable in the function calling value()
     */
    public double value() throws UnsupportedOperationException {
        return Math.sin(getOperand().value());
    }

    /**
     * Overriding the simplify method to simplify the operand of the Sin function
     * @return simplified sin function.
     */
    public Function simplify(){
        return new Sin(getOperand().simplify());
    }

    /**
     * Overriding the toString() method to output the string representation of the Sinusoidal function
     * @return string representation of sinusoidal function ("Sin[operand]")
     */
    @Override
    public String toString() {
        return "Sin[" + getOperand().toString() + "]";
    }

    /**
     * Overriding the equals method to return true if the operands are the same
     * @param o Object that is being compared to the Sine function
     * @return a boolean representing whether they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof Sin &&
                this.getOperand().equals(((Sin) o).getOperand()));
    }
}
