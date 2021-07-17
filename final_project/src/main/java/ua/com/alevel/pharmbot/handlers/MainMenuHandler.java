package ua.com.alevel.pharmbot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ua.com.alevel.pharmbot.bot.state.PharmBotState;
import ua.com.alevel.pharmbot.service.MainMenuService;
import ua.com.alevel.pharmbot.service.ReplyMessageService;
import ua.com.alevel.templates.MessageTemplates;

@Component
public class MainMenuHandler implements MessageHandler{

    private MainMenuService menuService;
    private ReplyMessageService replyService;

    public MainMenuHandler(MainMenuService menuService, ReplyMessageService replyService) {
        this.menuService = menuService;
        this.replyService = replyService;
    }

    @Override
    public SendMessage handle(Message message) {
        return menuService.getMainMenuMessage(message.getChatId(), replyService.getReplyText("welcome"));
    }

    @Override
    public PharmBotState getName() {
        return PharmBotState.SHOW_MAIN_MENU;
    }
}
