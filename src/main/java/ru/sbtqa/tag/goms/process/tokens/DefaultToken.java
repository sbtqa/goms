package ru.sbtqa.tag.goms.process.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.model.Operator;

class DefaultToken extends Token {

    public DefaultToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> atomize() {
        List<Token> workflow = new ArrayList<>();
        
        workflow.addAll(this.wrap());
        
        return workflow;
    }
    
}
