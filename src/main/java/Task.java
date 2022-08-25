public class Task {
    public String name;
    static public int currTotal = 0;
    public int index;

    public Task(String name){
        this.name = name;
        this.index= currTotal+1;
        currTotal +=1;
    }

}
