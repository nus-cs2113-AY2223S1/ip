import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void WelcomeUser() {
        String welcomeText = "-------------------------------------\n"
                + "Hello! I am not Duke.\n"
                + "I can echo you now.\n"
                + "-------------------------------------";
        System.out.println(welcomeText);
    }

    public static String CleanInput(String input) {
        return input.toLowerCase().trim();
    }

    public static void ListWords(String[] list, int listSize) {
        if (listSize != 0) {
            for (int i = 0; i < listSize; i++) {
                System.out.println((i + 1) + ": " + list[i]);
            }
        }
        else {
            System.out.println("The list is empty, Please add something!");
        }
    }

    public static void AddWord(String[] wordList, String word, int pos) {
        wordList[pos] = word;
        System.out.println("Added \"" + word + "\"");
    }

    public static void TrackInput() {
        Scanner scanner = new Scanner(System.in);
        String[] wordList = new String[100];
        final String LINEDIVIDER = "-------------------------------------";
        int wordCount = 0;
        while (true) {
            String input = CleanInput(scanner.nextLine());
            System.out.println(LINEDIVIDER);
            if (input.equals("bye")) {
                System.out.println("Bye bye!!! \n" + LINEDIVIDER + "\n");
                break;
            }
            else if (input.equals("list")) {
                ListWords(wordList, wordCount);
            }
            else {
                AddWord(wordList, input, wordCount);
                wordCount++;
            }
            System.out.println(LINEDIVIDER);
        }
    }

    public static void main(String[] args) {
        WelcomeUser();
        TrackInput();
    }
}
