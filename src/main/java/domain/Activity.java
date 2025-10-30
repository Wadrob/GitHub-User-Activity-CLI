package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {
    public String type;
    public Repo repo;
    public Payload payload;
}
