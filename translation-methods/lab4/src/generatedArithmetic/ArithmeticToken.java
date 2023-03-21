package generatedArithmetic;

public class ArithmeticToken {
    public final TypeOfToken typeOfToken;
    public final String text;

    ArithmeticToken(String text, TypeOfToken typeOfToken) {
        this.text = text;
        this.typeOfToken = typeOfToken;
    }

    enum TypeOfToken {
		POW,
		DIV,
		MUL,
		PLUS,
		MINUS,
		DIGIT,
		OPEN,
		CLOSE,
        END
    }


}