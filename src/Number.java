/**
 * Number is a class that represents any number that is a part of a function
 * It can represent both coefficients and constants
 * @author John McCormick
 */
public class Number extends Operand {
    /**
     *
     * @param value
     */
    public Number(double value) {
        super(value);
    }

    /**
     *
     * @return
     */
    public Operand derivative() {
        return new Number(0);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.getOperandValue().toString();
    }
}
