package bean;

import java.util.Arrays;

public  class VarHandlesDemo {
    public int count = 1;
    protected long sum = 100;
    private String name = "init";
    public int[] arrayData = new int[]{3,5,7};

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", sum=" + sum +
                ", data=" + Arrays.toString(arrayData) +
                '}';
    }
}