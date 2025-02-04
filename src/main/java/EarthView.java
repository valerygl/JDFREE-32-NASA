import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.hc.client5.http.impl.classic.HttpClients.createDefault;

public class EarthView {

    public static void getAndSaveEarthPicture () throws IOException {

//        56.23270926587861, 37.67261778889881 Novinki
//        55.8752429919747, 37.5267613033299 Ang
//        lon=100.75&lat=1.5


        String urlWithToken = "https://api.nasa.gov/planetary/earth/imagery?lon=55.8752429919747&lat=37.5267613033299&date=2024-01-01&api_key=LjYFU2ea7TdbiXGN1ZDIiI4z9IFiQ3pAh5b5cEJn";

        CloseableHttpClient client = createDefault();
        ObjectMapper mapper = new ObjectMapper();

        // посылаю запрос на постоянный эндпоинт
        HttpGet req = new HttpGet(urlWithToken);

        // HTTP-ответ сохраняю в переменную
        CloseableHttpResponse resp = client.execute(req);

        if (resp.getCode() == 200) {

            // преобразование битовой последовательности (http-Ответ) к структуре, описанной в классе NasaAnswer
            DailyPicResponse ans = mapper.readValue(resp.getEntity().getContent(), DailyPicResponse.class);

            // из URLа добываем название файла
            // System.out.println(ans.url);
            String[] splittedUrl = ans.url.split("/");
            String fileName = splittedUrl[splittedUrl.length - 1];

            // посылаю запрос по адресу картинки
            HttpGet pictureReq = new HttpGet(ans.url);

            // сохраняем картинку в переменную
            resp = client.execute(pictureReq);

            // создаем специальный объект, привязанный к файлу на диске и умеющий в него записывать
            FileOutputStream fos = new FileOutputStream("Pics/" + fileName);

            // взять ответ (картинку) и сохранить в файл
            resp.getEntity().writeTo(fos);
        }
        else {
            System.out.println("Sorry, NASA is down now");
        }

    }



}
