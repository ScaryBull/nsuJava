package oopj.commands;

import oopj.Command;
import oopj.ExecutionContext;
import oopj.exceptions.StackUnderflowException;


public class SubtractionCommand implements Command {
    public void execute(ExecutionContext context, String[] args) throws Exception {
        if (context.getStack().size() < 2) 
            throw new StackUnderflowException("Not enough arguments on the stack for '-'");
        double b = context.getStack().pop();
        double a = context.getStack().pop();
        context.getStack().push(a - b);
    }
}
