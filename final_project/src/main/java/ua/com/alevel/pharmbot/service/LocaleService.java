package ua.com.alevel.pharmbot.service;

public interface LocaleService {

    String getMessage(String message);
    String getMessage(String message, Object... args);
}
