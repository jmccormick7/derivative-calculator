/**
 * Log is a class that represents Natural Logarithmic functions
 * @author John McCormick
 */
public class Log extends Function {

    /**
     * Field that holds the operand of the Natural Logarithmic function
     */
    private final Function operand;

    /**
     * Constructor for the Log class
     * @param operand operand of the Natural Logarithmic funciton.
     */
    public Log(Function operand) {
        this.operand = operand;
    }

    /**
     * Getter method for the operand of the Natural Logarithmic function
     * @return the operand function
     */
    public Function getOperand() {
        return operand;
    }

    /**
     * Overriding the derivative method to find the derivative of the natural log function
     * @return the derivative function simplified
     */
    @Override
    public Function derivative() {
        if (getOperand() instanceof Number)
            return new Number(0);
        else {
            Function mainDeriv = new BinaryOp(new Number(1), BinaryOp.Op.DIV, getOperand());
            return new BinaryOp(getOperand().derivative(), BinaryOp.Op.MULT, mainDeriv).simplify();
        }
    }

    /**
     * Overriding the value(input) method to evaluate the Log function at a given x value
     * @param input a double at which to evaluate the function
     * @return the value of the natural log function at a given x
     */
    @Override
    public double value(double input) {
        return Math.log(getOperand().value(input));
    }

    /**
     * Overriding the value() function to evaluate the log expression (with no variables)
     * @return the value of the Natural Log expression
     * @throws UnsupportedOperationException when there is a variable in the function calling value()
     */
    @Override
    public double value() throws UnsupportedOperationException{
        return Math.log(getOperand().value());
    }

    /**
     * Overriding the simplify method to simplify the operand of the Log function
     * @return the simplified Log function
     */
    @Override
    public Function simplify(){
        return new Log(getOperand().simplify());
    }

    /**
     * Overriding the toString method to give the string representation of the Natural log function
     * @return the string representation of the natural log function ("Log[operand]")
     */
    @Override
    public String toString() {
        return "Log[" + getOperand().toString() + "]";
    }

    /**
     * Overriding the equals method so that the functions have to have the same parameters to be equal
     * @param o the object that is being compared to this
     * @return a boolean representing the equality of the Log and the given object parameter
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof Log &&
                this.getOperand().equals(((Log) o).getOperand()));
    }
}

