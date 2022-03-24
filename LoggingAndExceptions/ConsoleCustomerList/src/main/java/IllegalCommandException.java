public class IllegalCommandException extends RuntimeException{

    public IllegalCommandException (String command){
        super(command);
    }
}
