package ua.com.alevel.pharmbot.cache;

import org.springframework.stereotype.Service;
import ua.com.alevel.pharmbot.bot.state.PharmBotState;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDataCache implements DataCache{

    private Map<Long, PharmBotState> usersBotStates = new HashMap<>();
    @Override
    public void setUsersCurrentBotState(long chatId, PharmBotState botState) {
        usersBotStates.put(chatId, botState);
    }

    @Override
    public PharmBotState getUsersCurrentBotState(long userId) {
        return usersBotStates.get(userId);
    }
}
