package generatedLogicInC;

public class LogicInCToken {
    public final TypeOfToken typeOfToken;
    public final String text;

    LogicInCToken(String text, TypeOfToken typeOfToken) {
        this.text = text;
        this.typeOfToken = typeOfToken;
    }

    enum TypeOfToken {
		NOT,
		OR,
		AND,
		XOR,
		OPEN,
		CLOSE,
		VAR,
        END
    }


}