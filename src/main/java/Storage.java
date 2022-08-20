public class Storage {
    private static final String[] list = new String[120];
    private static int size = 0;

//    public Storage(){
//    }

    public static void add(String item) {
        list[size] = item;
        size++;
        System.out.format("added: %s%n", item);
    }

    public static void listAll() {
        for(int i = 0; i < size; i++) {
            System.out.format("%d. %s%n", i+1, list[i]);
        }
    }

    public void execute(String cmd) {
        if(cmd.equals("list")) {
            listAll();
        } else {
            add(cmd);
        }
    }
}
