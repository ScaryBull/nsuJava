package oopj;


public interface Command {
    void execute(ExecutionContext context, String[] args) throws Exception;
}
