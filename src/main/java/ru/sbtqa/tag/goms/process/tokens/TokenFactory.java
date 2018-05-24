package ru.sbtqa.tag.goms.process.tokens;

import ru.sbtqa.tag.goms.model.Operators;


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
                return new KkToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "K":
                return new KToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "O":
                return new OToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "BB":
                return new BbToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "C":
                return new CToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "S":
                return new SToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "ST":
                return new StToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "F":
                return new FToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "SCR":
                return new ScrToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            case "DND":
                return new DndToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
            default:
                return new DefaultToken(step, multiplier, Operators.INSTANCE.getOperator(symbol));
        }
    }
}
