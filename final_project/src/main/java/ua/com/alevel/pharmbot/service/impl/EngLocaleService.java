package ua.com.alevel.pharmbot.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ua.com.alevel.pharmbot.service.LocaleService;

import java.util.Locale;

@Service
public class EngLocaleService implements LocaleService {

    private final Locale locale = Locale.UK;
    private final MessageSource source;

    public EngLocaleService(MessageSource source) {
        this.source = source;
    }

    @Override
    public String getMessage(String message) {
        return this.source.getMessage(message, null, locale);
    }

    @Override
    public String getMessage(String message, Object... args) {
        return this.source.getMessage(message, args, locale);
    }
}
