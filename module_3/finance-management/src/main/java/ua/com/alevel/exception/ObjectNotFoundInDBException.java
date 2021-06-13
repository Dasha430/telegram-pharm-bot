package ua.com.alevel.exception;

public class ObjectNotFoundInDBException extends Exception{
    public ObjectNotFoundInDBException(Class<?> c, Long id) {
        super("instance of " + c.getName() + " with id = " + id + " wasn't found");
    }
}
