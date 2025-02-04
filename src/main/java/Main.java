//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
//import java.io.FileOutputStream;
//import static org.apache.hc.client5.http.impl.classic.HttpClients.createDefault;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {

        new PicSenderBot("daily_pic_sender_bot", "7919335706:AAG9snwsihw7O-uFF4UsUd8DOu4bwx0p_NI");

//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new PicSenderBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }

    }
}
