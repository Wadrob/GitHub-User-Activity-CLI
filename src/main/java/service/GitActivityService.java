package service;

import formatter.ActivityFormatter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GitActivityService {

    public String getActivities(String user) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("https://api.github.com/users/%s/events/public?per_page=100", user)))
                    .GET()
                    .build();
        } catch (Exception e) {
            return String.format("Error occurs when getting public activity list for user %s. Error: %s", user, e.getMessage());
        }

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200){
            ActivityFormatter activityFormatter = new ActivityFormatter();
            return activityFormatter.formatActivities(response.body());
        } else {
            return String.format("Could not find activity for user, or user: %s", user);
        }
    }
}