package queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyQueueByArray<T> {
    private static final int max = 20;
    /**
     * boolean add(E e)如果可以在不违反容量限制的情况下立即将指定元素插入此队列,则true在成功时返回并在当前没有可用空间时抛出IllegalStateException
     * E	element()检索但不删除此队列的头部(队列为空则抛出异常)
     * boolean offer(E e)如果可以在不违反容量限制的情况下立即将指定元素插入此队列。
     * E	peek()检索但不删除此队列的头部，如果此队列为空则返回null
     * E	poll()检索并移除此队列的头部，如果此队列为空则返回null
     * E	remove()检索并删除此队列的头部。
     */
    private Object[] queue;
    private int size;

    public MyQueueByArray() {
        queue = new Object[max];
    }

    public boolean add(T t) {
        if (size + 1 > queue.length) {
            throw new IllegalStateException();
        } else {
            queue[size++] = t;
            return true;
        }
    }

    public boolean offer(T t) {
        expendCapacity(size + 1);
        queue[size++] = t;
        return true;
    }

    public void expendCapacity(int size) {
        int len = queue.length;
        if (size >= len) {
            int newCapacity = len + (len >> 1);
            //max=max*3/2+1;
            queue = Arrays.copyOf(queue, newCapacity);
        }
    }

    public T peek() {
        return (T) queue[0];
    }

    public T element() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return (T) queue[0];
        }
    }

    public T poll() {
        if (size == 0) {
            return null;
        } else {
            T ans = (T) queue[0];
            for (int i = 0; i < size - 1; i++) {
                queue[i] = queue[i + 1];
            }
            size--;
            return ans;
        }
    }

    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            T ans = (T) queue[0];
            for (int i = 0; i < size - 1; i++) {
                queue[i] = queue[i + 1];
            }
            size--;
            return ans;
        }
    }
}