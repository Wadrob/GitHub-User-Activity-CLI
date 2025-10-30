package formatter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ActivityFormatterTest {

    @Test
    void shouldFormatPushEventCorrectly() throws IOException {
        String json = """
        [{"type":"PushEvent",
          "repo":{"name":"test/repo"},
          "payload":{"ref":"refs/heads/main"}}]
        """;

        ActivityFormatter formatter = new ActivityFormatter();
        String result = formatter.formatActivities(json);

        assertTrue(result.contains("Pushed to test/repo (branch main)"));
    }


}