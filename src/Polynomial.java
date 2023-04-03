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

    public static void main(String[] args) {
        Number three = new Number(3);
        Number two = new Number(2);
        Variable ex = new Variable();
        Polynomial xtofourth = new Polynomial(ex, 4);
        BinaryOp threex = new BinaryOp(three, BinaryOp.Op.MULT, xtofourth);
        Polynomial xsquared = new Polynomial(ex, 2);
        BinaryOp twoxsquared = new BinaryOp(two, BinaryOp.Op.MULT,xsquared);
        BinaryOp polyBase = new BinaryOp(threex, BinaryOp.Op.SUB, twoxsquared);
        Polynomial fx = new Polynomial(polyBase, 8);
        System.out.println(fx.derivative().value(1));
        System.out.println(fx.derivative().simplify().toString());
        System.out.println(fx.derivative().derivative().simplify().toString());
        System.out.println(fx.derivative().derivative().value(1));

        Variable x = new Variable();
        Number four = new Number(4);
        Number eight = new Number(8);
        Number twelve = new Number(12);

        Polynomial xTo4 = new Polynomial(x, 4);
        Polynomial xTo2 = new Polynomial(x, 2);
        Polynomial xTo3 = new Polynomial(x, 3);

        BinaryOp term1 = new BinaryOp(three, BinaryOp.Op.MULT, xTo4);
        BinaryOp term2 = new BinaryOp(two, BinaryOp.Op.MULT, xTo2);
        BinaryOp term3 = new BinaryOp(twelve, BinaryOp.Op.MULT, xTo3);
        BinaryOp term4 = new BinaryOp(four, BinaryOp.Op.MULT, x);

        BinaryOp diff = new BinaryOp(term1, BinaryOp.Op.SUB, term2);
        Polynomial diffTo7 = new Polynomial(diff, 7);

        BinaryOp product1 = new BinaryOp(eight, BinaryOp.Op.MULT, diffTo7);

        BinaryOp diff2 = new BinaryOp(term3, BinaryOp.Op.SUB, term4);

        BinaryOp finalFunction = new BinaryOp(product1, BinaryOp.Op.MULT, diff2);

        System.out.println("Function: " + finalFunction.toString());
        System.out.println("Function value at x=1: " + finalFunction.value(1));
        System.out.println("Second Derivative: "+ finalFunction.derivative().simplify().toString());
        System.out.println("Second Derivative value at x = 1: " + finalFunction.derivative().simplify().value(1));
        System.out.println("Second Derivative value at x = 1: " + finalFunction.derivative().value(1));
        System.out.println("Single polynomial" + new Polynomial(new Variable(), 4).derivative().simplify().derivative().simplify().toString());


//        Number thirtytwo = new Number(32);
//        BinaryOp thirtytwox = new BinaryOp(thirtytwo, BinaryOp.Op.MULT, ex);
//        BinaryOp threexsquared = new BinaryOp(three, BinaryOp.Op.MULT, xsquared);
//        Number one = new Number(1);
//        BinaryOp middleterm = new BinaryOp(threexsquared, BinaryOp.Op.SUB, one);
//        Polynomial finalterm = new Polynomial(threex, 7);
//        BinaryOp combination1 = new BinaryOp(middleterm, BinaryOp.Op.MULT, finalterm);
//        BinaryOp finalfunction = new BinaryOp(thirtytwox, BinaryOp.Op.MULT, combination1);
//        System.out.println(finalfunction.derivative().toString());
//        System.out.println(finalfunction.value(1));
//        System.out.println(finalfunction.derivative().value(1));
    }
}
