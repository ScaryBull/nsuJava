package oopj.commands;

import oopj.Command;
import oopj.ExecutionContext;
import oopj.exceptions.StackUnderflowException;


public class PrintCommand implements Command {
    public void execute(ExecutionContext context, String[] args) throws Exception {
        if (context.getStack().isEmpty()) 
            throw new StackUnderflowException("Stack is empty");
        System.out.println(context.getStack().peek());
    }
}
