package ua.com.alevel.pharmbot.service;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
public class ReplyMessageService {
    private static final String WARNING_EMOJI = EmojiParser.parseToUnicode(":x:");
    private static final String INSTRUCTION_EMOJI = EmojiParser.parseToUnicode(":exclamation:");

    private final LocaleService localeService;

    public ReplyMessageService(LocaleService localeService) {
        this.localeService = localeService;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, localeService.getMessage(replyMessage));
    }

    public SendMessage getReplyMessageWithMarkup(long chatId, String replyMessage, InlineKeyboardMarkup markup) {
        return new SendMessage(chatId, replyMessage).setReplyMarkup(markup);
    }

    public SendMessage getWarningReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, getEmojiReplyText(replyMessage, WARNING_EMOJI));
    }


    public String getReplyText(String replyText) {
        return localeService.getMessage(replyText);
    }

    public String getEmojiReplyText(String replyText, String emoji) {
        return localeService.getMessage(replyText, emoji);
    }
}
