/**
 * Operand is a class that contains number and variable and allows for usage of variables and numbers
 * @author John McCormick
 */
public abstract class Operand extends Function {

    /**
     * The value of the operand if it has one
     */
    private Double operandValue;

    /**
     * Constructor for operand
     * @param operandValue the double value of operand (null when variable)
     */
    public Operand(Double operandValue) {
        this.operandValue = operandValue;
    }

    /**
     * Getter method for the operand value
     * @return operand value as Double type, so variables will return null
     */
    public Double getOperandValue() {
        return operandValue;
    }


    /**
     * Setter method for operand Value
     * @param operandValue the value that the variable is being replaced with
     */
    protected void setOperandValue(Double operandValue) {
        this.operandValue = operandValue;
    }

    /**
     *
     * @return
     */
    public double value() {
        if (getOperandValue() == null)
            throw new UnsupportedOperationException();
        else
            return getOperandValue();
    }

    /**
     *
     * @param input
     * @return
     */
    public double value(double input) {
        Double originalVal = getOperandValue();
        if (getOperandValue() == null) {
            setOperandValue(input);
        }
        double returnVal = getOperandValue();
        setOperandValue(originalVal);
        return returnVal;
    }


    /**
     * Overriding the equals method to compare the operand values, as variables will always be null
     * and numbers will have the same Double value.
     * @param o
     * @return a boolean that is true if they are equal, and false if not
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Operand))
            return false;
        Operand operand = (Operand) o;
        return this.getOperandValue().equals(operand.getOperandValue());
    }

}
