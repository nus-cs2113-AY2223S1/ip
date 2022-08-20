import java.util.Arrays;
import java.util.List;

public class Display {
    private List<String> textRows;
    private static final int MAX_WIDTH = 80; //default: 80
    private static final int TEXTBOX_HORIZONTAL_POSITION = 46;
    private static final int TEXTBOX_VERTICAL_POSITION = 20;
    public Display() {
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

    public void print(String text){
        Dialog textbox = new Dialog(text, MAX_WIDTH, TEXTBOX_HORIZONTAL_POSITION);
        int rowIndex = 0;
        int textboxIndex = 0;
        for (String row : textRows) {
            rowIndex++;
            if (rowIndex < TEXTBOX_VERTICAL_POSITION && rowIndex >= TEXTBOX_VERTICAL_POSITION - textbox.getHeight()){
                row = row.substring(0, TEXTBOX_HORIZONTAL_POSITION) + textbox.getTextBox().get(textboxIndex);
                textboxIndex++;
            }
            System.out.println(row);
        }
    }
}
