package manager;

import service.GitActivityService;

import java.io.IOException;
import java.util.Scanner;

public class GitActivityManager {

    private final GitActivityService gitActivityService;
    private final Scanner scanner;

    public GitActivityManager(GitActivityService gitActivityService, Scanner scanner) {
        this.gitActivityService = gitActivityService;
        this.scanner = scanner;
    }

    public void run() throws IOException, InterruptedException {
        System.out.println("Enter GitHub username (or type 'quit' to exit):");

        while (true) {
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye");
                break;
            } else {
                String activities = gitActivityService.getActivities(line);
                System.out.println(activities);
                System.out.println("Next user or quit");
            }
        }
    }
}

