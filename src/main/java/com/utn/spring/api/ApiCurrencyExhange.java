package com.utn.spring.api;

import com.google.gson.JsonParser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Service
@Slf4j
public class ApiCurrencyExhange
{

    @CircuitBreaker(name = "CurrencyExchange", fallbackMethod = "fallback")
    public double getExchangeCurrencyEuro() throws IOException, InterruptedException, ParseException
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> responseDolar = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String price = JsonParser.parseString(responseDolar.body()).getAsJsonArray().get(0).getAsJsonObject().get("dolar").getAsJsonObject().get("compra").getAsString();
        NumberFormat myNumForm = NumberFormat.getInstance(Locale.FRENCH);
        return(double) myNumForm.parse(price);
    }

    @CircuitBreaker(name = "CurrencyExchange", fallbackMethod = "fallback")
    public double getExchangeCurrencyDolar() throws IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> responseEur = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String price = JsonParser.parseString(responseEur.body()).getAsJsonArray().get(0).getAsJsonObject().get("casa").getAsJsonObject().get("compra").getAsString();//.getAsJsonObject().get("compra").getAsString();

        NumberFormat myNumForm = NumberFormat.getInstance(Locale.FRENCH);
        Float parsedNumber = (Float) myNumForm.parse(price);

        return parsedNumber;
    }

    public double fallback(final Throwable throwable)
    {
        log.info("Fallback cause, {}", throwable.toString());
        return 0D;
    }
}
