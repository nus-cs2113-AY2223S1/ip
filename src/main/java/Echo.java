public class Echo {

    private String line;

    public Echo(String line){
        this.line = line;
    }

    public String getEcho(){
        return this.line;
    }


    public void EchoInput(){
        String input = this.line;
        if(input.contains("bye")){
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Bye. Hope to see you again soon");
            System.out.println("-------------------------------------------------------------------------------");
        }else{
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println(input);
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
}
