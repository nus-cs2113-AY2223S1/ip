package UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class to control the user interface (getting input from user, displaying prompts etc.
 * Displays a box of text overlaid on some ASCII art.
 */
public class UserInterface {
    /**
     * Maximum width of the console the application will run in.
     * Default: 80
     */
    private static final int MAX_WIDTH = 80;

    /**
     * Horizontal position that the box of text will be positioned at.
     * (Left-align)
     * Default: 46
     */
    private static final int TEXTBOX_HORIZONTAL_POSITION = 46;

    /**
     * Vertical position that the box of text will be positioned at.
     * (Bottom-align)
     * Default: 20
     */
    private static final int TEXTBOX_VERTICAL_POSITION = 20;

    /** Scanner used to read input. */
    private static final Scanner READER = new Scanner(System.in);

    private static final String ASCII_ART =
            //@@author Chris Johnson-reused
            //ASCII Art reused from https://asciiart.website/index.php?art=people/chris%20johnson
            //with minor modifications
            "                   ,X#N#####;.                      \n" +
            "                p####Q#@@@##8##s;                   \n" +
            "             p#         #   @ ####QNp@.,            \n" +
            "           ,#     88bb## # @     #####@@            \n" +
            "          p##@ @8'      ` ^\"\"\"T\"T8b88###            \n" +
            "         b#  ##8*                      ##           \n" +
            "         ###@#8G                    .: b#@          \n" +
            "         ###$vfG                    GG b##          \n" +
            "        bN#@$Sl*                   ^GG:S##          \n" +
            "        #NQGS\"                     GGGGN#G          \n" +
            "        S##$!*  ppS####s;,         ^Gpll@Q          \n" +
            "        ###9   `'oG\\\"###89   pQ#####s.Gb#G          \n" +
            "        G#QGo:* Tb7778bo\"*  p@5#@@Qs|#Gb@#          \n" +
            "        |GG!       '`  ' *  bb#G ^b#@3{b#\\          \n" +
            "        /pGG!:              G^       CG#@@          \n" +
            "         ^GGG      v? ;     p9$,     pG#@           \n" +
            "          f: .  pl  GG+b#;pN#8lGG,  pS@G'           \n" +
            "           GG * b\"##ss,  ^\"^GGQSGblGGl@             \n" +
            "           GG:C! .  Go., ^ ^D#57^7 #1               \n" +
            "            lG G)   ;     GGGQlG\"p#@                \n" +
            "          NGb#j    + ^G;GGpSS#3pN#@                 \n" +
            "  ,wmM####@GGb##;G       '^GGGQ#@`                  \n" +
            "######### @GGCG###;;.      pS###@#s,                \n" +
            "######### @GGGGGGG#######N######   ####s,.          \n" +
            "##########@G GGGGG GGGG########     ##@####s,       \n" +
            "###########S.   GG  CGGS#######   #  # ########s,   \n" +
            "##### ######;G   ^'' ~^GGSGGQ#   # #   ############,";
    //@@author

    /** ASCII art from {@link UserInterface#ASCII_ART} expressed as a list of strings, where each row is a string */
    private static final List<String> textRows = Arrays.asList(ASCII_ART.split("\n"));

    /**
     * Function to clear the console. Does not work in the IDE.
     */
    //@@author Abishek Kashyap-reused
    //Code reused from https://stackoverflow.com/a/38365871
    //with minor modifications
    private static void clearScreen() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ignored) {
        }
    }
    //@@author

    /**
     * Prints text, formatted with ASCII art and a text box, to console.
     *
     * @param text text to be printed
     */
    public static void print(String text) {
        DialogBox textBox = new DialogBox(text, MAX_WIDTH, TEXTBOX_HORIZONTAL_POSITION);
        int rowIndex = 0;
        int textBoxIndex = 0;

        // Vertical range that the text box should overlay onto
        int textBoxUpperBounds = TEXTBOX_VERTICAL_POSITION - textBox.getHeight();
        int textBoxLowerBounds = TEXTBOX_VERTICAL_POSITION;

        // Makes sure ASCII art can fit entire text box
        List<String> outputTextRows = extendScreen(textBoxUpperBounds);

        // If screen was extended, adjust upper and lower bounds to fit the box
        if (outputTextRows.size() != textRows.size()) {
            textBoxLowerBounds -= textBoxUpperBounds - 1;
            textBoxUpperBounds = 0;
        }
        clearScreen();
        // add some empty space at the top of the window for aesthetic reasons
        System.out.println();

        // replace text at bounds with text box at specified position
        for (String row : outputTextRows) {
            rowIndex++;
            if (rowIndex < textBoxLowerBounds && rowIndex >= textBoxUpperBounds) {
                row = row.substring(0, TEXTBOX_HORIZONTAL_POSITION)
                        + textBox.getTextBox().get(textBoxIndex);
                textBoxIndex++;
            }
            System.out.println(row);
        }
    }

    /**
     * Extends {@link UserInterface#textRows} to fit text box if needed
     * (in case of vertical overflow)
     *
     * @param difference difference in height that must be accounted for (negative implies overflow)
     * @return list of string tthat may have been extended
     */
    public static List<String> extendScreen(int difference) {
        List<String> outputRows = new ArrayList<>();
        if (difference <= 0) {
            // If there is overflow (difference is negative or 0)
            // Add (-difference) number of empty rows to outputRows
            for (int i = 0; i >= difference; i--) {
                outputRows.add(0, DialogBox.generateIndent(MAX_WIDTH, ' '));
            }
        }
        // Add the ASCII art
        outputRows.addAll(textRows);
        return outputRows;
    }

    /**
     * Gets input from the user
     *
     * @return input string from user
     */
    public static String getInput() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.print("INPUT:    ");
        return READER.nextLine().trim();
    }
}
