import bean.VarHandlesDemo;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @Author Hanyu.Wang
 * @Date 2024/3/26 16:48
 * @Description
 * @Version 1.0
 **/
public class TestVarHandles {

    @Test
    public void testCompareAndExchange() throws NoSuchFieldException, IllegalAccessException {
        VarHandlesDemo varHandlesDemo = new VarHandlesDemo();
        varHandlesDemo.count = 100;

        System.out.printf("count: %d \n", varHandlesDemo.count);

        VarHandle handle = MethodHandles.lookup()
                .in(VarHandlesDemo.class)
                .findVarHandle(VarHandlesDemo.class, "count", int.class);

        int result = (int) handle.compareAndExchange(varHandlesDemo, 100, 99);

        // boolean result = handle.compareAndSet(varHandlesDemo, 99, 99);

        System.out.printf("set result: %d \n", result);

        System.out.printf("count: %d \n", varHandlesDemo.count);

    }

    @Test
    public void testCompareAndSet() throws NoSuchFieldException, IllegalAccessException {
        VarHandlesDemo varHandlesDemo = new VarHandlesDemo();
        varHandlesDemo.count = 100;

        System.out.printf("count: %d \n", varHandlesDemo.count);

        VarHandle handle = MethodHandles.lookup()
                .in(VarHandlesDemo.class)
                .findVarHandle(VarHandlesDemo.class, "count", int.class);

        boolean result = handle.compareAndSet(varHandlesDemo, 100, 99);

        // boolean result = handle.compareAndSet(varHandlesDemo, 99, 99);

        System.out.printf("set result: %b \n", result);

        System.out.printf("count: %d \n", varHandlesDemo.count);

    }

    @Test
    public void testSetPublicField() throws NoSuchFieldException, IllegalAccessException {
        VarHandlesDemo instance = new VarHandlesDemo();
        VarHandle countHandle = MethodHandles.lookup()
                .in(VarHandlesDemo.class)
                .findVarHandle(VarHandlesDemo.class, "count", int.class);
        countHandle.set(instance, 99);
        System.out.println(instance.count);
    }

    @Test
    public void testSetProtectedField() throws NoSuchFieldException, IllegalAccessException {
        VarHandlesDemo instance = new VarHandlesDemo();
        VarHandle countHandle = MethodHandles.privateLookupIn(VarHandlesDemo.class, MethodHandles.lookup())
                .findVarHandle(VarHandlesDemo.class, "sum", long.class);
        countHandle.set(instance, 99999);
        System.out.println(instance);
    }

    @Test
    public void testSetPrivateField() throws NoSuchFieldException, IllegalAccessException {
        VarHandlesDemo instance = new VarHandlesDemo();
        VarHandle countHandle = MethodHandles.privateLookupIn(VarHandlesDemo.class, MethodHandles.lookup())
                .findVarHandle(VarHandlesDemo.class, "name", String.class);
        countHandle.set(instance, "hello world");
        System.out.println(instance);
    }
}
