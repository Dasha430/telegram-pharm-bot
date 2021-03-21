package ua.com.alevel.util;

import java.util.List;

public class ChessTaskUtil {
    public static boolean checkSfPossible(List<Integer> currentPosition, List<Integer> checkingPosition) {
        if (Math.abs(currentPosition.get(0) - checkingPosition.get(0)) == 2
                && Math.abs(currentPosition.get(1) - checkingPosition.get(1)) == 1
                || Math.abs(currentPosition.get(0) - checkingPosition.get(0)) == 1
                && Math.abs(currentPosition.get(1) - checkingPosition.get(1)) == 2 ) {
            return true;
        }
        return false;
    }
}
