import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.apache.hc.client5.http.impl.classic.HttpClients.createDefault;

public class Main {
    public static void main(String[] args) throws IOException {
        String urlWithToken = "https://api.nasa.gov/planetary/apod?api_key=LjYFU2ea7TdbiXGN1ZDIiI4z9IFiQ3pAh5b5cEJn";

        CloseableHttpClient client = createDefault();
        ObjectMapper mapper = new ObjectMapper();

        // посылаю запрос на постоянный эндпоинт
        HttpGet req = new HttpGet(urlWithToken);

        // HTTP-ответ сохраняю в переменную
        CloseableHttpResponse resp = client.execute(req);

        // преобразование битовой последовательности (http-Ответ) к структуре, описанной в классе NasaAnswer
        NasaAnswer ans = mapper.readValue(resp.getEntity().getContent(), NasaAnswer.class);

        // из URLа добываем название файла
        // System.out.println(ans.url);
        String[] splittedUrl = ans.url.split("/");
        String fileName = splittedUrl[splittedUrl.length-1];

        // посылаю запрос по адресу картинки
        HttpGet pictureReq = new HttpGet(ans.url);

        // сохраняем картинку в переменную
        resp = client.execute(pictureReq);

        // создаем специальный объект, привязанный к файлу на диске и умеющий в него записывать
        FileOutputStream fos = new FileOutputStream("Pics/" + fileName);

        // взять ответ (картинку) и сохранить в файл
        resp.getEntity().writeTo(fos);

    }
}
