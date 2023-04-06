/**
 * Number is a class that represents any number that is a part of a function
 * It can represent both coefficients and constants
 * @author John McCormick
 */
public class Number extends Function {

    /**
     * The value of the number
     */
    private final double numberValue;

    /**
     * Constructor for Number, it creates a new number, with numberValue of the number.
     * @param value a double with the value of the number
     */
    public Number(double value) {
        this.numberValue = value;
    }

    /**
     * Getter method for the number value
     * @return number value
     */
    public double getNumberValue() {
        return numberValue;
    }


    /**
     * This method calculates the derivative of a number. A derivative of a solo constant is always 0
     * @return a new Number with a value of 0.
     */
    public Function derivative() {
        return new Number(0);
    }

    /**
     * Method overrides the value() method to return the value of the number
     * @return value of the number as a double
     */
    @Override
    public double value() {
        return getNumberValue();
    }

    /**
     * Method that overrides the value(input) method to return the value of the number
     * @param input a double at which to evaluate the function
     * @return value of the number as a double
     */
    @Override
    public double value(double input) {
        return getNumberValue();
    }

    /**
     * Overriding the toString() method so that it returns the operand value in string form.
     * @return string form of the operand value.
     */
    @Override
    public String toString() {
        // uses valueOf() from the Java API
        return String.valueOf(getNumberValue());
    }

    /**
     * Override of equals() method to only return true if the object is also a number with the same number value
     * @param o object that is being compared to the number the method is called on
     * @return boolean is true if they are both numbers with equal values, and false if not.
     */
    @Override
    public boolean equals(Object o) {
        return (!(o instanceof Variable) && o instanceof Function && (this.value() == ((Function) o).value()));
    }

    /**
     * Overriding the simplify method for number
     * @return this because no simplification is required for number
     */
    @Override
    public Function simplify(){
        return this;
    }
}
