public class List {
    private static int amountOfItems = 0;
    private static String[] listItemNames;
    private static boolean[] listItemsMarks;


    public List(){
         listItemNames = new String[100];
         listItemsMarks = new boolean[100];
    }

    public int getListSize(){
        return amountOfItems;
    }

    public void addItem(String item){
        listItemNames[amountOfItems] = item;
        listItemsMarks[amountOfItems] = false;
        amountOfItems++;
        printingItemAdded(item);
    }

    public void printingList(){
        Message.printingHorizontalLine();
        for (int i = 0; i<amountOfItems; i++)
        {
            printingItem(i);
        }
        Message.printingHorizontalLine();
    }

    public void printingItem(int i){
        String mark = " ";
        if (listItemsMarks[i]){
            mark = "X";
        }
        System.out.println(i + 1 + ". [" + mark + "] " + listItemNames[i]);
    }

    public void markingItem(int i){
        System.out.println("Nice! I've marked this task as done:");
        listItemsMarks[i-1] = true;
        System.out.println( " [X] " + listItemNames[i-1]);
    }

    public void unmarkingItem(int i){
        System.out.println(" OK, I've marked this task as not done yet:");
        listItemsMarks[i-1] = false;
        System.out.println( " [ ] " + listItemNames[i-1]);
    }

    public void printingItemAdded(String item){
        Message.printingHorizontalLine();
        System.out.println("added: " + item);
        Message.printingHorizontalLine();
    }

}
