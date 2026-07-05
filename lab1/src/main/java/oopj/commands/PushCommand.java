package oopj.commands;

import oopj.Command;
import oopj.ExecutionContext;


public class PushCommand implements Command {
    @Override
    public void execute(ExecutionContext context, String[] args) {
        String arg = args[0];
        Double value;
        if (context.getParameters().containsKey(arg)) {
            value = context.getParameters().get(arg);
        } else {
            value = Double.parseDouble(arg);
        }
        
        context.getStack().push(value);
    }
}
