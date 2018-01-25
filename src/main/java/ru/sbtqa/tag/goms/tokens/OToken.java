package ru.sbtqa.tag.goms.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.model.Operator;
import ru.sbtqa.tag.goms.processing.Context;
import ru.sbtqa.tag.goms.utils.Regex;
import ru.sbtqa.tag.goms.utils.Templates;

public class OToken extends Token {

    public OToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> rule() {
        List<Token> workflow = new ArrayList<>();
        
        // TODO вынести в экспорт
        String pageName = Regex.get(getStep(), Templates.REGEX_INQUOTES);
        setStep("Экран " + pageName);

        workflow.addAll(this.wrap());
        workflow.add(TokenFactory.createToken("M"));
        
        Context.focusedElement = "";
        
        return workflow;
    }

}
