/**
 * Polynomial is a class that allows for exponential usage
 * It allows for function portions, variables, or numbers to be raised to a power
 * @author John McCormick
 */
public class Polynomial extends Function {

    /**
     * Field that holds the operand of the polynomial function
     */
    private final Function operand;

    /**
     * Field that holds the power that the operand is raised to in the polynomial function
     */
    private final double power;

    /**
     * Constructor for the polynomial function
     * @param operand Function that is the base of the polynomial
     * @param power The exponent that the operand is raised to
     */
    public Polynomial(Function operand, double power){
        this.operand = operand;
        this.power = power;
    }

    /**
     * Getter function for the operand
     * @return the operand
     */
    public Function getOperand() {
        return operand;
    }

    /**
     * Getter function for the power
     * @return the exponent of the function
     */
    public double getPower() {
        return power;
    }

    /**
     * Overriding the Derivative Method to return the derivative of the polynomial function.
     * @return a function that is the derivative of the polynomial function.
     */
    @Override
    public Function derivative() {
        Function simplifiedOperand = getOperand().simplify();
        if (simplifiedOperand instanceof Number)
            return simplifiedOperand.derivative();
        Polynomial polyDeriv = new Polynomial(simplifiedOperand,getPower() - 1);
        Number coeff = new Number(getPower());
        if (!(simplifiedOperand instanceof Variable)) {
            BinaryOp currentDeriv = new BinaryOp(coeff, BinaryOp.Op.MULT, polyDeriv);
            return new BinaryOp(currentDeriv, BinaryOp.Op.MULT, simplifiedOperand.derivative()).simplify();
        }
        else
            return new BinaryOp(coeff, BinaryOp.Op.MULT, polyDeriv).simplify();
    }

    /**
     * Overriding the value(input) function to give the value of the polynomial function with an input
     * @param input a double at which to evaluate the function
     * @return the value of the function
     */
    @Override
    public double value(double input) {
        return Math.pow(getOperand().value(input),getPower());
    }

    /**
     * Overriding the value() function to give the value of the polynomial function if it has no variables
     * @return the value of the function, numerically
     * @throws UnsupportedOperationException when a function with a variable has this called on it
     */
    @Override
    public double value() throws UnsupportedOperationException {
        return Math.pow(getOperand().value(), getPower());
    }

    @Override
    public String toString() {
        if (getOperand() instanceof BinaryOp)
            return "(" + getOperand().toString() + ")^" + getPower();
        else
            return getOperand().toString() + "^" + getPower();
    }

    /**
     * Overriding the simplify function so that it simplifies the polynomial function
     * @return the simplified expression
     */
    @Override
    public Function simplify() {
        Function simplifiedOperand = getOperand().simplify();

        // If the power is 1, return the simplified operand
        if (getPower() == 1) {
            return simplifiedOperand;
        }
        if (getPower() == 0) {
            return new Number(1);
        }

        return new Polynomial(simplifiedOperand, getPower());
    }

    /**
     * Overriding the equals method to judge equality based on power and operand
     * @param o the object being compared to the Polynomial function
     * @return boolean representing their equality
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof Polynomial &&
                ((Polynomial) o).getPower() == this.getPower() &&
                ((Polynomial) o).getOperand().equals(this.getOperand()));
    }

}
