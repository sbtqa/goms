package ru.sbtqa.tag.goms.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.model.Model;
import ru.sbtqa.tag.goms.model.Operator;
import ru.sbtqa.tag.goms.processing.Context;
import ru.sbtqa.tag.goms.utils.Regex;
import ru.sbtqa.tag.goms.utils.Templates;

public class KKToken extends Token {

    public KKToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> rule() {
        List<Token> workflow = new ArrayList<>();
        
        if (!"".equals(Context.focusedElement) && getStep().contains(Context.focusedElement)) {
            String elementFocusedDescription = Model.getOperator("F").getDescription();
            String mentalPreparationWithFocusedElementDescription = String.format(elementFocusedDescription, Context.focusedElement);
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
        Context.focusedElement = "";
        
        return workflow;
    }

}
