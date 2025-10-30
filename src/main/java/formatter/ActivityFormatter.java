package formatter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Activity;

import java.io.IOException;
import java.util.List;

public class ActivityFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public String formatActivities(String json) throws IOException {

        List<Activity> activities = MAPPER.readValue(json, new TypeReference<>() {
        });

        StringBuilder sb = new StringBuilder("Output:\n");

        for (Activity activity : activities) {

            String name = activity.getRepo().getName();
            String refType = activity.getPayload().getRefType();
            String ref = activity.getPayload().getRef();
            String type = activity.getType();

            switch (type) {
                case "PushEvent" -> {
                    sb.append(String.format("- Pushed to %s (branch %s)%n", name, getBranch(activity)));
                }
                case "CreateEvent" -> {
                    if ("branch".equals(refType)) {
                        sb.append(String.format("- Created activity branch %s in %s%n", ref, name));
                    } else if ("repository".equals(refType)) {
                        sb.append(String.format("- Created activity new repository %s%n", name));
                    } else {
                        sb.append(String.format("- Created something in %s%n", name));
                    }
                }
                case "IssuesEvent" -> sb.append(String.format("- Opened or modified an issue in %s%n", name));
                case "WatchEvent" -> sb.append(String.format("- Starred %s%n", name));
                default -> sb.append(String.format("- %s in %s%n", type, name));
            }
        }

        return sb.toString();
    }

    private String getBranch(Activity activity) {
        return isPayloadRefNotNull(activity)
                ? replaceBranchNameWithWhiteSpace(activity)
                : "unknown branch";
    }

    private String replaceBranchNameWithWhiteSpace(Activity activity) {
        return activity.getPayload().getRef().replace("refs/heads/", "");
    }

    private boolean isPayloadRefNotNull(Activity activity) {
        return activity.getPayload() != null && activity.getPayload().getRef() != null;
    }
}