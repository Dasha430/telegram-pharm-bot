package ua.com.alevel.service;


public class FindShortestPathUtil {

    public static int find(int citiesNumber, int begin, int end, int[][] sumMatrix) {
        int[] minDistance = new int[citiesNumber];
        int[] markedVertexes = new int[citiesNumber];
        final int INFINITY = Integer.MAX_VALUE;

        for (int i = 0; i < citiesNumber; i++) {
            minDistance[i] = INFINITY;
            markedVertexes[i] = 1;
        }
        minDistance[begin - 1] = 0;


        int minIndex;
        int min;

        do {
            min = INFINITY;
            minIndex = INFINITY;

            for (int i = 0; i < citiesNumber; i++) {
                if (markedVertexes[i] == 1 && minDistance[i] < min) {
                    min = minDistance[i];
                    minIndex = i;
                }
            }

            if (minIndex != INFINITY) {
                for (int i = 0; i < citiesNumber; i++) {
                    if (sumMatrix[minIndex][i] > 0) {
                        int temp = min + sumMatrix[minIndex][i];
                        if (temp < minDistance[i]) {
                            minDistance[i] = temp;
                        }
                    }
                }
                markedVertexes[minIndex] = 0;
            }

        } while (minIndex < INFINITY);
        return minDistance[end - 1];

    }
}
