import manager.GitActivityManager;
import service.GitActivityService;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        GitActivityService gitActivityService = new GitActivityService();
        Scanner scanner = new Scanner(System.in);

        GitActivityManager gitActivityManager = new GitActivityManager(gitActivityService, scanner);
        gitActivityManager.run();
    }
}
