package ru.sbtqa.tag.goms.process.tokens;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.model.Operator;
import ru.sbtqa.tag.goms.process.States;
import ru.sbtqa.tag.goms.utils.Regex;
import ru.sbtqa.tag.goms.utils.Templates;

class FToken extends Token {

    public FToken(String step, int multiplier, Operator operator) {
        super(step, multiplier, operator);
    }

    @Override
    public List<Token> atomize() {
        States.focusedElement = Regex.get(getStep(), Templates.REGEX_INQUOTES).replace("\"", "");
        
        return new ArrayList<>();
    }

}
