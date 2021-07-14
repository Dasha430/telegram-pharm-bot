package ua.com.alevel.pharmbot.service;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ReplyMessageService {
    private static final String WARNING_EMOJI = EmojiParser.parseToUnicode(":x: cross mark");
    private static final String INSTRUCTION_EMOJI = EmojiParser.parseToUnicode(":exclamation:exclamation mark");

    private final LocaleService localeService;

    public ReplyMessageService(LocaleService localeService) {
        this.localeService = localeService;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, localeService.getMessage(replyMessage));
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage, Object... args) {
        return new SendMessage(chatId, localeService.getMessage(replyMessage, args));
    }

    public SendMessage getInstructionReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, getEmojiReplyText(replyMessage, INSTRUCTION_EMOJI));
    }

    public SendMessage getWarningReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, getEmojiReplyText(replyMessage, WARNING_EMOJI));
    }


    public String getReplyText(String replyText) {
        return localeService.getMessage(replyText);
    }

    public String getReplyText(String replyText, Object... args) {
        return localeService.getMessage(replyText, args);
    }

    public String getEmojiReplyText(String replyText, String emoji) {
        return localeService.getMessage(replyText, emoji);
    }
}
