package hello.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Quote {
    public static String getRandomQuote() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://api.quotable.io/random").asJson();
        return jsonResponse.getBody().getObject().get("content").toString();
    }
}
