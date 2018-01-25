package ru.sbtqa.tag.goms.tokens;

import java.util.List;
import ru.sbtqa.tag.goms.model.Operator;

public class KToken extends Token {

    public KToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> rule() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
