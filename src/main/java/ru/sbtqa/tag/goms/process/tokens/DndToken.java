package ru.sbtqa.tag.goms.process.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.model.Operator;

class DndToken extends Token {

    public DndToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> atomize() {
        List<Token> workflow = new ArrayList<>();
        
        workflow.add(TokenFactory.createToken("M"));
        workflow.addAll(moveHandsOn(HandContext.ON_MOUSE));
        workflow.add(TokenFactory.createToken("P"));
        workflow.add(TokenFactory.createToken("B"));
        workflow.add(TokenFactory.createToken(this.getStep(),"P"));
        workflow.add(TokenFactory.createToken("B"));

        return workflow;
    }
}
