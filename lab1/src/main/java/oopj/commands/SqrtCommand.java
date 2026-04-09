package oopj.commands;

import oopj.Command;
import oopj.ExecutionContext;
import oopj.exceptions.*;


public class SqrtCommand implements Command {
    public void execute(ExecutionContext context, String[] args) throws Exception {
        if (context.getStack().isEmpty()) 
            throw new StackUnderflowException("Stack is empty");
        
        double val = context.getStack().pop();
        if (val < 0) 
            throw new NegativeNumberException("Root of negative number: " + val);
        
        context.getStack().push(Math.sqrt(val));
    }
}
