package oopj.commands;

import oopj.Command;
import oopj.ExecutionContext;
import oopj.exceptions.StackUnderflowException;


public class MultiplyCommand implements Command {
    public void execute(ExecutionContext context, String[] args) throws Exception {
        if (context.getStack().size() < 2) 
            throw new StackUnderflowException("Not enough arguments on the stack for '*'");
        context.getStack().push(context.getStack().pop() * context.getStack().pop());
    }
}