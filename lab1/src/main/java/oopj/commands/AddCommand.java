package oopj.commands;

import oopj.Command;
import oopj.ExecutionContext;
import oopj.exceptions.StackUnderflowException;


public class AddCommand implements Command{
    @Override
    public void execute(ExecutionContext context, String[] args) throws Exception {
        if (context.getStack().size() < 2) {
            throw new StackUnderflowException("Error not enough arguments on the stack");
        }

        double arg2 = context.getStack().pop();
        double arg1 = context.getStack().pop();
        context.getStack().push(arg1 + arg2);
    }
}
