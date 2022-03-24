public class IncorrectEmailException extends RuntimeException{

    public IncorrectEmailException(String email){
        super(email);
    }
}
