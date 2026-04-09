package oopj;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import oopj.commands.*;
import oopj.exceptions.StackUnderflowException;
import static org.junit.jupiter.api.Assertions.*;


class CalTest {
    private ExecutionContext context;

    @BeforeEach
    void setUp() {
        context = new ExecutionContext();
    }

    @Test
    void testAddCommand() throws Exception {
        context.getStack().push(10.0);
        context.getStack().push(5.0);
        
        AddCommand add = new AddCommand();
        add.execute(context, new String[0]);
        
        assertEquals(15.0, context.getStack().peek());
    }

    @Test
    void testSqrtCommand() throws Exception {
        context.getStack().push(16.0);
        
        SqrtCommand sqrt = new SqrtCommand();
        sqrt.execute(context, new String[0]);
        
        assertEquals(4.0, context.getStack().peek());
    }

    @Test
    void testDefineAndPush() throws Exception {
        DefineCommand define = new DefineCommand();
        define.execute(context, new String[]{"a", "10"});
        
        PushCommand push = new PushCommand();
        push.execute(context, new String[]{"a"});
        
        assertEquals(10.0, context.getStack().peek());
    }

    @Test
    void testStackUnderflow() {
        AddCommand add = new AddCommand();
        assertThrows(StackUnderflowException.class, () -> {add.execute(context, new String[0]);});
    }
}