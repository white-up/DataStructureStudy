package queue;

import Link.Linked;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;


public class PriorityLinkedList<T> {
    protected LinkedList[] linkedLists;
    protected int priorities;
    protected int size;
    private Linked<T>[] linkedS;
    private int priority;

    public PriorityLinkedList(int priority) {
        this.priority = priority;
        structure();
    }

    public Itr getItr() {
        return new Itr();
    }

    private void structure() {
        linkedS = new Linked[priority];
        for (int i = 0; i < priority; i++) {
            linkedS[i] = new Linked();
        }
        size = 0;
    }

    public void addFirst(T t, int priority) {
        linkedS[priority].addFirst(t);
        size++;
    }

    public void addLast(T t, int priority) {
        linkedS[priority].addFirst(t);
        size++;
    }

    public T removeFirst() {
        T t = null;
        for (int i = priority - 1; i >= 0; i--) {
            Linked temp = linkedS[i];
            if (!temp.isEmpty()) {
                t = (T) temp.removeFirst();
                break;
            }
        }
        if (t != null) {
            size--;
        }
        return t;
    }

    public T removeLast() {
        T t = null;
        for (int i = 0; i < priority; i++) {
            Linked temp = linkedS[i];
            if (!temp.isEmpty()) {
                t = (T) temp.removeFirst();
                break;
            }
        }
        if (t != null) {
            size--;
        }
        return t;
    }


    public T peekFirst() {
        T t = null;
        for (int i = priority - 1; i >= 0; i--) {
            Linked temp = linkedS[i];
            if (!temp.isEmpty()) {
                t = (T) temp.removeFirst();

            }
            if (t != null) {
                break;
            }
        }

        return t;
    }


    public List getAll() {
        List ans = new ArrayList();
        for (int i = priority - 1; i >= 0; i--) {
            Linked linked = linkedS[i];
            var it = linked.getItr();
            while (it.hasNext()) {
                ans.add(it.next());
            }
        }
        return ans;
    }


    public void clear() {
        structure();
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    class Itr implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public T next() {
            return removeFirst();
        }

        @Override
        public void remove() {
            removeFirst();
            //Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }
    }


}