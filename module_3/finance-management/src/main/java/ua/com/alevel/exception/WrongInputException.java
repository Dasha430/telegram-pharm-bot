package ua.com.alevel.exception;

public class WrongInputException extends Exception {

    public WrongInputException(String expected, String got) {
        super("Wrong input, expected: " +  expected + ", got: " + got);
    }
}
