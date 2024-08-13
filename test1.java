import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class test1 {
    static Random random = new Random();
    static Scanner sc = new Scanner(System.in);

    static void Rules() {
        System.out.println(
                "Text: A random passage is generated for typing.\r\n" +
                        "Typing: Users type as quickly and accurately as possible.\r\n" +
                        "Accuracy: Mistakes are recorded for accuracy assessment.\r\n" +
                        "Speed: Typing speed (WPM) is calculated.\r\n" +
                        "Output: Displays WPM and Accuracy");
    }

    static String randomtext() {
    

         try {
           
            String content = new String(Files.readAllBytes(Paths.get("paragraphs.txt")));
            String[] paragraphs = content.split("\\r?\\n\\r?\\n");

            
            int randomIndex = random.nextInt(paragraphs.length);
            return paragraphs[randomIndex].trim();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading paragraphs.txt.";
        }
    
    }

    static void test(String x) {

        System.out.println(x);
        System.out.println("\n1");
        System.out.println("Press any key to Start the text");
        String key = sc.nextLine();
        System.out.println("Test Started");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
        
            public void run() {
                System.out.println("\n" + "Time's up , Press any key to Continue");
            }
        };
        timer.schedule(task, 30000); // 30 seconds

        String usertext = sc.nextLine();

        timer.cancel();

        result(usertext, x);
    }

    static int result(String usertext, String randomtext) {
        String[] userwords = usertext.split(" ");
        String[] randomwords = randomtext.split(" ");

        int count = 0;
        int minLength = Math.min(userwords.length, randomwords.length);

        for (int i = 0; i < minLength; i++) {
            if (userwords[i].equals(randomwords[i])) {
                count++;
            }
        }

        float accuracy = ((float) count / minLength) * 100;
        System.out.println("Number of correct words - " + count);
        System.out.println("The Accuracy is " + accuracy + " %");

        float newwpm = count / 0.5f;
        System.out.println("WPM is " + newwpm);

        return 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Welcome to Typing Speed Calculator");
            System.out.println("Press 1 to Start the Test");
            System.out.println("Press 2 for Rules");
            System.out.println("Press 3 to exit");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    String text = randomtext();
                    test(text);
                    break;
                case 2:
                    Rules();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\"Please Press Another key\"");
                    break;
            }
        }
        sc.close();
    }
}
