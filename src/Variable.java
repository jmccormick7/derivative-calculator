/**
 * Variable is a class that represents a variable,
 * All variables will be represented as 'x'
 * @author John McCormick
 */
public class Variable extends Operand {

    /**
     *
     */
    public Variable() {
        super(null);
    }

    /**
     *
     * @return
     */
    public Operand derivative() {
        return new Number(1);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "x";
    }
}
