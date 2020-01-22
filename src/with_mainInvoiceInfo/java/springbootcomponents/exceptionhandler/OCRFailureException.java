package springbootcomponents.exceptionhandler;

public class OCRFailureException extends RuntimeException {

    String Message;

    public OCRFailureException(){}

    public OCRFailureException(String message){
        Message = message;
    }

    @Override
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
