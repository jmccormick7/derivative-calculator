/**
 * BinaryOp is a class that allows for the use of operators in a function
 * It is used for the four basic operators(addition, subtraction, multiplication, and division)
 * @author John McCormick
 */
public class BinaryOp extends Function{

    /**
     * Enum Op, represents the four binary operations (addition, subtraction, multiplication, and division)
     * It also contains operation specific methods for value, derivative, and toString
     * Called as PLUS, SUB, MULT, DIV
     */
    public enum Op {
        PLUS {
            @Override
            public double value(Function left, Function right) throws UnsupportedOperationException {
                return left.value() + right.value();
            }

            @Override
            public double value(Function left, Function right, double input) {
                return left.value(input) + right.value(input);
            }

            @Override
            public Function derivative(Function left, Function right){
                return new BinaryOp(left.derivative(), PLUS, right.derivative());
            }

            @Override
            public String toString() {
                return "+";
            }
        },
        SUB {
            @Override
            public double value(Function left, Function right) throws UnsupportedOperationException {
                return left.value() - right.value();
            }

            @Override
            public double value(Function left, Function right, double input) {
                return left.value(input) - right.value(input);
            }

            @Override
            public Function derivative(Function left, Function right) {
                return new BinaryOp(left.derivative(), SUB, right.derivative());
            }

            @Override
            public String toString() {
                return "-";
            }
        },
        MULT {
            @Override
            public double value(Function left, Function right) throws UnsupportedOperationException{
                return left.value() * right.value();
            }

            @Override
            public double value(Function left, Function right, double input) {
                return left.value(input) * right.value(input);
            }

            @Override
            public Function derivative(Function left, Function right) {
                Function first = new BinaryOp(left, MULT, right.derivative());
                Function second = new BinaryOp(left.derivative(), MULT, right);
                return new BinaryOp(first, PLUS, second);
            }

            @Override
            public String toString() {
                return "*";
            }
        },
        DIV {
            @Override
            public double value(Function left, Function right) throws UnsupportedOperationException{
                return left.value() / right.value();
            }

            @Override
            public double value(Function left, Function right, double input) {
                return left.value(input) / right.value(input);
            }

            @Override
            public Function derivative(Function left, Function right){
                Function firstNum = new BinaryOp(right, MULT, left.derivative());
                Function secondNum = new BinaryOp(left, MULT, right.derivative());
                Function numerator = new BinaryOp(firstNum, SUB, secondNum);
                Function denominator = new BinaryOp(right, MULT, right);
                return  new BinaryOp(numerator, DIV, denominator);
            }

            @Override
            public String toString() {
                return "/";
            }
        };

        /**
         * Method that gets the value for the enum used(with no double input)
         * @param left left Operand of the expression
         * @param right right Operand of the expression
         * @return the value of the function
         * @throws UnsupportedOperationException when a variable is in either of the functions
         */
        public abstract double value(Function left, Function right) throws UnsupportedOperationException;

        /**
         * Method that gets the value for the enum used(when a value is inputted)
         * @param left left Operand of the expression
         * @param right right Operand of the expression
         * @param input the value being subsituted for the variables
         * @return the value of the expression
         */
        public abstract double value(Function left, Function right, double input);

        /**
         * Method that returns the derivative for the enum used
         * @param left left Operand of the expression
         * @param right right Operand of the expression
         * @return the derivative of the expression leftOperand enum rightOperand
         */
        public abstract Function derivative(Function left, Function right);
    }

    /**
     * Field that holds the left Operand of the operation
     */
    private final Function leftOperand;

    /**
     * This field holds the operator of the binary operation
     */
    private final Op operator;

    /**
     * Field that holds the right Operand of the operation
     */
    private final Function rightOperand;

    /**
     * Constructor for the BinaryOp Class
     * @param leftOperand The function on the left side of the operator
     * @param operator The operator (PLUS, SUB, MULT, DIV)
     * @param rightOperand The function on the right side of hte operator
     */
    public BinaryOp(Function leftOperand, Op operator, Function rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    /**
     * Overriding the derivative method to return the derivative of the operation
     * @return a new Function that is the derivative of this function
     */
    public Function derivative() {
        // simplify the two operands
        Function simpleLeft = getLeftOperand().simplify();
        Function simpleRight = getRightOperand().simplify();
        return getOperator().derivative(simpleLeft,simpleRight);
    }

    /**
     * Overriding the value(input) method to evaluate for BinaryOps
     * @param input a double at which to evaluate the function
     * @return the value of the operation with input replacing all the variables
     */
    @Override
    public double value(double input) {
        return getOperator().value(getLeftOperand(),getRightOperand(),input);
    }

    /**
     * Overriding the value() function for BinaryOp
     * @return the value of the binaryOp with no input(for expressions of just number values)
     * @throws UnsupportedOperationException when a function with a variable has this method called on it
     */
    @Override
    public double value() throws UnsupportedOperationException{
        return getOperator().value(getLeftOperand(),getRightOperand());
    }

    /**
     * Getter method for the left operand of the operator function
     * @return the left operand of the operation
     */
    public Function getLeftOperand() {
        return leftOperand;
    }

    /**
     * Getter method for the operator of the operator function
     * @return the operator of the operation
     */
    public Op getOperator() {
        return operator;
    }

    /**
     * Getter method for the Right operand of the operation function
     * @return the right operand of the operation function
     */
    public Function getRightOperand() {
        return rightOperand;
    }

    /**
     * Overriding toString() to show the string representation of the function.
     * @return string representation of the function
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        // if the left operand is a BinaryOp enclose them in parentheses
        if (getLeftOperand() instanceof BinaryOp)
            result.append("(" + getLeftOperand().toString() + ") ");
        // if the left operand is not a binaryOp then we will not enclose in parentheses
        else
            result.append(getLeftOperand().toString() + " ");
        // add the operator
        result.append(getOperator().toString() + " ");
        // if the right operand is a binary op and the operator of the right operand differs from the operator of this
        // binary op then put in parentheses
        if (getRightOperand() instanceof BinaryOp && ((BinaryOp) getRightOperand()).getOperator() != getOperator())
            result.append("(" + getRightOperand().toString() +")");
        // otherwise just append the right operand
        else
            result.append(getRightOperand().toString());
        // return the resulting string
        return result.toString();
    }

    /**
     * Overriding the .equals method so that it only returns true if all operands and operator are the same
     * @param o Object being compared to the BinaryOp .equals() is called on.
     * @return a boolean representing the equality of the BinaryOp and object.
     */
    @Override
    public boolean equals(Object o){
        return (o instanceof BinaryOp &&
                    this.getLeftOperand().equals(((BinaryOp) o).getLeftOperand()) &&
                    this.getOperator() == ((BinaryOp) o).getOperator() &&
                    this.getRightOperand().equals(((BinaryOp) o).getRightOperand()));


    }

    @Override
    public Function simplify() {
        Function leftSimplified = getLeftOperand().simplify();
        Function rightSimplified = getRightOperand().simplify();

        // Handle specific simplification cases, e.g., addition or multiplication by zero
        if (getOperator() == Op.PLUS || getOperator() == Op.SUB) {
            // addition or subtraction of 0 with 0 as the first value
            if ((leftSimplified instanceof Number) && (leftSimplified.value() == 0)) {
                if (getOperator() == Op.PLUS) {
                    return rightSimplified;
                }
                else if (getOperator() == Op.SUB) {
                    return new BinaryOp(new Number(-1), Op.MULT, rightSimplified);
                }
            }
            // addition and subtraction of 0 with 0 as the second operand
            else if ((rightSimplified instanceof Number) && (rightSimplified.value() == 0)) {
                return leftSimplified;
            }
            // algebraic simplification
            if (leftSimplified instanceof Number && rightSimplified instanceof Number) {
                BinaryOp operation = new BinaryOp(leftSimplified, getOperator(), rightSimplified);
                return new Number(operation.value());
            }
        }
        else if (getOperator() == Op.MULT) {
            // multiplication of 1
            if (leftSimplified instanceof Number && leftSimplified.value() == 1) {
                return rightSimplified;
            }
            // multiplication of 1
            else if (rightSimplified instanceof Number && rightSimplified.value() == 1) {
                return leftSimplified;
            }
            // multiplication of 0
            if (rightSimplified instanceof Number && rightSimplified.value() == 0 ||
                    leftSimplified instanceof Number && leftSimplified.value() == 0) {
                return new Number(0);
            }
            // distribution of constant onto a coefficient
            if (leftSimplified instanceof Number && rightSimplified instanceof BinaryOp && ((BinaryOp) rightSimplified).getLeftOperand() instanceof Number) {
                Number num = new Number(leftSimplified.value() * ((BinaryOp) rightSimplified).getLeftOperand().value());
                return new BinaryOp(num, ((BinaryOp) rightSimplified).getOperator(), ((BinaryOp) rightSimplified).getRightOperand());
            }
            // algebraic simplification
            if (leftSimplified instanceof Number && rightSimplified instanceof Number)
                return new Number(leftSimplified.value() * rightSimplified.value());
        }
        else if (getOperator() == Op.DIV) {
            // division by 1
            if (rightSimplified instanceof Number && rightSimplified.value() == 1) {
                return leftSimplified;
            }
            // algebraic simplification
            if (leftSimplified instanceof Number && rightSimplified instanceof Number) {
                return new Number(leftSimplified.value() / rightSimplified.value()).simplify();
            }
        }

        // If no specific simplification rule applies, return the original expression with simplified operands
        return new BinaryOp(leftSimplified, getOperator(), rightSimplified);
    }

}
