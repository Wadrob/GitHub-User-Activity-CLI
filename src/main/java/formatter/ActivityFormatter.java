package formatter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Activity;

import java.io.IOException;
import java.util.List;

public class ActivityFormatter {

    public String formatActivities(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Activity> activities = mapper.readValue(json, new TypeReference<>() {
        });

        StringBuilder sb = new StringBuilder("Output:\n");

        for (Activity a : activities) {
            switch (a.type) {
                case "PushEvent" -> {
                    String branch = (a.payload != null && a.payload.ref != null)
                            ? a.payload.ref.replace("refs/heads/", "")
                            : "unknown branch";
                    sb.append(String.format("- Pushed to %s (branch %s)%n", a.repo.name, branch));
                }
                case "CreateEvent" -> {
                    if ("branch".equals(a.payload.refType)) {
                        sb.append(String.format("- Created a branch %s in %s%n", a.payload.ref, a.repo.name));
                    } else if ("repository".equals(a.payload.refType)) {
                        sb.append(String.format("- Created a new repository %s%n", a.repo.name));
                    } else {
                        sb.append(String.format("- Created something in %s%n", a.repo.name));
                    }
                }
                case "IssuesEvent" -> sb.append(String.format("- Opened or modified an issue in %s%n", a.repo.name));
                case "WatchEvent" -> sb.append(String.format("- Starred %s%n", a.repo.name));
                default -> sb.append(String.format("- %s in %s%n", a.type, a.repo.name));
            }
        }

        return sb.toString();
    }
}