package ua.com.alevel;

import org.junit.jupiter.api.Test;
import ua.com.alevel.service.AbstractMathSet;
import ua.com.alevel.service.impl.MathSet;

public class MathSetTest {

    @Test
    public void test (){
        AbstractMathSet<Integer> ms1 = new MathSet<>();
        AbstractMathSet<Integer> ms2 = new MathSet<>(10);
        Integer[] arr = {12, 6, 9, 7, 4, 37};
        Integer[] arr2 = {23, -5, 13, 0, -4, 7};
        AbstractMathSet<Integer> ms3 = new MathSet<>(arr);
        AbstractMathSet<Integer> ms4 = new MathSet<>(arr, arr2);
        AbstractMathSet<Integer> ms5 = new MathSet<>((MathSet) ms3);
        AbstractMathSet<Integer> ms6 = new MathSet<>( (MathSet) ms3, (MathSet) ms1);

        ms1.add(17);
        ms1.add(-5);

        ms4.add(arr2);
        ms5.join(ms6);
        ms3.join(ms1, ms2, ms6);
        ms3.sortAsc();
        ms3.sortDesc();
        ms5.sortAsc(2, 6);

        int min = ms4.getMin();
        int max = ms4.getMax();
        double avg = ms3.getAverage();
        int med = ms4.getMedian();

        Object[]array = ms4.toArray();
        Object[] array2 = ms4.toArray(1, 4);


        AbstractMathSet<Integer> ms7 = ms6.squash(2, 5);
        ms1.clear();
        ms2.clear();
        ms3.clear(arr);
        ms4.clear();
        ms5.clear();
        ms6.clear();
        ms7.clear();

        Float[] arrF = {1.123f, 1.09f };
        Float[] arrF2 = {1.123f};

        AbstractMathSet<Float> msF = new MathSet<>(arrF);
        float a = msF.getAverage();
        msF.clear(arrF2);

    }
}
