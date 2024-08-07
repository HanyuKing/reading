import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Hanyu.Wang
 * @Date 2024/3/26 16:15
 * @Description
 * @Version 1.0
 **/
public class TestCollection {
    @Test
    public void testGetAllFrames(){
        List<StackWalker.StackFrame> stack =
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk((s) -> s.collect(Collectors.toList()));
        System.out.println("All frames : \n" + stack.toString());
    }

    private void print(StackWalker stackWalker){
        stackWalker.forEach(stackFrame -> System.out.printf("%6d| %s -> %s %n",
                stackFrame.getLineNumber(),
                stackFrame.getClassName(),
                stackFrame.getMethodName()));
    }

    @Test
    public void testShowReflectFrames(){
        final StackWalker stackWalker =
                StackWalker.getInstance(StackWalker.Option.SHOW_REFLECT_FRAMES);
        print(stackWalker);
    }

    @Test
    public void testConvenienceFactoryMethod() {
        List<Integer> list = List.of(1,2,3,4,5); // ImmutableCollections

        // list.add(6); // java.lang.UnsupportedOperationException

        Set<Integer> set = Set.of(1,2,3,4,5);
        Map<String, String> map = Map.of("key1","value1","key2","value2","key3","value3");

        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
    }
}
