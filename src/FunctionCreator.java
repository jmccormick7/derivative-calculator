import java.util.*;

/**
 * A Class that creates functions from String Inputs
 */
public class FunctionCreator {
    /**
     * Set of Operators
     */
    private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
    /**
     * Set of function types
     */
    private static final Set<String> FUNCTIONS = new HashSet<>(Arrays.asList("sin", "cos", "exp", "log", "pol"));

    /**
     * Creates a function from a string
     * @param input string representation of a function
     * @return the function
     */
    public static Function createFunction(String input) {
        String[] postfix = infixToPostfix(input);
        return evaluatePostfix(postfix);
    }

    private static String[] infixToPostfix(String input) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        List<String> tokens = tokenize(input);
        for (String token : tokens) {
            if (isNumeric(token) || "x".equals(token)) {
                output.add(token);
            } else if (FUNCTIONS.contains(token) || "(".equals(token)) {
                stack.push(token);
            } else if (")".equals(token)) {
                while (!stack.isEmpty() && !"(".equals(stack.peek())) {
                    output.add(stack.pop());
                }
                if (!stack.isEmpty() && "(".equals(stack.peek())) {
                    stack.pop();
                }
                if (!stack.isEmpty() && FUNCTIONS.contains(stack.peek())) {
                    output.add(stack.pop());
                }
            } else {
                while (!stack.isEmpty() && !"(".equals(stack.peek()) && precedence(stack.peek()) >= precedence(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output.toArray(new String[0]);
    }


    private static Function evaluatePostfix(String[] postfix) {
        Stack<Function> stack = new Stack<>();

        for (String token : postfix) {
            if (token.startsWith("^")) {
                String exponentStr = token.substring(1);
                double exponent = Double.parseDouble(exponentStr);
                Function base = stack.pop();
                stack.push(new Polynomial(base, exponent));
            }
            else {
                switch (token) {
                    case "x":
                        stack.push(new Variable());
                        break;
                    case "sin":
                        stack.push(new Sin(stack.pop()));
                        break;
                    case "cos":
                        stack.push(new Cos(stack.pop()));
                        break;
                    case "exp":
                        stack.push(new Exp(stack.pop()));
                        break;
                    case "log":
                        stack.push(new Log(stack.pop()));
                        break;
                    case "^":
                        Function power = stack.pop();
                        Function operand = stack.pop();

                        if (power instanceof Number) {
                            stack.push(new Polynomial(operand, ((Number) power).getNumberValue()));
                        } else {
                            throw new IllegalArgumentException("Power must be a number.");
                        }
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        Function right = stack.pop();
                        Function left = stack.pop();
                        BinaryOp.Op operator = stringToOp(token);
                        stack.push(new BinaryOp(left, operator, right));
                        break;
                    default:
                        stack.push(new Number(Double.parseDouble(token)));
                        break;
                }
            }
        }

        return stack.pop();
    }

    private static List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isWhitespace(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
            } else if (OPERATORS.contains(String.valueOf(c)) || c == '(' || c == ')' || c == '^') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                if (c == '^') {
                    String nextToken = "";
                    while (i + 1 < input.length() && Character.isDigit(input.charAt(i + 1))) {
                        nextToken += input.charAt(i + 1);
                        i++;
                    }
                    tokens.add("^" + nextToken);
                } else {
                    tokens.add(String.valueOf(c));
                }
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private static BinaryOp.Op stringToOp(String str) {
        switch (str) {
            case "+":
                return BinaryOp.Op.PLUS;
            case "-":
                return BinaryOp.Op.SUB;
            case "*":
                return BinaryOp.Op.MULT;
            case "/":
                return BinaryOp.Op.DIV;
            default:
                throw new IllegalArgumentException("Invalid operator string: " + str);
        }
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
