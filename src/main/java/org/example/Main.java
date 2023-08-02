package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
//import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

//import static sun.nio.ch.DatagramChannelImpl.AbstractSelectableChannels.forEach;

public class Main {

    public static final String REMOTE_SERVICE_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My Test Service")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
// создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
// отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);
// вывод полученных заголовков
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
// чтение тела ответа
//        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
//        System.out.println(body);

        List<Post> posts = mapper.readValue(
                response.getEntity().getContent(), new
                        TypeReference<List<Post>>() {
                        }
        );
//        posts.stream()
//                .filter(value -> value.getUpvotes() >0)
//                .forEach(System.out::println);

        response.close();
        httpClient.close();

        posts.forEach(System.out::println);
    }
}