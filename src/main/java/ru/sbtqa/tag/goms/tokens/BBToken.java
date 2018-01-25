package ru.sbtqa.tag.goms.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.model.Operator;

public class BBToken extends Token {

    public BBToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> rule() {
        List<Token> workflow = new ArrayList<>();
        
        workflow.add(TokenFactory.createToken("M"));
        moveHandsOn(HandContext.ON_MOUSE);

//        String actionSelect = String.format(Templates.TEMPLATE_ACTION, actions.get("select").getDescription()).replace("\"", "");
//        if (token.getDescription().toLowerCase().contains(actionSelect)) {
//            workflow.add(new ru.sbtqa.tag.goms.objects.Token(Symbol.P));
//            workflow.add(new ru.sbtqa.tag.goms.objects.Token(Symbol.BB));
//            workflow.add(new ru.sbtqa.tag.goms.objects.Token(Symbol.T));
//            workflow.add(new ru.sbtqa.tag.goms.objects.Token(Symbol.M));
//        }
//
//        String actionSelectWoWait = String.format(Templates.TEMPLATE_ACTION, actions.get("selectWithoutSystemResponse").getDescription()).replace("\"", "");
//        if (token.getDescription().toLowerCase().contains(actionSelectWoWait)) {
//            workflow.add(new ru.sbtqa.tag.goms.objects.Token(Symbol.P));
//            workflow.add(new ru.sbtqa.tag.goms.objects.Token(Symbol.BB));
//            workflow.add(new ru.sbtqa.tag.goms.objects.Token(Symbol.M));
//        }

        workflow.add(TokenFactory.createToken("P"));
        workflow.addAll(this.wrap());
        workflow.add(TokenFactory.createToken("T"));
        
//        String actionSetCheckbox = String.format(Templates.TEMPLATE_ACTION, actions.get("setCheckBox").getDescription()).replace("\"", "");
//        if (!token.getDescription().toLowerCase().contains(actionSetCheckbox)) {
//            workflow.add(new ru.sbtqa.tag.goms.objects.Token(Symbol.T));
//        }
        
        return workflow;
    }
}
