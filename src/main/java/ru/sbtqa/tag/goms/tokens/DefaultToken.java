package ru.sbtqa.tag.goms.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.model.Operator;

public class DefaultToken extends Token {

    public DefaultToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> rule() {
        List<Token> workflow = new ArrayList<>();
        
        workflow.addAll(this.wrap());
        
        return workflow;
    }
    
}
