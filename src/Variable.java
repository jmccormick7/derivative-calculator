/**
 * Variable is a class that represents a variable,
 * All variables will be represented as 'x'
 * @author John McCormick
 */
public class Variable extends Function {

    /**
     * Constructor for Variable, takes no inputs and sets the operandValue in super to null.
     */
    public Variable() {
    }

    /**
     * This method does the derivative for the variable, always 1 for a first-order variable.
     * @return new Number with a value of 1.
     */
    @Override
    public Function derivative() {
        return new Number(1);
    }

    /**
     * Overrrides the value() method to throw an UnsupportedOperationException
     * @return UnsupportedOperationException
     */
    @Override
    public double value() {
        throw new UnsupportedOperationException();
    }

    /**
     * Overriding the value(double) method to return the inputted double
     * @param input a double at which to evaluate the function
     * @return inputted double as the value
     */
    @Override
    public double value(double input) {
        return input;
    }

    /**
     * Overriding the toString() method to always output x, as variables in this calculator will always be x
     * @return String "x"
     */
    @Override
    public String toString() {
        return "x";
    }

    /**
     * Overriding the equals method to return true if the object is also a Variable
     * @param o object that is being compared to the variable the method is called on
     * @return true if o is a Variable, and false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Variable)
            return true;
        return false;

    }
}
