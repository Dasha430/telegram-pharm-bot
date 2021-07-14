package ua.com.alevel.pharmbot.cache;

import ua.com.alevel.pharmbot.bot.state.PharmBotState;

public interface DataCache {
    void setUsersCurrentBotState(long userId, PharmBotState botState);

    PharmBotState getUsersCurrentBotState(long userId);
}
