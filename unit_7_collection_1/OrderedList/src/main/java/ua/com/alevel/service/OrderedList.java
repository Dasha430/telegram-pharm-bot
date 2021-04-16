package ua.com.alevel.service;

import java.util.*;

public class OrderedList<E extends Comparable> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;
    private E[] elements;

    public OrderedList() {
        elements = (E[]) new Comparable[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {

        for (Object obj : this.elements) {
            if(o.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it;
        it = new Itr();
        return it;
    }

    private class Itr implements Iterator<E> {

        protected int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size() && elements[currentIndex] != null;
        }

        @Override
        public E next() {
            return (E) elements[currentIndex++];
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {

        ListItr(int index) {
            super();
            currentIndex = index;
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex - 1 > 0 && elements[currentIndex - 1] != null;
        }

        @Override
        public E previous() {
            return (E) elements[currentIndex--];
        }

        @Override
        public int nextIndex() {
            if (this.hasNext()) {
                return currentIndex + 1;
            } else {
                return size();
            }

        }

        @Override
        public int previousIndex() {
            if (this.hasPrevious()) {
                return currentIndex - 1;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public void remove() {
            try {
                OrderedList.this.remove(currentIndex);
            } catch(IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void set(E e) {
            try {
                OrderedList.this.set(currentIndex, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }

        }

        @Override
        public void add(E e) {
            OrderedList.this.add(e);

        }
    }


    @Override
    public Object[] toArray() {
        Object[] newArray;
        newArray = new Object[size];
        if (size >= 0) System.arraycopy(elements, 0, newArray, 0, size);
            return newArray;

    }

    @Override
    public boolean add(E e) {
        if (this.isEmpty()) {
            this.elements[0] = e;
            size++;
            return true;
        }
        E[] tempArr = this.elements.clone();

        if (this.size + 1 > this.capacity) {
            this.capacity *= 2;

            this.elements = (E[]) new Comparable[capacity];
            copy(tempArr);
        }


        for (int i = 0; i < this.size; i++) {
            if ( elements[i].compareTo(e) > 0) {
                E temp = elements[i];
                elements[i] = e;
                this.size++;
                for (int j = i + 1; j < this.size; j++) {
                    elements[j] = tempArr[j - 1];
                }
                return true;

            } else if (i == size - 1) {
                elements[size] = e;
                this.size++;
                return true;
            }
        }

        return false;
    }





    private void copy(E[] toCopy) {
        for (int i = 0; i < toCopy.length; i++) {
            this.elements[i] = toCopy[i];
        }
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size)
            return Arrays.copyOf(elements, size, a.getClass());
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }



    @Override
    public boolean remove(Object o) {

        final Object[] es = this.elements;

        for (int i = 0; i < this.size; i++) {
            if (es[i].equals(o)) {
                fastRemove(es, i);
                return true;
            }
        }

        return false;

    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        size = newSize;
        es[size] = null;
    }

    @Override
    public boolean addAll(Collection c) {
       return this.addAll(0, c);

    }

    @Override
    public boolean addAll(int index, Collection c) {

        try {
            Object[] arr = c.toArray();

            for (int i = index; i < arr.length; i++) {
                this.add((E) arr[i]);
            }
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;

    }

    @Override
    public E get(int index) {
        return (E) this.elements[index];
    }

    @Deprecated
    @Override
    public E set(int index, E element) {
        this.remove(index);
        this.add(element);
        return null;
    }

    @Deprecated
    @Override
    public void add(int index, E element) {
        this.add(element);

    }



    @Override
    public E remove(int index) {
        if (index > this.size) {
            throw new RuntimeException("index out of range");
        }
        final Object[] es = elements;

        E oldValue = (E) es[index];
        fastRemove(es, index);

        return oldValue;
    }


    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = this.indexOf(o);
        for (int i = 0; i < this.size; i++) {
            if (elements[i].equals(o)) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    @Override
    public ListIterator listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator listIterator(int index) {
        return new ListItr(index);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        List <E> subList = new OrderedList<E>();

        if (fromIndex > size || toIndex > size) {
            throw new RuntimeException("index out of range");
        }
        if (fromIndex > toIndex) {
            throw new RuntimeException("first index should be smaller");
        }
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(this.elements[i]);
        }
        return subList;
    }

    @Override
    public boolean retainAll(Collection c) {
        List<E> es = new OrderedList<>();
        es.addAll(c);
        int newSize = size;
        if (c.stream().allMatch(obj -> obj.getClass().equals(elements[0].getClass()))) {

            int index = -1;
            for(int i = 0; i < this.size(); i++) {
                index = es.indexOf(this.get(i));
                if (index == -1) {
                    this.remove(i);
                    newSize--;
                }
            }
            size = newSize;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        Object[] es = c.toArray();
        int newSize = size;
        if (c.stream().allMatch(obj -> obj.getClass().equals(elements[0].getClass()))) {

            int index = -1;
            for (int i = 0; i < es.length; i++) {
                index = this.indexOf(es[i]);
                if (index != -1) {
                    this.remove(index);
                    newSize--;
                }
            }
            size = newSize;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        Object[] es = c.toArray();
        if (c.stream().allMatch(obj -> obj.getClass().equals(elements[0].getClass()))) {

            int index = -1;
            for(int i = 0; i < es.length; i++) {
                index = this.indexOf(es[i]);
                if (index == -1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
