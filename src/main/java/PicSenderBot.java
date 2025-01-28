import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PicSenderBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived( Update update) {

//        /start
//        /help
//        /image
//        /date 2025-01-28


        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage replyMessage = new SendMessage(); // Create a SendMessage object with mandatory fields
            long chatId = update.getMessage().getChatId(); // узнаём, откуда нам писали
            replyMessage.setChatId(chatId); // задаем, в какой чат будем отвечать (в тот же, откуда нам написали)
            String origText = update.getMessage().getText(); // вытащим текст из оригинального сообщения
            replyMessage.setText("Вы нам писали, что " + origText);


//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText(update.getMessage().getText());

            try {
                execute(replyMessage ); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "pic_sender";
    }

    @Override
    public String getBotToken() {
        return "7919335706:AAG9snwsihw7O-uFF4UsUd8DOu4bwx0p_NI";
    }

}
