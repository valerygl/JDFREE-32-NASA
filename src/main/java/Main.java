//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
//import java.io.FileOutputStream;
//import static org.apache.hc.client5.http.impl.classic.HttpClients.createDefault;
import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException {

        AstronomyPictureOfTheDay.getAndSaveDailyPicture();

    }
}
