import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Display {
    private List<String> textRows;
    private static final int MAX_WIDTH = 80; //default: 80
    private static final int TEXTBOX_HORIZONTAL_POSITION = 46;
    private static final int TEXTBOX_VERTICAL_POSITION = 20;

    private static Scanner reader = new Scanner(System.in);

    public Display () {
        String chris =
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
        textRows = Arrays.asList(chris.split("\n"));
    }

    public void print (String text) {
        Dialog textbox = new Dialog(text, MAX_WIDTH, TEXTBOX_HORIZONTAL_POSITION);
        int rowIndex = 0;
        int textboxIndex = 0;
        int textboxUpperBounds = TEXTBOX_VERTICAL_POSITION - textbox.getHeight();
        int textboxLowerBounds = TEXTBOX_VERTICAL_POSITION;

        List<String> outputTextRows = extendScreen(textRows, textboxUpperBounds);

        if (outputTextRows.size() != textRows.size()) {
            textboxLowerBounds += -textboxUpperBounds + 1;
            textboxUpperBounds = 0;
        }
        clearScreen();
        for (String row : outputTextRows) {
            rowIndex++;
            if (rowIndex < textboxLowerBounds && rowIndex >= textboxUpperBounds) {
                row = row.substring(0, TEXTBOX_HORIZONTAL_POSITION) + textbox.getTextBox().get(textboxIndex);
                textboxIndex++;
            }
            System.out.println(row);
        }
    }

    //@@author Abishek Kashyap-reused
    //Code reused from https://stackoverflow.com/a/38365871
    //with minor modifications
    private static void clearScreen () {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }
    //@@author
    public List<String> extendScreen (List<String> inputRows, int difference) {
        List<String> outputRows = new ArrayList<String>();
        if (difference < 0) {
            for (int i = 0; i >= difference; i--) {
                outputRows.add(0, Dialog.generateIndent(MAX_WIDTH));
            }
        }
        outputRows.addAll(inputRows);
        return outputRows;
    }

    public String getInput () {
        System.out.println("");
        System.out.println("What would you like to do?");
        System.out.print("INPUT:    ");
        return reader.nextLine();
    }
}
