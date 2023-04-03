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
     * Overriding the derivative function so that it returns the derivative of the Natural exponential function
     * @return the derivative function
     */
    public Function derivative() {
        if (this.getOperand() instanceof Number) {
            return new Number(0);
        }
        else {
            return new BinaryOp(getOperand().derivative(), BinaryOp.Op.MULT, this).simplify();
        }
    }

    /**
     * Overriding the value(input) method to return the value of the exponential function at a given x value
     * @param input a double at which to evaluate the function
     * @return the value of the function at the x componenet
     */
    @Override
    public double value(double input) {
        return Math.pow(Math.E, getOperand().value(input));
    }

    /**
     * Overriding the value() method to return the value of the exponential expression
     * @return the value of the exponential expression
     * @throws UnsupportedOperationException if there is a variable in the function
     */
    @Override
    public double value() throws UnsupportedOperationException {
        return Math.pow(Math.E, getOperand().value());
    }

    /**
     * Overriding the simplify function to simplify the operand of the exponential
     * @return the simplified Exponential function
     */
    @Override
    public Function simplify(){
        return new Exp(getOperand().simplify());
    }

    /**
     * Overriding the toString method to output the string representation of the natural exponential function
     * @return the string representation of the natural exponential function ("Exp[operand]")
     */
    @Override
    public String toString() {
        return "Exp[" + getOperand().toString() + "]";
    }

    /**
     * Overriding the equals method to check the parameters of the Exp functions.
     * @param o the object being compared to
     * @return a boolean representing their equality
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof Exp &&
                this.getOperand().equals(((Exp) o).getOperand()));
    }
}
