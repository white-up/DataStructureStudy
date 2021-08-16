package stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyStackByQueues<T> {
    private Queue<T> queueIn;
    private Queue<T> queueOut;
    private int size;

    /**
     * boolean empty()测试此堆栈是否为空
     * E   peek()查看此堆栈顶部的对象而不将其从堆栈中移除
     * E	pop()移除此堆栈顶部的对象并将该对象作为此函数的值返回
     * E	push(E item)将一个项目推到这个堆栈的顶部
     * int	search(Object o)返回对象在此堆栈上的从1开始的位置
     */
    public MyStackByQueues() {
        queueIn = new ArrayDeque<>();
        queueOut = new ArrayDeque<>();
    }

    public boolean empty() {
        return size == 0;
    }

    public T peek() {
        if (empty()) {
            return null;
        }
        while (queueIn.size() != 1) {
            queueOut.add(queueIn.poll());
        }
        T ans = queueIn.peek();
        queueOut.add(queueIn.poll());
        while (!queueOut.isEmpty()) {
            queueIn.add(queueOut.poll());
        }
        return ans;
    }

    public T pop() {
        if (empty()) {
            return null;
        }
        while (queueIn.size() != 1) {
            queueOut.add(queueIn.poll());
        }
        T ans = queueIn.poll();
        while (!queueOut.isEmpty()) {
            queueIn.add(queueOut.poll());
        }
        size--;
        return ans;
    }

    public void push(T t) {
        queueIn.add(t);
        size++;
    }

    public int search(T t) {
        int ans = -1;
        for (int i = 0; i < size; i++) {
            T temp = queueIn.poll();
            if (t.equals(temp)) {
                ans = size - i;
            }
            queueOut.add(temp);
        }
        while (!queueOut.isEmpty()) {
            queueIn.add(queueOut.poll());
        }
        return ans;
    }

}