package com.utn.spring.api;

import com.google.gson.Gson;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ApiArgentinianPlayer
{

    @CircuitBreaker(name = "apiArgentinianPlayer", fallbackMethod = "fallbackApi")
    public SportDataApi getData() throws IOException,InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://app.sportdataapi.com/api/v1/soccer/players?apikey=YOUR_API_KEY&country_id=13"))
                .header("accept", "application/json")
                .header("host","https://app.sportdataapi.com/api/v1/soccer/leagues")
                .header("apikey", "148a4910-cee9-11eb-a6d2-e12ba636a918")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();


        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(),SportDataApi.class);
    }

    public SportDataApi fallbackApi(final Throwable throwable)
    {
        log.info("Fallback cause, {}", throwable.toString());
        return SportDataApi.builder().build();
    }
}
