import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.Scanner;

import static org.apache.hc.client5.http.impl.classic.HttpClients.createDefault;

public class Main {
    public static void main(String[] args) throws IOException {
        String urlWithToken = "https://api.nasa.gov/planetary/apod?api_key=LjYFU2ea7TdbiXGN1ZDIiI4z9IFiQ3pAh5b5cEJn";

        CloseableHttpClient client = createDefault();
        ObjectMapper mapper = new ObjectMapper();

        HttpGet req = new HttpGet(urlWithToken);
        CloseableHttpResponse resp = client.execute(req);


        NasaAnswer ans1 = mapper.readValue(resp.getEntity().getContent(), NasaAnswer.class);
//        NasaAnswer ans = mapper.readValue(resp.getEntity().getContent(), NasaAnswer.class);

        System.out.println(ans1.title);
        System.out.println(ans1.url);

//        Scanner sca = new Scanner(resp.getEntity().getContent());
//        String imgInfo = sca.nextLine();
//        System.out.println(imgInfo);

    }
}
