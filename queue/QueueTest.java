package queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    /**
     * boolean add(E e)如果可以在不违反容量限制的情况下立即将指定元素插入此队列,则true在成功时返回并在当前没有可用空间时抛出IllegalStateException
     * E	element()检索但不删除此队列的头部(队列为空则抛出异常)
     * boolean offer(E e)
     * 如果可以在不违反容量限制的情况下立即将指定元素插入此队列。
     * E	peek()
     * 检索但不删除此队列的头部，如果此队列为空则返回null
     * E	poll()
     * 检索并移除此队列的头部，如果此队列为空则返回null
     * E	remove()检索并删除此队列的头部。
     */
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        System.out.println(queue.size());
        System.out.println(queue.peek());
        queue.offer("test0");
        queue.offer("test2");
        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(queue.poll());

        System.out.println(queue.element());
        System.out.println(queue.size());
    }
}