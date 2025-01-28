import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.hc.client5.http.impl.classic.HttpClients.createDefault;

public class UtilsGetURLPictureOfTheDay {

    // класс умеет получать картинку дня и сохранять ее

    public static String getAndSaveDailyPicture () throws IOException {

        String urlWithToken = "https://api.nasa.gov/planetary/apod?api_key=LjYFU2ea7TdbiXGN1ZDIiI4z9IFiQ3pAh5b5cEJn";

        CloseableHttpClient client = createDefault();
        ObjectMapper mapper = new ObjectMapper();

        // посылаю запрос на постоянный эндпоинт
        HttpGet req = new HttpGet(urlWithToken);

        // HTTP-ответ сохраняю в переменную
        CloseableHttpResponse resp = client.execute(req);

        if (resp.getCode() == 200) {

            // преобразование битовой последовательности (http-Ответ) к структуре, описанной в классе NasaAnswer
            DailyPicResponse ans = mapper.readValue(resp.getEntity().getContent(), DailyPicResponse.class);
            return ans.url;

        }
        else {
            return "Sorry, NASA is down now";
        }

    }

}
