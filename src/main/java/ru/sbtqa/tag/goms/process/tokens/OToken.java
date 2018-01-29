package ru.sbtqa.tag.goms.process.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.model.Operator;
import ru.sbtqa.tag.goms.process.States;

class OToken extends Token {

    public OToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> atomize() {
        List<Token> workflow = new ArrayList<>();
        
        workflow.addAll(this.wrap());
        
        States.focusedElement = "";
        
        return workflow;
    }

}
