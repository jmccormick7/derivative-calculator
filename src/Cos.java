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
     * Overriding the derivative function to give the derivative of a cosine function
     * @return the derivative function
     */
    public Function derivative() {
        if(getOperand() instanceof Number) {
            return new Number(0);
        }
        BinaryOp secondHalf = new BinaryOp(new Number(-1), BinaryOp.Op.MULT, new Sin(getOperand()));
        return new BinaryOp(getOperand().derivative(), BinaryOp.Op.MULT, secondHalf).simplify();
    }

    /**
     * Overriding the value(input) function so that it gives the value of the cos function at a given x value
     * @param input a double at which to evaluate the function
     * @return the value at the inputted x val.
     */
    public double value(double input) {
        return Math.cos(getOperand().value(input));
    }

    /**
     * Overriding the value() function so that it gives the value of the cosine expression
     * @return the value of the cosine exception
     * @throws UnsupportedOperationException when there is a variable in the function calling value()
     */
    public double value() throws UnsupportedOperationException {
        return Math.cos(getOperand().value());
    }

    /**
     * Overriding the simplify method so that it simplifies the operand of the sine function.
     * @return the simplified cosine function
     */
    public Function simplify(){
        return new Cos(getOperand().simplify());
    }

    /**
     * Overriding the toString function to output the string representation of the cosine function
     * @return string representation of the cosine function ("Cos[operand]")
     */
    @Override
    public String toString() {
        return "Cos[" + getOperand().toString() + "]";
    }

    /**
     * Overriding the equals method to return true if the operands are the same
     * @param o Object that is being compared to the Cos function
     * @return a boolean representing whether they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof Cos &&
                this.getOperand().equals(((Cos) o).getOperand()));

    }
}

