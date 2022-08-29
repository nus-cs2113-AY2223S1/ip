public class List {
    private static int amountOfItems = 0;
    private static String[] list;

    public List(){
         list = new String[100];
    }

    public int getListSize(){
        return amountOfItems;
    }

    public void addItem(String item){
        list[amountOfItems] = item;
        amountOfItems++;
        printingItemAdded(item);
    }

    public void printingList(){
        Message.printingHorizontalLine();
        for (int i = 0; i<amountOfItems; i++)
        {
            printingItem(i,list[i]);
        }
        Message.printingHorizontalLine();
    }

    public void printingItem(int i, String item){
        System.out.println(i + 1 + ". " + item);
    }

    public void printingItemAdded(String item){
        Message.printingHorizontalLine();
        System.out.println("added: " + item);
        Message.printingHorizontalLine();
    }

}
