import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Test class for the Derivative Calculator
 * @author John McCormick
 */
public class Project3Tester {

    /**
     * Test for operands .equals() method
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
     * Test for operands toString() method
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
     * Test the operand constructors, and the getter method for getOperandValue
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
}
