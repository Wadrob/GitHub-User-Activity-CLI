package manager;

import service.GitActivityService;

import java.io.IOException;
import java.util.Scanner;

public class GitActivityManager {

    public void run() throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        GitActivityService gitActivityService = new GitActivityService();
        System.out.println("Please enter username for git-hub public activities:");

        while (true) {
            String line = scan.nextLine();

            if (line.contains("quit")) {
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

