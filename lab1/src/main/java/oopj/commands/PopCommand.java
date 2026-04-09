package oopj.commands;

import oopj.Command;
import oopj.ExecutionContext;
import oopj.exceptions.StackUnderflowException;


public class PopCommand implements Command {
    public void execute(ExecutionContext context, String[] args) throws Exception {
        if (context.getStack().isEmpty()) 
            throw new StackUnderflowException("Stack is empty");
        context.getStack().pop();
    }
}