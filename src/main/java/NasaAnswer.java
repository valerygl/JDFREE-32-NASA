import com.fasterxml.jackson.annotation.JsonProperty;

// класс, связывающий поля из json с полями класса. Чтобы потом пользоваться полями объекта
public class NasaAnswer {

    String title;
    String url;

    public NasaAnswer(@JsonProperty("copyright") String copyright,
                      @JsonProperty("date") String date,
                      @JsonProperty("explanation") String explanation,
                      @JsonProperty("hdurl") String hdurl,
                      @JsonProperty("media_type") String media_type,
                      @JsonProperty("service_version") String service_version,
                      @JsonProperty("title") String title,
                      @JsonProperty("url") String url) {
        this.title = title;
        this.url = url;
    }
}
