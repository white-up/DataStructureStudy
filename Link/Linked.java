package Link;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class Linked<T> implements Cloneable {
    private Node head;//头结点
    private int size; //链表元素个数

    //private Itr itr;
    //构造函数
    public Linked() {
        this.head = null;
        this.size = 0;
        //itr=new Itr();
    }

    // 重写hashcode方法
    @Override
    public int hashCode() {
        if (head != null) {
            return head.hashCode();
        } else {
            return 0;
        }
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public T getHeadVal() {
        if (!this.isEmpty()) {
            return head.t;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Linked)) {
            return false;
        } else {
            Linked<T> linked = (Linked<T>) obj;
            return linked.getHead() == head && linked.hashCode() == this.hashCode();
        }

    }


    public Itr getItr() {
        return new Itr();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Linked<T> ans = (Linked<T>) super.clone();
        ans.head = (Node) this.head.clone();
        return ans;
    }

    @Override
    public String toString() {
        Itr itr = getItr();
        if (itr.hasNext()) {
            String ans = "[" + itr.next();
            while (itr.hasNext()) {
                ans += "," + itr.next();
            }
            return ans + "]";
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //链表头部添加元素
    public void addFirst(T t) {
        Node node = new Node(t);    //节点对象
        node.next = this.head;
        this.head = node;
        //this.head = new Node(e,head);等价上述代码
        this.size++;
    }

    //向链表尾部插入元素
    public void addLast(T t) {
        this.add(t, this.size);
    }
    /*@Override//浅拷贝
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }*/

    //向链表中间插入元素
    public void add(T t, int index) {
        //过滤错误角坐标
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is error");
        }
        //头添加元素
        if (index == 0) {
            this.addFirst(t);
            return;
        }
        Node preNode = this.head;
        //找到要插入节点的前一个节点
        for (int i = 0; i < index - 1; i++) {
            preNode = preNode.next;
        }
        Node node = new Node(t);
        //要插入的节点的下一个节点指向preNode节点的下一个节点
        node.next = preNode.next;
        //preNode的下一个节点指向要插入节点node
        preNode.next = node;
        this.size++;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        var iterator = c.iterator();
        //过滤错误角坐标
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is error");
        }
        Node startNode = switchNode(c);
        Node endNode = startNode;
        while (endNode.next != null) {
            endNode = endNode.next;
        }
        //头添加元素
        if (index == 0) {
            endNode.next = this.head;
            this.head = startNode;
            this.size += c.size();
        }
        Node preNode = this.head;
        //找到要插入节点的前一个节点
        for (int i = 0; i < index - 1; i++) {
            preNode = preNode.next;
        }
        endNode.next = preNode.next;
        preNode.next = startNode;
        return true;
    }

    public Node switchNode(Collection<? extends T> c) {
        if (c == null || c.size() == 0) {
            return null;
        }
        var iterator = c.iterator();
        Node newNode = new Node(iterator.next());
        Node newNodeMove = newNode;
        while (iterator.hasNext()) {
            Node temp = new Node(iterator.next());
            newNodeMove.next = temp;
            newNodeMove = newNodeMove.next;
        }
        return newNode;
    }

    public T getLast() {
        Node cur = this.head;    //记录当前结点
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur.t;
    }

    //删除链表元素
    public void remove(T t) {
        if (head == null) {
            System.out.println("无元素可删除");
            return;
        }
        //要删除的元素与头结点的元素相同
        while (head != null && head.t.equals(t)) {
            head = head.next;
            this.size--;
        }
        /**
         * 上面已经对头节点判别是否要进行删除
         * 所以要对头结点的下一个结点进行判别
         */
        Node cur = this.head;
        while (cur != null && cur.next != null) {
            if (cur.next.t.equals(t)) {
                this.size--;
                cur.next = cur.next.next;
            } else cur = cur.next;
        }

    }

    //删除链表第一个元素
    public T removeFirst() {
        if (this.head == null) {
            System.out.println("无元素可删除");
            return null;
        }
        Node delNode = this.head;
        this.head = this.head.next;
        delNode.next = null;
        this.size--;
        return delNode.t;
    }

    //删除链表的最后一个元素
    public T removeLast() {
        if (this.head == null) {
            System.out.println("无元素可删除");
            return null;
        }
        //只有一个元素
        if (this.getSize() == 1) {
            return this.removeFirst();
        }
        Node cur = this.head;    //记录当前结点
        Node pre = this.head;    //记录要删除结点的前一个结点
        while (cur.next != null) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = null;
        this.size--;
        return cur.t;
    }

    //加入虚拟头结点的链表进行删除
    public void removeElt(T t) {
        //构造虚拟头结点，并且下一个结点指向head
        Node dummy = new Node(t, this.head);
        //声明结点指向虚拟头结点
        Node cur = dummy;
        //从虚拟头结点的下一个结点开始遍历
        while (cur.next != null) {
            if (cur.next.t.equals(t)) {
                cur.next = cur.next.next;
                this.size--;
            } else cur = cur.next;
        }
        //去除虚拟头结点
        this.head = dummy.next;
    }

    /**
     * @param t:插入在t元素的位置
     * @param des：要插入的元素
     */
    public void insert(T t, T des) {
        //构造虚拟头结点，并且下一个结点指向head
        Node dummy = new Node(null, this.head);
        //构造要插入的结点
        Node dNode = new Node(des);
        //声明变量cur指向虚拟头结点
        Node cur = dummy;
        while (cur.next != null) {
            if (cur.next.t.equals(t)) {
                dNode.next = cur.next;
                cur.next = dNode;
                this.size++;
                break;
            } else cur = cur.next;
        }
        this.head = dummy.next;
    }

    //链表中是否包含某个元素
    public boolean contains(T t) {
        Node cur = this.head;
        while (cur != null) {
            if (cur.t.equals(t)) {
                return true;
            } else cur = cur.next;
        }
        return false;
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public int search(Object t) {
        Node cur = this.head;
        int i = 1;
        while (cur != null) {
            if (cur.t.equals(t)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    <T> T[] toArray(T[] a) {
        Node moveNode = this.head;
        if (a.length < this.size) {
            return null;
        }
        for (int i = 0; i < this.size; i++) {
            a[i] = (T) moveNode.t;
            moveNode = moveNode.next;
        }
        return a;
    }

    public class Itr implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return !(size == 0);
        }

        @Override
        public T next() {
            T ans = null;
            if (hasNext()) {
                ans = head.t;
                remove();
            }
            return ans;
        }

        @Override
        public void remove() {
            if (this.hasNext()) {
                head = head.next;
                size--;
            }
            //Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private class Node implements Cloneable {

        private T t;
        private Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node(T t) {
            this(t, null);
        }


        @Override
        public int hashCode() {
            int ans1 = 0, ans2 = 0;

            if (t != null) {
                ans1 = t.hashCode();
            }
            if (next != null) {
                ans2 = next.hashCode();
            }
            return ans1 + 17 * ans2;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Node ans = (Node) super.clone();
            try {
                ans.t = (T) this.t.getClass().getDeclaredConstructor().newInstance();
                ans.next = (Node) this.next.clone();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } finally {
                return ans;
            }
        }
    }

}