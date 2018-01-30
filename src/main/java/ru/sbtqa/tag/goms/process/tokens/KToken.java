package ru.sbtqa.tag.goms.process.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.model.Operator;
import ru.sbtqa.tag.goms.utils.Templates;

class KToken extends Token {

    public KToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> atomize() {
        List<Token> workflow = new ArrayList<>();

        workflow.add(TokenFactory.createToken("M"));
        workflow.addAll(moveHandsOn(HandContext.ON_KEYBOARD));

        workflow.addAll(this.wrap());
        if (!getStep().toLowerCase().contains(Templates.KEY_TAB)) {
            workflow.add(TokenFactory.createToken("T"));
        }

        return workflow;
    }

}
