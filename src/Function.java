/**
 * Function is a class that is used to represent a mathematical function
 * regardless of its complexity or its components.
 * @author John McCormick
 */
public abstract class Function {

    /**
     * A method that calculates the value of a function at a given double input
     * @param input a double at which to evaluate the function
     * @return the value of the function at the input value
     */
    public abstract double value(double input);

    /**
     * An overload on value that allows for calling the value of a function with no variables and only constants
     * @return the value of the function/expression.
     */
    public abstract double value();

    /**
     * A method that solves the derivative of the function it is called on.
     * @return a function that is the derivative of the function this method is called on
     */
    public abstract Function derivative();

    /**
     * A method that simplifies any expressions removing any unnecessary terms
     * @return the simplified funtion
     */
    public abstract Function simplify();
}
