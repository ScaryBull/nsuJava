package oopj.commands;

import oopj.Command;
import oopj.ExecutionContext;


public class DefineCommand implements Command {
    public void execute(ExecutionContext context, String[] args) throws Exception {
        String name = args[0];
        double value = Double.parseDouble(args[1]);
        context.getParameters().put(name, value);
    }
}
