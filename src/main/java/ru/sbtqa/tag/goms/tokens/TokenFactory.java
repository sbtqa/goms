package ru.sbtqa.tag.goms.tokens;

import ru.sbtqa.tag.goms.model.Model;

public class TokenFactory {
    
    public static final int DEFAULT_MULTIPLIER = 1;
    public static final String DEFAULT_STEP = "";

    public static Token createToken(String symbol) {
        return createToken(DEFAULT_STEP, DEFAULT_MULTIPLIER, symbol);
    }
   
    public static Token createToken(String step, String symbol) {
        return createToken(step, DEFAULT_MULTIPLIER, symbol);
    }
    
    public static Token createToken(String step, int multiplier, String symbol) {
        switch (symbol) {
            case "KK":
                return new KKToken(step, multiplier, Model.getOperator(symbol));
            case "K":
                return new KToken(step, multiplier, Model.getOperator(symbol));
            case "O":
                return new OToken(step, multiplier, Model.getOperator(symbol));
            case "BB":
                return new BBToken(step, multiplier, Model.getOperator(symbol));
            case "C":
                return new CToken(step, multiplier, Model.getOperator(symbol));
            case "S":
                return new SToken(step, multiplier, Model.getOperator(symbol));
            case "ST":
                return new STToken(step, multiplier, Model.getOperator(symbol));
            case "F":
                return new FToken(step, multiplier, Model.getOperator(symbol));
            default:
                return new DefaultToken(step, multiplier, Model.getOperator(symbol));
        }
    }
}
