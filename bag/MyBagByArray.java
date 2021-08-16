package bag;


import java.util.Arrays;
import java.util.Iterator;

public class MyBagByArray<T> implements Iterable<T> {
    private static final int DEFAULT_SIZE = 16;//默认容器大小
    private Object[] table; //背包容器内部的数据结构，是一个对象的数组。便于接受任意类型(向上转型)
    private Object[] bag;
    private int size;//元素个数

    public MyBagByArray() {
        bag = new Object[DEFAULT_SIZE];
    }

    //添加元素
    public void add(T t) {
        expendCapacity();//在新增之前进行容器
        table[size++] = t;
    }

    public void expendCapacity() {
        int len = bag.length;
        if (size >= len) {
            int newCapacity = len + (len >> 1);
            //max=max*3/2+1;
            bag = Arrays.copyOf(bag, newCapacity);
        }
    }


    //判断元素是否为空

    public boolean isEmpty() {
        return size == 0;

    }

    //获取元素的数量

    public int size() {
        return size;
    }

    public T get(int i) {
        assert i < 0 || i >= size;
        return (T) bag[i];
    }

    //因为要实现迭代功能，在java中必须要实现Iterable接口，实现iterator()方法，返回一个可迭代的元素(实现了Iterator接口，hasNext() 方法和next()方法，java语法糖知识)

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private int cursor; //cursor 进行迭代 上下指针移动

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public T next() {
            int i = cursor;
            cursor++;
            return get(i);
        }

    }

}