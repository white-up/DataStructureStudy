package stack;

import java.util.Arrays;

public class MyStackByArray<T> {
    private static final int max = 20;
    private Object[] stack;
    private int size;

    public MyStackByArray() {
        stack = new Object[max];
    }

    /**
     * boolean	empty()测试此堆栈是否为空
     * E   peek()查看此堆栈顶部的对象而不将其从堆栈中移除
     * E	pop()移除此堆栈顶部的对象并将该对象作为此函数的值返回
     * E	push(E item)将一个项目推到这个堆栈的顶部
     * int	search(Object o)返回对象在此堆栈上的从1开始的位置
     */
    public boolean empty() {
        return size == 0;
    }

    public T peek() {
        return size > 0 ? (T) stack[size - 1] : null;
    }

    public T pop() {
        T ans = peek();
        if (size > 0) {
            stack[size - 1] = null;
            size--;
        }
        return ans;
    }

    public void push(T t) {
        expendCapacity(size + 1);
        stack[size++] = t;
    }

    public void expendCapacity(int size) {
        int len = stack.length;
        if (size >= len) {
            int newCapacity = len + (len >> 1);
            //max=max*3/2+1;
            stack = Arrays.copyOf(stack, newCapacity);
        }
    }

    public int search(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(stack[i])) {
                return size - i;
            }
        }
        return -1;
    }

}