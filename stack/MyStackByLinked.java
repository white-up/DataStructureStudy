package stack;

import Link.Linked;

public class MyStackByLinked<T> {
    private Linked<T> linked;

    public MyStackByLinked() {
        linked = new Linked<>();
    }

    /**
     * boolean	empty()测试此堆栈是否为空
     * E   peek()查看此堆栈顶部的对象而不将其从堆栈中移除
     * E	pop()移除此堆栈顶部的对象并将该对象作为此函数的值返回
     * E	push(E item)将一个项目推到这个堆栈的顶部
     * int	search(Object o)返回对象在此堆栈上的从1开始的位置
     */
    public boolean empty() {
        return linked.isEmpty();
    }

    public T pop() {
        T ans = linked.getLast();
        linked.removeLast();
        return ans;
    }

    public T peek() {
        return linked.getLast();
    }

    public void push(T item) {
        linked.addLast(item);
    }

    public int search(Object o) {
        int ans = linked.search(o);
        if (ans > 0) {
            return linked.getSize() + 1 - ans;
        } else {
            return -1;
        }
    }

}