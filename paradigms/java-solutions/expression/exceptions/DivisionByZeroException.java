package expression.exceptions;

public class DivisionByZeroException extends EvaluatingException {

    public DivisionByZeroException(String e) {
        super(e);
    }
}
