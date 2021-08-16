package Link;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        /**API
         boolean add(E e)将指定元素添加到此列表的结尾。
         void add(int index, E element) 在此列表中指定的位置(0开始)插入指定的元素(发生越界抛出IndexOutOfBoundsException异常)
         boolean addAll(Collection<? extends E> c) 添加指定 collection中的所有元素到此列表的结尾,顺序是指定 collection 的迭代器返回这些元素的顺序
         boolean addAll(int index, Collection<? extends E> c)将指定 collection 中的所有元素从指定位置开始插入此列表
         void addFirst(E e)将指定元素插入此列表的开头
         void addLast(E e) 将指定元素添加到此列表的结尾
         void clear() 从此列表中移除所有元素
         Object clone()返回此LinkedList的浅表副本(浅拷贝)
         boolean contains(Object o)如果此列表包含指定元素则返回 true。
         Iterator<E> descendingIterator()返回以逆向顺序在此双端队列的元素上进行迭代的迭代器。
         E element()获取但不移除此列表的第一个元素
         E get(int index) 返回此列表中指定位置处的元素。
         E getFirst()返回此列表的第一个元素。
         E getLast()返回此列表的最后一个元素。
         int indexOf(Object o)返回此列表中首次出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1。
         int lastIndexOf(Object o)返回此列表中最后出现的指定元素的索引，如果此列表中不包含该元素，则返回 -1。
         ListIterator<E> listIterator(int index)返回此列表中的元素的列表迭代器（按适当顺序）,从列表中指定位置开始。
         boolean offer(E e)将指定元素添加到此列表的末尾（最后一个元素）。
         boolean offerFirst(E e)在此列表的开头插入指定的元素。
         boolean offerLast(E e)在此列表末尾插入指定的元素。
         E peek()获取但不移除此列表的头（第一个元素）。
         E peekFirst()获取但不移除此列表的第一个元素；如果此列表为空，则返回 null。
         E peekLast()获取但不移除此列表的最后一个元素；如果此列表为空，则返回 null。
         E poll()获取并移除此列表的头（第一个元素）
         E pollFirst()获取并移除此列表的第一个元素；如果此列表为空，则返回 null。
         E pollLast()获取并移除此列表的最后一个元素；如果此列表为空，则返回 null。
         E pop()从此列表所表示的堆栈处弹出一个元素。
         void push(E e)将元素推入此列表所表示的堆栈。
         E remove()获取并移除此列表的头（第一个元素）。
         E remove(int index)移除此列表中指定位置处的元素。
         boolean remove(Object o)从此列表中移除首次出现的指定元素（如果存在）。
         E removeFirst()移除并返回此列表的第一个元素。
         boolean removeFirstOccurrence(Object o)从此列表中移除第一次出现的指定元素（从头部到尾部遍历列表时）。
         E removeLast()移除并返回此列表的最后一个元素。
         boolean removeLastOccurrence(Object o)从此列表中移除最后一次出现的指定元素（从头部到尾部遍历列表时）。
         E set(int index, E element)将此列表中指定位置的元素替换为指定的元素。
         int size()返回此列表的元素数。
         Object[] toArray()返回以适当顺序（从第一个元素到最后一个元素）包含此列表中所有元素的数组。
         <T> T[]toArray(T[] a)返回以适当顺序（从第一个元素到最后一个元素）包含此列表中所有元素的数组；返回数组的运行时类型为指定数组的类型。
         List<E> subList(int fromIndex, int toIndex)切割原链表生成对应角标下的新链表
         */
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("test1");
        //构造空链表
        LinkedList<String> stringLinkedList = new LinkedList<>();
        //LinkedList(Collection<? extends E> c)构造一个包含指定collection中的元素的列表,这些元素按其collection的迭代器返回的顺序排列
        LinkedList<String> stringLinkedList1 = new LinkedList<>(stringArrayList);
        //添加
        stringLinkedList.add(0, "test0");
        stringLinkedList.addAll(0, stringArrayList);
        //生成
        LinkedList<String> stringLinkedListClone = (LinkedList<String>) stringLinkedList.clone();
        //System.out.println(System.identityHashCode());
        System.out.println(System.identityHashCode(stringLinkedListClone.getFirst()));
        System.out.println(System.identityHashCode(stringLinkedList.getFirst()));
        System.out.println();

        /*var subList=stringLinkedListClone.subList(0,1);
        //遍历
        var iterator= stringLinkedList.descendingIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
    }
}