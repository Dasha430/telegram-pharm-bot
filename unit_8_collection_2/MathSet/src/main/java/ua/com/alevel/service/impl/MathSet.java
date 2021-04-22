package ua.com.alevel.service.impl;

import ua.com.alevel.service.AbstractMathSet;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;


public class MathSet< Number> implements AbstractMathSet<Number> {

    private Object[] elements;
    private int size;
    private int capacity;
    private final int INITIAL_CAPACITY = 16;

    public MathSet() {
        capacity = INITIAL_CAPACITY;
        elements = new Object[capacity];
        size = 0;
    }

    public MathSet (int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        size = 0;
    }
    public MathSet (Number[] numbers) {
        this.size = numbers.length;
        this.capacity = INITIAL_CAPACITY;
        updateCapacityIfNeeded();
        elements = new Object[capacity];
        copyElements(numbers);
    }
    private void copyElements(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            this.elements[i] =  numbers[i];
        }
    }

    private void updateCapacityIfNeeded() {
        if (size > capacity) {
            capacity *= 2;

        }
    }
    public MathSet (Number[] ... numbers) {
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;

        for (Number[] nums: numbers) {
            int oldSize = size;
            size += nums.length;
            updateCapacityIfNeeded();
            Object[] temp = new Object[oldSize];
            if (elements != null) {
                temp = elements.clone();
            }
            elements = new Object[capacity];
            if (temp != null) {
                copyElements((Number[])temp);
            }
            append(nums, oldSize);
        }

    }

    public MathSet (MathSet numbers) {
        this.capacity = numbers.capacity;
        this.size = numbers.size;
        elements = new Object[capacity];
        copyElements((Number[]) numbers.elements);

    }

    public MathSet (MathSet ... numbers) {
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
        for (MathSet ms: numbers) {

            int oldSize = size;
            size += ms.size;
            updateCapacityIfNeeded();
            Object[] temp = new Object[oldSize];
            if (elements != null) {
                temp = elements.clone();
            }
            elements = new Object[capacity];
            if (temp != null) {
                copyElements((Number[])temp);
            }
            append((Number[]) ms.elements, oldSize);
        }

    }

    private void append(Number[] numbers, int oldSize) {


        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null) {

                this.elements[oldSize + i] = numbers[i];
            }
        }
    }

    @Override
    public void add(Number n) {

        if (this.size + 1 > this.capacity) {

            this.capacity *= 2;
            Object[] tempArr = this.elements.clone();
            this.elements = new Object[capacity];
            copyElements((Number[])tempArr);;
        }

        if (n != null) {

            this.elements[size] = n;
            size++;

        }
    }

    @Override
    public void add(Number... n) {
        for (Number num: n) {
            this.add(num);
        }

    }

    @Override
    public void join(AbstractMathSet ms) {
        MathSet another = (MathSet) ms;
        Number[] anotherElements = (Number[]) another.elements.clone();
        for (Number n: anotherElements) {
            this.add(n);
        }

    }

    @Override
    public void join(AbstractMathSet... ms) {
        for (AbstractMathSet ams: ms) {
            this.join(ams);
        }

    }

    private class NumberComparator implements Comparator<Number> {

        @Override
        public int compare(Number o1, Number o2) {
            return new BigDecimal(o1.toString()).compareTo(new BigDecimal(o2.toString()));
        }
    }

     private <E extends Number> void quickSort(E[] array, int low, int high) {
        NumberComparator nc = new NumberComparator();
         if (array.length == 0)
             return;

         if (low >= high)
             return;

         int middle = low + (high - low) / 2;
         E base = array[middle];

         int i = low, j = high;
         while (i <= j) {
             while (nc.compare(array[i], base) < 0) {
                 i++;
             }

             while (nc.compare(array[j], base) > 0) {
                 j--;
             }

             if (i <= j) {
                 E temp = array[i];
                 array[i] = array[j];
                 array[j] = temp;
                 i++;
                 j--;
             }
         }

         if (low < j)
             quickSort(array, low, j);

         if (high > i)
             quickSort(array, i, high);
    }

   private void reverse() {
        Object[] temp = this.elements.clone();
        int count = 0;
       for (int i = size - 1;  i >= 0 ; i--) {
           this.elements[count] = temp[i];
           count++;
       }
   }

   private int findIndex(Number value) {
       for (int i = 0; i < size; i++) {
           if (this.elements[i].equals(value)) {
               return i;
           }
       }
       throw new RuntimeException("no such element");
   }

    @Override
    public void sortDesc() {
        quickSort((Number[])this.elements, 0, size - 1);
        this.reverse();

    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        quickSort((Number[])this.elements, firstIndex, lastIndex);
        this.reverse();
    }

    @Override
    public void sortDesc(Number value) {
        int index = this.findIndex(value);
        quickSort((Number[])this.elements, index, size - 1);
        this.reverse();
    }

    @Override
    public void sortAsc() {
        this.quickSort((Number[])this.elements, 0, size - 1);

    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        if (firstIndex > size || lastIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        this.quickSort((Number[])this.elements, firstIndex, lastIndex);
    }

    @Override
    public void sortAsc(Number value) {
        int index = this.findIndex(value);
        quickSort((Number[])this.elements, index, size - 1);

    }

    @Override
    public Number get(int index) {
        if (index < size) {
            return (Number)this.elements[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Number getMax() {
        NumberComparator nc = new NumberComparator();
        Number max = (Number)elements[0];
        for (int i = 0; i < size; i++) {
            if (nc.compare(max, (Number)elements[i] ) < 0) {
                max = (Number)elements[i];
            }
        }
        return max;
    }

    @Override
    public Number getMin() {
        NumberComparator nc = new NumberComparator();
        Number min = (Number)elements[0];
        for (int i = 0; i < size; i++) {
            if (nc.compare(min, (Number)elements[i] ) > 0) {
                min = (Number)elements[i];
            }
        }
        return min;
    }

    @Override
    public Number getAverage() {
        Number sum = (Number)elements[0];
        for (int i = 1; i < size; i++) {
            sum = addNumbers(sum, (Number)elements[i]);

        }
        Integer s = size;

        return divideNumbers(sum,(Number) s);
    }

    private <E extends Number>Number addNumbers(E a, E b) {

        if (a instanceof Double || b instanceof Double) {
            Double aDouble = (Double) a;
            Double bDouble = (Double) b;
            Double res = aDouble + bDouble;
            return (Number) res;
        } else if (a instanceof Float || b instanceof Float) {
            Float aFloat = (Float) a;
            Float bFloat = (Float) b;
            Float res = aFloat + bFloat;
            return (Number) res;
        } else {
            Integer aInt = (Integer)a;
            Integer bInt = (Integer)b;
            Integer res = aInt + bInt;
            return (Number) res;
        }

    }

    private <E extends Number, N extends Number> Number divideNumbers(E a, N b) {

        if (a instanceof Double) {

            Double res = Double.parseDouble(a.toString()) / Double.parseDouble(b.toString());
            return (E) res;
        }
         else if (a instanceof Float) {

            Float res = Float.parseFloat(a.toString()) / Float.parseFloat(b.toString());
            return (E) res;
        } else {

            BigInteger c = new  BigInteger(a.toString()).divide(new BigInteger(b.toString()));
            Integer res = Integer.parseInt(c.toString());
            return (E) res;
        }
    }

    @Override
    public Number getMedian() {

        Number[] nums = (Number[])this.elements.clone();
        quickSort(nums, 0, size - 1);
        int middle;
        if (size % 2 != 0) {

            return nums[(size - 1) / 2];
        }
        Integer divider = 2;
        return divideNumbers(addNumbers(nums[size / 2],nums[size / 2 - 1]), (Number) divider);
    }

    @Override
    public  Number[] toArray() {
        Number[] newArr =  (Number[])new Object[size];
        if (size >= 0) {
            int count = 0;
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] != null) {
                    newArr[count] = (Number) elements[i];
                    count++;
                }
            }
        }
        return newArr;
    }

    @Override
    public Number[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex > size || lastIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        Number[] result = (Number[])new Object[lastIndex - firstIndex + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = (Number)elements[firstIndex + i];
        }
        return result;
    }

    @Override
    public AbstractMathSet squash(int firstIndex, int lastIndex) {
        Number[] newElements =  (Number[]) new Object[size - (lastIndex - firstIndex + 1)];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (i >= firstIndex && i <= lastIndex) {
                continue;
            }
            newElements[count] = (Number)elements[i];
            count++;
        }
        return new MathSet((newElements));
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;

    }
    private boolean contains(Number n) {
        for (int i = 0; i < elements.length; i++) {
            if (n.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                if (numbers[i].equals(elements[j])) {
                    fastRemove(elements, j);
                }
            }


        }
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        size = newSize;
        es[size] = null;
    }

    @Override
    public String toString() {
        return "{" +
                "elements= " + Arrays.toString(elements) +
                '}';
    }
}
