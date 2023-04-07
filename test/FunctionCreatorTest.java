import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionCreatorTest {

    @Test
    public void testVariable() {
        Function function = FunctionCreator.createFunction("x");
        assertTrue(function instanceof Variable);
    }

    @Test
    public void testNumber() {
        Function function = FunctionCreator.createFunction("42");
        assertTrue(function instanceof Number);
        assertEquals(42, ((Number) function).getNumberValue(), 0);
    }

    @Test
    public void testSin() {
        Function function = FunctionCreator.createFunction("sin(x)");
        assertTrue(function instanceof Sin);
        assertTrue(((Sin) function).getOperand() instanceof Variable);
    }

    @Test
    public void testCos() {
        Function function = FunctionCreator.createFunction("cos(x)");
        assertTrue(function instanceof Cos);
        assertTrue(((Cos) function).getOperand() instanceof Variable);
    }

    @Test
    public void testExp() {
        Function function = FunctionCreator.createFunction("exp(x)");
        assertTrue(function instanceof Exp);
        assertTrue(((Exp) function).getOperand() instanceof Variable);
    }

    @Test
    public void testLog() {
        Function function = FunctionCreator.createFunction("log(x)");
        assertTrue(function instanceof Log);
        assertTrue(((Log) function).getOperand() instanceof Variable);
    }

    @Test
    public void testPolynomial() {
        Function function = FunctionCreator.createFunction("x^2");
        assertTrue(function instanceof Polynomial);
        assertTrue(((Polynomial) function).getOperand() instanceof Variable);
        assertEquals(2, ((Polynomial) function).getPower(), 0);
    }

    @Test
    public void testBinaryOp() {
        Function function = FunctionCreator.createFunction("x + 2");
        assertTrue(function instanceof BinaryOp);
        assertTrue(((BinaryOp) function).getLeftOperand() instanceof Variable);
        assertEquals(BinaryOp.Op.PLUS, ((BinaryOp) function).getOperator());
        assertTrue(((BinaryOp) function).getRightOperand() instanceof Number);
        assertEquals(2, ((Number) ((BinaryOp) function).getRightOperand()).getNumberValue(), 0);
    }

    @Test
    public void testComplexExpression() {
        Function function = FunctionCreator.createFunction("sin(x^2) + cos(2 * x)");
        assertTrue(function instanceof BinaryOp);

        BinaryOp binaryOp = (BinaryOp) function;
        assertEquals(BinaryOp.Op.PLUS, binaryOp.getOperator());

        assertTrue(binaryOp.getLeftOperand() instanceof Sin);
        Sin sin = (Sin) binaryOp.getLeftOperand();
        assertTrue(sin.getOperand() instanceof Polynomial);
        Polynomial polynomial = (Polynomial) sin.getOperand();
        assertTrue(polynomial.getOperand() instanceof Variable);
        assertEquals(2, polynomial.getPower(), 0);

        assertTrue(binaryOp.getRightOperand() instanceof Cos);
        Cos cos = (Cos) binaryOp.getRightOperand();
        assertTrue(cos.getOperand() instanceof BinaryOp);
        BinaryOp binaryOp2 = (BinaryOp) cos.getOperand();
        assertEquals(BinaryOp.Op.MULT, binaryOp2.getOperator());
        assertTrue(binaryOp2.getLeftOperand() instanceof Number);
        assertEquals(2, ((Number) binaryOp2.getLeftOperand()).getNumberValue(), 0);
        assertTrue(binaryOp2.getRightOperand() instanceof Variable);
    }

    @Test
    public void testComplexNestedExpression() {
        String input = "2 * (3 + sin(x^2) * (4 - x) / (cos(2 * x) - exp(log(x))))";
        Function f = FunctionCreator.createFunction(input);

        double x = 1.5;
        double expectedResult = 2 * (3 + Math.sin(Math.pow(x, 2)) * (4 - x) / (Math.cos(2 * x) - Math.exp(Math.log(x))));
        double actualResult = f.value(x);

        assertTrue(Math.abs(expectedResult - actualResult) < 1e-9);
    }
}
