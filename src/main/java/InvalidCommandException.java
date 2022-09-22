public class InvalidCommandException extends Exception{

    public InvalidCommandException(String error){
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(error);
        System.out.println("-------------------------------------------------------------------------------");
    }
}
