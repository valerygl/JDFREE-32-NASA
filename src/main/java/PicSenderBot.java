import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class PicSenderBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;


    // теперь программа работает не только с одним ботом, но с любым другим ботом (имя+токен), которого ей передадут при вызове
    public PicSenderBot(String BOT_NAME, String BOT_TOKEN) throws TelegramApiException {
        this.BOT_NAME = BOT_NAME;
        this.BOT_TOKEN = BOT_TOKEN;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }


    @Override
    public void onUpdateReceived( Update update) {


        if (update.hasMessage() && update.getMessage().hasText()) {

            long chatId = update.getMessage().getChatId();
            String textFromUser = update.getMessage().getText();

            switch (textFromUser) {
                case "/start":
                    sendMessage(chatId, "Привет, я бот. Здесь есть команды /help и /image");
                    break;

                case "/help":
                    sendMessage(chatId, "Используй /image , чтобы получить картинку из NASA");
                    break;

                case "/image":
                    try {
                        sendMessage(chatId, UtilsGetURLPictureOfTheDay.getPicURL());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                default:
                    sendMessage(chatId, "Нет такой команды. Используй /image , чтобы получить картинку из NASA");
            }
        }
    }


    private void sendMessage(long chatId, String incomingText) {

        SendMessage msg = new SendMessage(); // Create a SendMessage object with mandatory fields
        msg.setChatId(chatId); // задаем, в какой чат будем отвечать (в тот же, откуда нам написали)
        msg.setText(incomingText);

        try {
            execute(msg ); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
