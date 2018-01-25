package ru.sbtqa.tag.goms.tokens;

import ru.sbtqa.tag.goms.processing.States;
import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.model.Model;
import ru.sbtqa.tag.goms.model.Operator;
import ru.sbtqa.tag.goms.utils.Regex;
import ru.sbtqa.tag.goms.utils.Templates;

public class KKToken extends Token {

    public KKToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> atomize() {
        List<Token> workflow = new ArrayList<>();
        
        if (!"".equals(States.focusedElement) && getStep().contains(States.focusedElement)) {
            String elementFocusedDescription = Model.getOperator("F").getDescription();
            String mentalPreparationWithFocusedElementDescription = String.format(elementFocusedDescription, States.focusedElement);
            workflow.add(TokenFactory.createToken(mentalPreparationWithFocusedElementDescription, "M"));
        } else {
            workflow.add(TokenFactory.createToken("M"));
            workflow.addAll(moveHandsOn(HandContext.ON_MOUSE));
            
            // focus on text field by mouse click
            workflow.add(TokenFactory.createToken("P"));
            workflow.add(TokenFactory.createToken("BB"));
            workflow.add(TokenFactory.createToken("M"));
        }

        workflow.addAll(moveHandsOn(HandContext.ON_KEYBOARD));

        setMultiplier(Regex.get(getStep(), Templates.REGEX_INQUOTES, 2).replace("\"", "").length());
        
        workflow.addAll(this.wrap());
        States.focusedElement = "";
        
        return workflow;
    }

}
