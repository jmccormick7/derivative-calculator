import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test class for the Derivative Calculator
 * @author John McCormick
 */
public class Project3Tester {

    /**
     * Test for number and variable .equals() method
     */
    @Test
    public void testOperandEquals() {
        Number six = new Number(6);
        Number six2 = new Number(6);
        Variable x = new Variable();
        Variable y = new Variable();
        Number seven = new Number(7);
        String hi = "hi";
        // testing two numbers with equal values returns true
        assertTrue("Equal value number test failed.", six.equals(six2));
        // testing that two unequal numbers does indeed return false
        assertFalse("Unequal numbers incorrectly asserted as equal", six.equals(seven));
        // testing that number compared to variable does not return equal
        assertFalse("number incorrectly asserted to be equal to a variable", six.equals(x));
        // testing that variable compared to number does not return equal
        assertFalse("variable incorrectly asserted to be equal to a numebr", x.equals(six));
        // testing that two variables returns true
        assertTrue("Two variables failed to be recognized as equal", x.equals(y));
        // testing to make sure that number cannot be equal to a non operand
        assertFalse("number incorrectly asserted as equal to an object", six.equals(hi));
        // testing to make sure that variable cannot be asserted as equal to a non operand
        assertFalse("variable incorrectly asserted as equal to an object", x.equals(hi));
    }

    /**
     * Test for variable and number toString() method
     */
    @Test
    public void testOperandToString() {
        Number six = new Number(6);
        Variable x = new Variable();
        Variable y = new Variable();
        Number seven = new Number(7);
        Number nonWhole = new Number(3.14);
        // testing that a number using toString outputs the number in double form as a string
        assertEquals("number toString() failed", "7.0", seven.toString());
        // testing it with a different number
        assertEquals("different whole number test failed", "6.0", six.toString());
        // testing it with a number with a decimal portion
        assertEquals("non-Whole number toString test failed", "3.14", nonWhole.toString());
        // testing that any variable always outputs "x", using two variables
        assertEquals("variable toString test failed.", "x", x.toString());
        assertEquals("variable toString test failed.","x", y.toString());
    }

    /**
     * Test the number constructor, and the getter method for getNumberValue
     */
    @Test
    public void testNumberConstructor(){
        Number six = new Number(6);
        Number pi = new Number(3.14);
        // testing that six getOperandValue is 6
        assertTrue("NumberConstructor getOperandValue failed", 6.0 == six.getNumberValue());
        assertTrue("Non Whole Number getOperandValue failed", 3.14 == pi.getNumberValue());

    }

    /**
     * Test the number version of value,
     */
    @Test
    public void testNumberValue() {
        Number six = new Number(6);
        Number pi = new Number(3.14);
        // testing to see that on both a whole number and a real number value() works
        assertTrue("no input value failed on a whole number", 6.0 == six.value());
        assertTrue("no input value failed on non whole number", 3.14 == pi.value());
        // testing to ensure that value(input) does not change the value and that the operand value says the same
        assertTrue("input value failed on a whole number", 6.0 == six.value(25));
        assertTrue("input value failed on non whole number", 3.14 == pi.value(25));

    }

    /**
     * Test the variable version of value throws an UnsupportedOperationException when no input value is called
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testVariableExceptionValue() {
        Variable x = new Variable();
        x.value();
    }

    /**
     * Test the variable value returns the inputted value of the double input value method
     */
    @Test
    public void testVariableValue() {
        Variable x = new Variable();
        // testing a whole number input
        assertEquals("value with whole number input test failed on variable", 10, x.value(10), 0);
        // testing a non-whole number input
        assertEquals("value with float number input test failed on variable", 3.14, x.value(3.14), 0);
    }

    /**
     * Test the variable derivative returns 1.
     */
    @Test
    public void testVariableDerivative() {
        Variable x = new Variable();
        assertEquals("Derivative of a stand alone variable was not 1.", new Number(1), x.derivative());
    }

    /**
     * Test that the derivative of a number is 0
     */
    @Test
    public void testNumberDerivative() {
        Number pi = new Number(3.14);
        Number six = new Number(6);
        Number zero = new Number(0);
        assertEquals("The derivative of a stand alone integer is incorrect",zero, six.derivative() );
        assertEquals("The derivative of a stand alone decimal is incorrect", zero, pi.derivative());
    }

    //Testing the Enum Op

    /**
     * Testing the value method within BinaryOp (Summing)
     */
    @Test
    public void testBinaryOpPlusValue() {
        // testing for numbers only
        Number pi = new Number(3.14);
        Number six = new Number(6);
        assertEquals("Adding numbers failed", 9.14, new BinaryOp(pi, BinaryOp.Op.PLUS, six).value(),0);
        assertEquals("Numbers were assigned incorrect values", 9.14, new BinaryOp(pi, BinaryOp.Op.PLUS, six).value(8),0);
        // testing with variables
        Variable x = new Variable();
        assertEquals("Finding the value of a variable added to a number failed", 14.0, new BinaryOp(six, BinaryOp.Op.PLUS, x).value(8),0);
    }

    /**
     * Testing that the value() method throws an UnsupportedOperationException on variables
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testBinaryOpPlusValueException() {
        Variable x = new Variable();
        Number eight = new Number(8);
        BinaryOp test = new BinaryOp(eight, BinaryOp.Op.PLUS, x);
        double value = test.value();
    }
    /**
     * Testing the value method within BinaryOP (Subtraction)
     */
    @Test
    public void testBinaryOpSubValue() {
        // testing for numbers only
        Number pi = new Number(3.14);
        Number six = new Number(6);
        assertEquals("Subtracting numbers failed", 2.86, new BinaryOp(six, BinaryOp.Op.SUB, pi).value(),0);
        assertEquals("Numbers were assigned incorrect values", 2.86, new BinaryOp(six, BinaryOp.Op.SUB, pi).value(8),0);
        // testing with variables
        Variable x = new Variable();
        assertEquals("Finding the value of a variable added to a number failed", 4.0, new BinaryOp(six, BinaryOp.Op.SUB, x).value(2),0);
    }

    /**
     * Testing that the value() method throws a UnsupportedOperationException when called on a variable expression
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testBinaryOpSubValueException() {
        Variable x = new Variable();
        Number eight = new Number(8);
        BinaryOp test = new BinaryOp(eight, BinaryOp.Op.SUB, x);
        double value = test.value();
    }

    /**
     * Testing the value method within BinaryOp (Multiplication)
     */
    @Test
    public void testBinaryOpMultValue() {
        // testing for numbers only
        Number pi = new Number(3.14);
        Number six = new Number(6);
        assertEquals("Subtracting numbers failed", 18.84, new BinaryOp(six, BinaryOp.Op.MULT, pi).value(),0);
        assertEquals("Numbers were assigned incorrect values", 18.84, new BinaryOp(six, BinaryOp.Op.MULT, pi).value(8),0);
        // testing with variables
        Variable x = new Variable();
        assertEquals("Finding the value of a variable added to a number failed", 12.0, new BinaryOp(six, BinaryOp.Op.MULT, x).value(2),0);
    }

    /**
     * Testing value() method throws UnsupportedOperationException on multiplication of variable expressions
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testBinaryOpMultValueException() {
        Variable x = new Variable();
        Number eight = new Number(8);
        BinaryOp test = new BinaryOp(eight, BinaryOp.Op.MULT, x);
        double value = test.value();
    }

    /**
     * Testing the value method within BinaryOp (Division)
     */
    @Test
    public void testBinaryOPDivValue() {
        // testing for numbers only
        Number pi = new Number(1.5);
        Number six = new Number(6);
        assertEquals("Subtracting numbers failed", 4.0, new BinaryOp(six, BinaryOp.Op.DIV, pi).value(),0);
        assertEquals("Numbers were assigned incorrect values", 4.0, new BinaryOp(six, BinaryOp.Op.DIV, pi).value(8),0);
        // testing with variables
        Variable x = new Variable();
        assertEquals("Finding the value of a variable added to a number failed", 3.0, new BinaryOp(six, BinaryOp.Op.DIV, x).value(2),0);
    }

    /**
     * Testing the div value() method throws UnsupportedOperationException on variable expressions
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testBinaryOpDivValueException() {
        Variable x = new Variable();
        Number eight = new Number(8);
        BinaryOp test = new BinaryOp(eight, BinaryOp.Op.DIV, x);
        double value = test.value();
    }

    /**
     * Testing the derivative method within BinaryOP (Addition + Subtraction)
     */
    @Test
    public void testBinaryOpAddDerivative() {
        Variable x = new Variable();
        Number three = new Number(3);
        BinaryOp plusTest = new BinaryOp(three, BinaryOp.Op.PLUS, x);
        BinaryOp subTest = new BinaryOp(three, BinaryOp.Op.SUB, x);
        assertTrue("Addition of derivatives incorrect", new Number(1).equals(plusTest.derivative()));
        assertTrue("Subtraction of derivatives incorrect", new Number(-1).equals(subTest.derivative()));
    }

    /**
     * Testing the derivative method within BinaryOp (Product Rule)
     */
    @Test
    public void testBinaryOpProductRule() {
        Variable x = new Variable();
        Number three = new Number(3);
        BinaryOp multTest = new BinaryOp(three, BinaryOp.Op.MULT, x);
        // testing at 1 and 2 to ensure it truly is correct
        assertTrue("Simple chain rule failed", 3 == multTest.derivative().value(1));
        assertTrue("Simple chain rule failed", 3 == multTest.derivative().value(2));
        BinaryOp multTest2 = new BinaryOp(x, BinaryOp.Op.MULT, x);
        assertTrue("chain rule of variables failed", 2 == multTest2.derivative().value(1));
        assertTrue("chain rule of variables failed", 4 == multTest2.derivative().value(2));
    }

    /**
     * Testing the derivative method within BinaryOP (Quotient Rule)
     */
    @Test
    public void testBinaryOpQuotientRule() {
        Variable x = new Variable();
        Number three = new Number(3);
        BinaryOp divTest = new BinaryOp(three, BinaryOp.Op.DIV, x);
        // testing at 1 and 2 to ensure it is truly correct
        assertTrue("Simple quotient rule failed", -3.0 == divTest.derivative().value(1));
        assertTrue("Simple quotient rule failed", -0.75 == divTest.derivative().value(2));
    }


    /**
     * Testing the BinaryOp constructor and getter methods
     */
    @Test
    public void testBinaryOpConstructor() {
        Variable x = new Variable();
        Number ten = new Number(10);
        BinaryOp testOp = new BinaryOp(x, BinaryOp.Op.PLUS, ten);
        assertEquals("getLeftOperand failed", x, testOp.getLeftOperand());
        assertEquals("getOperator failed", BinaryOp.Op.PLUS, testOp.getOperator());
        assertEquals("getRightOperand failed", ten, testOp.getRightOperand());
    }

    /**
     * Testing the toString method within BinaryOp
     */
    @Test
    public void testBinaryOpToString() {
        Variable x = new Variable();
        Number ten = new Number(10);
        BinaryOp plusOp = new BinaryOp(x, BinaryOp.Op.PLUS, ten);
        BinaryOp subOp = new BinaryOp(x, BinaryOp.Op.SUB, ten);
        BinaryOp multOp = new BinaryOp(x, BinaryOp.Op.MULT, ten);
        BinaryOp divOp = new BinaryOp(x, BinaryOp.Op.DIV, ten);
        assertEquals("plus operator toString incorrect", "x + 10.0", plusOp.toString());
        assertEquals("sub operator toString incorrect", "x - 10.0", subOp.toString());
        assertEquals("mult operator toString incorrect", "x * 10.0", multOp.toString());
        assertEquals("div operator toString incorrect", "x / 10.0", divOp.toString());
        // testing binary ops of binary ops
        assertEquals("first element binary op was not put into parentheses", "(x + 10.0) + 6.0", new BinaryOp(plusOp, BinaryOp.Op.PLUS, new Number(6)).toString());
        // testing two binary ops of the same operator
        assertEquals("Two binary ops of same operator parentheses were incorrect", "(x + 10.0) + x + 10.0", new BinaryOp(plusOp, BinaryOp.Op.PLUS, plusOp).toString());
        // testing two binary ops of different operators
        assertEquals("Switch operator test failed.", "(x + 10.0) - x - 10.0", new BinaryOp(plusOp, BinaryOp.Op.SUB, subOp).toString());
        assertEquals("Differing central operator test failed.", "(x + 10.0) - (x + 10.0)", new BinaryOp(plusOp, BinaryOp.Op.SUB, plusOp).toString());
        assertEquals("All different operators test failed", "(x + 10.0) * (x - 10.0)", new BinaryOp(plusOp, BinaryOp.Op.MULT, subOp).toString());
    }

    /**
     * Testing the equals method within BinaryOp
     */
    @Test
    public void testBinaryOpEquals() {
        Number six = new Number(6);
        Number six2 = new Number(6);
        Number one = new Number(1);
        Number one2 = new Number(1);
        Variable x = new Variable();
        Variable y = new Variable();
        BinaryOp numberPlusTest = new BinaryOp(six, BinaryOp.Op.PLUS, one);
        BinaryOp numberPlusTest1 = new BinaryOp(six2, BinaryOp.Op.PLUS, one2);
        // sub
        BinaryOp numberSUBTest = new BinaryOp(six, BinaryOp.Op.SUB, one);
        BinaryOp numberSUBTest1 = new BinaryOp(six2, BinaryOp.Op.SUB, one2);
        // div
        BinaryOp numberDIVTest = new BinaryOp(six, BinaryOp.Op.DIV, one);
        BinaryOp numberDIVTest1 = new BinaryOp(six2, BinaryOp.Op.DIV, one2);
        // mult
        BinaryOp numberMULTTest = new BinaryOp(six, BinaryOp.Op.MULT, one);
        BinaryOp numberMULTTest1 = new BinaryOp(six2, BinaryOp.Op.MULT, one2);
        // variable binaryOp
        BinaryOp xPlus = new BinaryOp(x, BinaryOp.Op.PLUS, y);
        BinaryOp xPlus2 = new BinaryOp(y, BinaryOp.Op.PLUS, x);
        BinaryOp xSub = new BinaryOp(x, BinaryOp.Op.SUB, y);
        BinaryOp xSub2 = new BinaryOp(y, BinaryOp.Op.SUB, x);
        BinaryOp xMult = new BinaryOp(x, BinaryOp.Op.MULT, y);
        BinaryOp xMult2 = new BinaryOp(y, BinaryOp.Op.MULT, x);
        BinaryOp xDiv = new BinaryOp(x, BinaryOp.Op.DIV, y);
        BinaryOp xDiv2 = new BinaryOp(y, BinaryOp.Op.DIV, x);
        // testing that all the elements must be the same
        assertTrue("Plus test failed",numberPlusTest.equals(numberPlusTest1));
        assertTrue("Subtraction test failed", numberSUBTest.equals(numberSUBTest1));
        assertTrue("Division test failed", numberDIVTest.equals(numberDIVTest1));
        assertTrue("Multiplication test failed", numberMULTTest.equals(numberMULTTest1));
        // testing that a change in operator will make it false
        assertFalse("Operators incorrectly asserted equal", numberPlusTest.equals(numberSUBTest));
        // testing for mixed order on all operations
        // testing that for any operation double variables always yield true
        assertTrue("Plus variable test failed",xPlus.equals(xPlus2));
        assertTrue("Subtraction variable test failed", xSub.equals(xSub2));
        assertTrue("Division variable test failed", xDiv.equals(xDiv2));
        assertTrue("Multiplication variable test failed", xMult.equals(xMult2));
    }

    /**
     * Testing the simplify method of binaryOp
     */
    @Test
    public void testBinaryOpSimplify() {
        // Test addition and subtraction simplification rules
        BinaryOp test1 = new BinaryOp(new Number(0), BinaryOp.Op.PLUS, new Number(5));
        assertEquals("Adding 0 test failed", new Number(5), test1.simplify());

        BinaryOp test2 = new BinaryOp(new Number(2), BinaryOp.Op.SUB, new Number(0));
        assertEquals("Subtracting 0 test failed", new Number(2), test2.simplify());

        BinaryOp test3 = new BinaryOp(new Number(3), BinaryOp.Op.PLUS, new Number(4));
        assertEquals("Adding two numbers failed", new Number(7), test3.simplify());

        BinaryOp test4 = new BinaryOp(new Number(3), BinaryOp.Op.SUB, new Number(4));
        assertEquals("Subtracting two numbers failed", new Number(-1), test4.simplify());

        // Test multiplication simplification rules
        BinaryOp test5 = new BinaryOp(new Number(1), BinaryOp.Op.MULT, new Number(5));
        assertEquals("Multiplication by 1 testfailed", new Number(5), test5.simplify());

        BinaryOp test6 = new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Number(0));
        assertEquals("Multimplication by 0 test failed", new Number(0), test6.simplify());

        BinaryOp test7 = new BinaryOp(new Number(0), BinaryOp.Op.MULT, new Number(3));
        assertEquals("0 as the first element in Mult Binary Op test failed", new Number(0), test7.simplify());

        BinaryOp test8 = new BinaryOp(new Number(2), BinaryOp.Op.MULT, new BinaryOp(new Number(3), BinaryOp.Op.PLUS, new Number(4)));
        assertEquals("Algebra simplification", new Number(14), test8.simplify());

        // Test division simplification rules
        BinaryOp test9 = new BinaryOp(new Number(5), BinaryOp.Op.DIV, new Number(1));
        assertEquals("Dividing by 1 test failed", new Number(5), test9.simplify());

        BinaryOp test10 = new BinaryOp(new Number(10), BinaryOp.Op.DIV, new Number(2));
        assertEquals("Dividing two numbers test failed.", new Number(5), test10.simplify());

        BinaryOp test11 = new BinaryOp(new Number(0), BinaryOp.Op.SUB, new Variable());
        assertEquals("Subtracting from 0 test failed.", new BinaryOp(new Number(-1), BinaryOp.Op.MULT, new Variable()), test11.simplify());

        BinaryOp test12 = new BinaryOp(new Number(4), BinaryOp.Op.MULT, new BinaryOp(new Number(4), BinaryOp.Op.PLUS, new Variable()));
        assertEquals("Multiplication of constants test failed", new BinaryOp(new Number(16), BinaryOp.Op.PLUS, new Variable()), test12.simplify());
    }

    // Testing the Polynomial Class
    /**
     * Testing the constructor and getter methods of the polynomial class
     */
    @Test
    public void testPolynomialConstructor() {
        Polynomial test = new Polynomial(new Number(2), 4);
        assertTrue("Operand getter method test failed", new Number(2).equals(test.getOperand()));
        assertEquals("Power getter method test failed.", 4.0, test.getPower(), 0);
    }

    /**
     * testing the value method of Polynomial
     */
    @Test
    public void testPolynomialValue() {
        Polynomial test = new Polynomial(new Number(2), 4);
        Polynomial test2 = new Polynomial(new Variable(), 2);
        assertEquals("Value with input on number only polynomial failed", 16.0, test.value(5),0);
        assertEquals("Value with no input on number only polynomial failed", 16.0, test.value(), 0);
        assertEquals("Value with input on variable polynomial failed.", 64.0, test2.value(8), 0);
    }

    /**
     * Testing that value() throws UnsupportedOperationException when done on a variable
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testPolynomialValueThrows() {
        Polynomial test2 = new Polynomial(new Variable(), 2);
        double testVal = test2.value();
    }

    /**
     * Testing the toString method of polynomial
     */
    @Test
    public void testPolynomialToString() {
        Polynomial test = new Polynomial(new Number(2), 4);
        Polynomial test2 = new Polynomial(new Variable(), 2);
        Polynomial test3 = new Polynomial(new BinaryOp(new Variable(), BinaryOp.Op.PLUS, new Number(7)), 3);
        assertEquals("Number polynomial toString failed", "2.0^4.0", test.toString());
        assertEquals("Variable polynomial toString failed", "x^2.0", test2.toString());
        assertEquals("BinaryOp polynomial toString failed", "(x + 7.0)^3.0", test3.toString());
    }

    /**
     * Testing the derivative method of Polynomial
     */
    @Test
    public void testPolynomialderivative() {
        Polynomial test = new Polynomial(new Number(2), 4);
        Polynomial test2 = new Polynomial(new Variable(), 2);
        Polynomial test3 = new Polynomial(new BinaryOp(new Variable(), BinaryOp.Op.PLUS, new Number(7)), 3);
        assertTrue("Numbers incorrectly passed down", new Number(0).equals(test.derivative()));
        assertTrue("Simple variable polynomial incorrectly solved.", new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Variable()).equals(test2.derivative()));
        // test at 1 and 2 to ensure that it is actually true
        assertEquals("BinaryOp Polynomial chain rule incorrectly computed",192.0, test3.derivative().value(1), 0);
        assertEquals("BinaryOp Polynomial chain rule incorrectly computed",243.0, test3.derivative().value(2), 0);
    }

    /**
     * Testing the equals method in Polynomial class
     */
    @Test
    public void testPolynomialEquals() {
        Polynomial test = new Polynomial(new Number(2), 4);
        Polynomial test2 = new Polynomial(new Variable(), 4);
        Polynomial test3 = new Polynomial(new Number(2), 3);
        Polynomial test4 = new Polynomial(new Number(2), 4);
        Variable x = new Variable();
        assertTrue("matching operand and power test failed", test.equals(test4));
        assertFalse("Matching powers only incorrectly asserted true", test.equals(test2));
        assertFalse("Matching operands only incorrectly asserted true.", test.equals(test3));
        assertFalse("Non polynomial incorrectly asserted true", test2.equals(x));
    }

    /**
     * Testing the simplify method in Polynomial
     */
    @Test
    public void testPolynomialSimplify() {
            // Test polynomial simplification rules
        Polynomial test1 = new Polynomial(new Variable(), 1);
        assertEquals("Polynomial to the power of 1 test failed.", new Variable(), test1.simplify());

        Polynomial test2 = new Polynomial(new Variable(), 0);
        assertEquals("Polynomial to the 0th power test failed.", new Number(1), test2.simplify());

        Polynomial test3 = new Polynomial(new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Number(4)), 2);
        assertEquals("Operand is simplified test failed.", new Polynomial(new Number(8), 2), test3.simplify());
    }

    // Testing the Sin Class
    /**
     * Testing the constructor and getter methods for Sin
     */
    @Test
    public void testSinConstructor() {
        Sin test = new Sin(new Variable());
        assertTrue(new Variable().equals(test.getOperand()));
    }

    /**
     * Testing the value method in Sin
     */
    @Test
    public void testSinValue() {
        Sin test = new Sin(new Variable());
        double rightAngle = Math.toRadians(90.0);
        assertEquals("variable input value test failed", Math.sin(rightAngle), test.value(rightAngle),0);
        Sin test2 = new Sin(new Number(rightAngle));
        assertEquals("Number input value test failed", Math.sin(rightAngle), test2.value(900),0);
        assertEquals("Number no input value test failed", Math.sin(rightAngle), test2.value(),0);
    }

    /**
     * Testing for an UnsupportedOperationException when calling value() on an operand with variables
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testSinValueThrows() {
        Sin test = new Sin(new Variable());
        double val = test.value();
    }

    /**
     * Testing the toString method in the Sin class (only have to test that the operand shows up in the proper place
     */
    @Test
    public void testSinToString() {
        Sin test = new Sin(new Variable());
        assertEquals("Operand not properly formatted", "Sin[x]", test.toString());
    }

    /**
     * Testing equals in the Sin class
     */
    @Test
    public void testSinEquals() {
        Sin test = new Sin(new Variable());
        Sin test2 = new Sin(new Variable());
        Variable x = new Variable();
        Sin test3 = new Sin(new Number(8));
        assertTrue("Identical operand equals test failed", test.equals(test2));
        assertFalse("Different function equals test failed", test.equals(x));
        assertFalse("Differing operand equals test failed", test.equals(test3));
    }

    /**
     * Testing the derivative method on the Sin class
     */
    @Test
    public void testSinDerivative() {
        Sin test1 = new Sin(new Number(8));
        Sin test2 = new Sin(new Variable());
        Sin test3 = new Sin(new Polynomial(new Variable(), 2));
        assertEquals("Sin of a constant derivative failed.", new Number(0), test1.derivative());
        assertEquals("Sin of a single variable test failed", new Cos(new Variable()), test2.derivative());
        assertEquals("Sin chain rule incorrectly solved.",
                new BinaryOp(new BinaryOp (new Number(2), BinaryOp.Op.MULT, new Variable()), BinaryOp.Op.MULT, new Cos(new Polynomial(new Variable(), 2))),
                test3.derivative());
    }

    /**
     * Testing the simplify method for Sin
     */
    @Test
    public void testSinSimplify() {
        Sin test3 = new Sin(new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Number(4)));
        assertEquals("Operand is simplified test failed.", new Sin(new Number(8)), test3.simplify());
    }

    // Testing the Cos Class

    /**
     * Testing the constructor and getter methods for Cos
     */
    @Test
    public void testCosConstructor() {
        Cos test = new Cos(new Variable());
        assertTrue(new Variable().equals(test.getOperand()));
    }

    /**
     * Testing the value method in Cos
     */
    @Test
    public void testCosValue() {
        Cos test = new Cos(new Variable());
        double rightAngle = Math.toRadians(90.0);
        assertEquals("variable input value test failed", Math.cos(rightAngle), test.value(rightAngle),0);
        Cos test2 = new Cos(new Number(rightAngle));
        assertEquals("Number input value test failed", Math.cos(rightAngle), test2.value(900),0);
        assertEquals("Number no input value test failed", Math.cos(rightAngle), test2.value(),0);
    }

    /**
     * Testing for an UnsupportedOperationException when calling value() on an operand with variables
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testCosValueThrows() {
        Cos test = new Cos(new Variable());
        double val = test.value();
    }

    /**
     * Testing the toString method in the Cos class (only have to test that the operand shows up in the proper place
     */
    @Test
    public void testCosToString() {
        Cos test = new Cos(new Variable());
        assertEquals("Operand not properly formatted", "Cos[x]", test.toString());
    }

    /**
     * Testing equals in the Cos class
     */
    @Test
    public void testCosEquals() {
        Cos test = new Cos(new Variable());
        Cos test2 = new Cos(new Variable());
        Variable x = new Variable();
        Cos test3 = new Cos(new Number(8));
        assertTrue("Identical operand equals test failed", test.equals(test2));
        assertFalse("Different function equals test failed", test.equals(x));
        assertFalse("Differing operand equals test failed", test.equals(test3));
    }

    /**
     * Testing the derivative method on the Cos class
     */
    @Test
    public void testCosDerivative() {
        Cos test1 = new Cos(new Number(8));
        Cos test2 = new Cos(new Variable());
        Cos test3 = new Cos(new Polynomial(new Variable(), 2));
        assertEquals("Cos of a constant derivative failed.", new Number(0), test1.derivative());
        assertEquals("Cos of a single variable test failed", new BinaryOp(new Number(-1), BinaryOp.Op.MULT, new Sin(new Variable())), test2.derivative());
        assertEquals("Cos chain rule incorrectly solved.",
                new BinaryOp(new BinaryOp (new Number(2), BinaryOp.Op.MULT, new Variable()), BinaryOp.Op.MULT, new BinaryOp(new Number(-1), BinaryOp.Op.MULT, new Sin(new Polynomial(new Variable(), 2)))),
                test3.derivative());
    }

    /**
     * Testing the simplify method of the Cos class
     */
    @Test
    public void testCosSimplify() {
        Cos test3 = new Cos(new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Number(4)));
        assertEquals("Operand is simplified test failed.", new Cos(new Number(8)), test3.simplify());
    }

    // Testing the Log class

    /**
     * Testing the constructor and getter methods for Log
     */
    @Test
    public void testLogConstructor() {
        Log test = new Log(new Variable());
        assertTrue(new Variable().equals(test.getOperand()));
    }

    /**
     * Testing the value method in Log
     */
    @Test
    public void testLogValue() {
        Log test = new Log(new Variable());
        assertEquals("variable input value test failed", Math.log(12), test.value(12),0);
        Log test2 = new Log(new Number(3));
        assertEquals("Number input value test failed", Math.log(3), test2.value(900),0);
        assertEquals("Number no input value test failed", Math.log(3), test2.value(),0);
    }

    /**
     * Testing for an UnsupportedOperationException when calling value() on an operand with variables
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testLogValueThrows() {
        Log test = new Log(new Variable());
        double val = test.value();
    }

    /**
     * Testing the toString method in the Log class (only have to test that the operand shows up in the proper place
     */
    @Test
    public void testLogToString() {
        Log test = new Log(new Variable());
        assertEquals("Operand not properly formatted", "Log[x]", test.toString());
    }

    /**
     * Testing equals in the Log class
     */
    @Test
    public void testLogEquals() {
        Log test = new Log(new Variable());
        Log test2 = new Log(new Variable());
        Variable x = new Variable();
        Log test3 = new Log(new Number(8));
        assertTrue("Identical operand equals test failed", test.equals(test2));
        assertFalse("Different function equals test failed", test.equals(x));
        assertFalse("Differing operand equals test failed", test.equals(test3));
    }

    /**
     * Testing the derivative method on the Log class
     */
    @Test
    public void testLogDerivative() {
        Log test1 = new Log(new Number(8));
        Log test2 = new Log(new Variable());
        Log test3 = new Log(new Polynomial(new Variable(), 2));
        assertEquals("Log of a constant derivative failed.", new Number(0), test1.derivative());
        assertEquals("Log of a single variable test failed", new BinaryOp(new Number(1), BinaryOp.Op.DIV, new Variable()), test2.derivative());
        assertEquals("Log chain rule incorrectly solved.",
                new BinaryOp( new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Variable()), BinaryOp.Op.MULT, new BinaryOp(new Number(1), BinaryOp.Op.DIV, new Polynomial(new Variable(),2))), test3.derivative());
    }

    /**
     * Testing the simplify method of the Log class
     */
    @Test
    public void testLogSimplify() {
        Log test3 = new Log(new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Number(4)));
        assertEquals("Operand is simplified test failed.", new Log(new Number(8)), test3.simplify());
    }

    // Testing the Exp Class

    /**
     * Testing the constructor and getter methods for Exp
     */
    @Test
    public void testExpConstructor() {
        Exp test = new Exp(new Variable());
        assertTrue(new Variable().equals(test.getOperand()));
    }

    /**
     * Testing the value method in Exp
     */
    @Test
    public void testExpValue() {
        Exp test = new Exp(new Variable());
        assertEquals("variable input value test failed", Math.pow(Math.E, 12), test.value(12),0);
        Exp test2 = new Exp(new Number(3));
        assertEquals("Number input value test failed", Math.pow(Math.E, 3), test2.value(900),0);
        assertEquals("Number no input value test failed", Math.pow(Math.E, 3), test2.value(),0);
    }

    /**
     * Testing for an UnsupportedOperationException when calling value() on an operand with variables
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testExpValueThrows() {
        Exp test = new Exp(new Variable());
        double val = test.value();
    }

    /**
     * Testing the toString method in the Exp class (only have to test that the operand shows up in the proper place
     */
    @Test
    public void testExpToString() {
        Exp test = new Exp(new Variable());
        assertEquals("Operand not properly formatted", "Exp[x]", test.toString());
    }

    /**
     * Testing equals in the Exp class
     */
    @Test
    public void testExpEquals() {
        Exp test = new Exp(new Variable());
        Exp test2 = new Exp(new Variable());
        Variable x = new Variable();
        Exp test3 = new Exp(new Number(8));
        assertTrue("Identical operand equals test failed", test.equals(test2));
        assertFalse("Different function equals test failed", test.equals(x));
        assertFalse("Differing operand equals test failed", test.equals(test3));
    }

    /**
     * Testing the derivative method on the Exp class
     */
    @Test
    public void testExpDerivative() {
        Exp test1 = new Exp(new Number(8));
        Exp test2 = new Exp(new Variable());
        Exp test3 = new Exp(new Polynomial(new Variable(), 2));
        assertEquals("Exp of a constant derivative failed.", new Number(0), test1.derivative());
        assertEquals("Exp of a single variable test failed", test2, test2.derivative());
        assertEquals("Exp chain rule incorrectly solved.",
                 new BinaryOp(new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Variable()), BinaryOp.Op.MULT, test3),
                 test3.derivative());
    }

    /**
     * Testing the simplify method of the Exp class
     */
    @Test
    public void testExpSimplify() {
        Exp test3 = new Exp(new BinaryOp(new Number(2), BinaryOp.Op.MULT, new Number(4)));
        assertEquals("Operand is simplified test failed.", new Exp(new Number(8)), test3.simplify());
    }

    // Testing complex functions
}
