import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        String url = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        // Task 1
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(request);
        String json = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        ResponseToObject rto = new ResponseToObject();
        List<Facts> factsList = rto.JsonToObjectsList(json, Facts.class);
        List<Facts> factsListFilterUpvotes = factsList.stream()
                .filter(value -> value.getUpvotes().isPresent() && value.getUpvotes().get() > 0)
                .collect(Collectors.toList());
        for (Facts f : factsListFilterUpvotes) {
            System.out.println(f);
        }

        // Task 2
        String key = "";        // нужно вставить свой ключ
        url = "https://api.nasa.gov/planetary/apod?api_key=" + key;
        HttpGet requestNasa = new HttpGet(url);
        CloseableHttpResponse responseNasa = httpClient.execute(requestNasa);
        String jsonNasa = new String(responseNasa.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        List<Nasa> nasaList = rto.JsonToObjectsList(jsonNasa, Nasa.class);

        for (Nasa n : nasaList) {
            String nUrl = n.getUrl();
            String fileName = nUrl.substring(nUrl.lastIndexOf("/") + 1);

            HttpGet requestN = new HttpGet(nUrl);
            CloseableHttpResponse responseN = httpClient.execute(requestN);
            responseN.getEntity().writeTo(new FileOutputStream(new File(fileName)));
        }
    }
}
