import manager.GitActivityManager;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        GitActivityManager gitActivityManager = new GitActivityManager();
        gitActivityManager.run();
    }
}
