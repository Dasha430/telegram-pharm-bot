package ua.com.alevel.pharmbot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ua.com.alevel.pharmbot.bot.state.PharmBotState;
import ua.com.alevel.pharmbot.cache.UserDataCache;
import ua.com.alevel.pharmbot.service.ReplyMessageService;
import ua.com.alevel.pharmbot.service.SearchHistoryService;

import java.util.List;

@Component
public class SearchHistoryCommandHandler implements MessageHandler {

    private UserDataCache cache;
    private SearchHistoryService searchService;
    private ReplyMessageService replyService;

    public SearchHistoryCommandHandler(UserDataCache cache,
                                       SearchHistoryService searchService,
                                       ReplyMessageService replyService) {
        this.cache = cache;
        this.searchService = searchService;
        this.replyService = replyService;
    }

    @Override
    public SendMessage handle(Message message) {
        Long chatId = message.getChatId();
        StringBuffer reply = new StringBuffer("");

        if (cache.getUsersCurrentBotState(message.getChatId()).equals(PharmBotState.SEARCH_HISTORY_REQUEST)) {
            reply.append("Your search history:\n ");
            List<String> results = searchService.getHistory(chatId);
            for (String r: results) {
                reply.append(r + "\n");
            }

        }

        return replyService.getReplyMessage(chatId, reply.toString());
    }

    @Override
    public PharmBotState getName() {
        return PharmBotState.SEARCH_HISTORY_REQUEST;
    }
}
