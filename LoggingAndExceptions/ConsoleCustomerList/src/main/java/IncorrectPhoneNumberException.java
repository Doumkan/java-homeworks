public class IncorrectPhoneNumberException extends RuntimeException {

    public IncorrectPhoneNumberException (String number){
        super(number);
    }
}
