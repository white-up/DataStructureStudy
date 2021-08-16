package queue;

import Link.Linked;

import java.util.NoSuchElementException;

public class MyQueueByLinked<T> {
    private static int maxSize = 8888;
    private Linked<T> linked;

    public MyQueueByLinked() {
        linked = new Linked<>();
    }

    /**
     * boolean add(E e)如果可以在不违反容量限制的情况下立即将指定元素插入此队列,则true在成功时返回并在当前没有可用空间时抛出IllegalStateException
     * E	element()检索但不删除此队列的头部(队列为空则抛出异常NoSuchElementException)
     * boolean offer(E e)如果可以在不违反容量限制的情况下立即将指定元素插入此队列。
     * E	peek()检索但不删除此队列的头部,如果此队列为空则返回null
     * E	poll()检索并移除此队列的头部，如果此队列为空则返回null
     * E	remove()检索并删除此队列的头部。
     */
    public T remove() {
        if (linked.isEmpty()) {
            throw new NoSuchElementException();
        }
        return pool();
    }

    public T pool() {
        if (!linked.isEmpty()) {
            T ans = linked.getHeadVal();
            linked.removeFirst();
            return ans;
        } else {
            return null;
        }
    }

    public boolean add(T t) {
        if (linked.getSize() < maxSize) {
            linked.addLast(t);
            return true;
        } else {
            throw new IllegalStateException();
            //return false;
        }
    }

    public boolean offer(T t) {
        if (linked.getSize() < maxSize) {
            linked.addLast(t);
            return true;
        } else {
            return false;
        }
    }

    public T peek() {
        return linked.getHeadVal();
    }

    public T element() {
        if (linked.isEmpty()) {
            throw new NoSuchElementException();
        }
        return linked.getHeadVal();
    }
}