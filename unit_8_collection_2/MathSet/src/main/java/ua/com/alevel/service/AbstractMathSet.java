package ua.com.alevel.service;

import java.util.HashSet;
import java.util.Set;

public interface AbstractMathSet<Number> {


    void add(Number n);
    void add(Number ... n);
    void join (AbstractMathSet ms);
    void join (AbstractMathSet ... ms);
    void sortDesc();
    void sortDesc(int firstIndex, int lastIndex);
    void sortDesc(Number value);
    void sortAsc();
    void sortAsc(int firstIndex, int lastIndex);
    void sortAsc(Number value);
    Number get(int index);
    Number getMax();
    Number getMin();
    Number getAverage();
    Number getMedian();
    <N extends Number> N[]toArray();
    Number[] toArray(int firstIndex, int lastIndex);
    AbstractMathSet squash(int firstIndex, int lastIndex);
    void clear();
    void clear(Number[] numbers);

    Set<Integer> set = new HashSet<>();

}
