package oopj;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;


public class ExecutionContext {
    private final Stack<Double> stack = new Stack<>();
    private final Map<String, Double> parameters = new HashMap<>();

    public Stack<Double> getStack() {
        return stack; 
    }

    public Map<String, Double> getParameters() { 
        return parameters;
    }
}
