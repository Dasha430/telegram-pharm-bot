package ua.com.alevel;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input lesson number (from 1 to 10):" );
        int lessonNum = sc.nextInt();
        System.out.printf("%nLesson number %d ends at ", lessonNum );
        for (int t: getLessonEndTime(lessonNum)) {
            System.out.print(t + " ");
        }

    }
    public static int[] getLessonEndTime(int num) {
        int shortBreaks = num / 2 * 5;
        int longBreaks = (num - 1) / 2 * 15;
        int lessons = num * 45;
        int[] time = { 9 + (lessons + shortBreaks + longBreaks) / 60, (lessons + shortBreaks + longBreaks) % 60 };
        return time;
    }
}
