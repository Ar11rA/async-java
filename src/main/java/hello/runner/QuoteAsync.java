package hello.runner;

import com.mashape.unirest.http.exceptions.UnirestException;
import hello.api.Quote;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


public class QuoteAsync {
    public static List<String> getQuotes(int n) {
        CompletableFuture<String>[] promises = new CompletableFuture[n];
        for(int i = 0; i < n; i++) {
            promises[i] = CompletableFuture.supplyAsync(() -> {
                try {
                    return Quote.getRandomQuote();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
                return null;
            });
        }
        CompletableFuture.allOf(promises).join();
        List<String> quotes  = Arrays.stream(promises).map(CompletableFuture::join).collect(Collectors.toList());
        return quotes;
        }
}

