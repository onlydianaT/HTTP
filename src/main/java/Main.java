import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.*;
import java.util.Date;

public class Main {

    public static final String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) {
        List<Integer> indexOfNullPosition = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        ) {

            HttpGet request = new HttpGet(URL);

            try (
                    CloseableHttpResponse response = httpClient.execute(request);
            ) {
                List<Data> resposeList = mapper.readValue(response.getEntity().getContent(),
                        new TypeReference<>() {
                        }
                );

                for (int i = 0; i < resposeList.size(); i++) {
                    Data line = resposeList.get(i);
                    String t = line.getUpvotes();
                    if (t == null || t.isEmpty()) {
                    } else {
                        indexOfNullPosition.add(i);
                    }
                }
                for (int i = 0; i < indexOfNullPosition.size(); i++) {
                    for (int j = 0; j < resposeList.size(); j++) {
                    }
                    System.out.println(resposeList.get(indexOfNullPosition.get(i)));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

