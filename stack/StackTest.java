package stack;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
/**
 boolean	empty()测试此堆栈是否为空
 E   peek()查看此堆栈顶部的对象而不将其从堆栈中移除
 E	pop()移除此堆栈顶部的对象并将该对象作为此函数的值返回
 E	push(E item)将一个项目推到这个堆栈的顶部
 int	search(Object o)返回对象在此堆栈上的从1开始的位置
 */
        Stack stack = new Stack();
        //判断是否为空
        System.out.println(stack.empty());
        //进栈
        stack.push("test1");
        stack.push("test0");
    /*
    //取栈顶值(不出栈)
    System.out.println(stack.search("test1"));
    System.out.println(stack.peek());
    stack.pop();
    System.out.println(stack.peek());
    //出栈
    stack.pop();
    */
        //遍历
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}