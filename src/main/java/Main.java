import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        String body = new String(httpResponse.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(body);
    }
}
