package ru.sbtqa.tag.goms.junk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;
import ru.sbtqa.tag.goms.utils.Reader;
import ru.sbtqa.tag.goms.utils.Regex;
import ru.sbtqa.tag.goms.utils.Templates;

public class Processor {

    // TODO вынести в отдельный контекст
    public static HandContext handContext = HandContext.ON_MOUSE;
    public static String focusedElement = "";

    public static List<Token> process(List<Token> tokens) {
        List<Token> workflow = new ArrayList<>();
        handContext = HandContext.ON_MOUSE;
        focusedElement = "";

        Map<String, Token> actions = Reader.getActions(Reader.readFileFromResources("model/actions.json"));

        for (Token token : tokens) {
            switch (token.getOperator()) {
                case O:
                    String pageName = Regex.get(token.getDescription(), Templates.REGEX_INQUOTES);
                    token.setDescription("Экран " + pageName);

                    workflow.addAll(Wrapper.wrap(token));
                    workflow.add(new Token(Symbol.M));
                    focusedElement = "";
                    break;

                case F:
                    // init focused element 
                    focusedElement = Regex.get(token.getDescription(), Templates.REGEX_INQUOTES).replace("\"", "");
                    break;

                case BB:
                    // insert M operator before BB 
                    if (workflow.isEmpty() || workflow.get(workflow.size() - 1).getOperator() != Symbol.M) {
                        workflow.add(new Token(Symbol.M));
                    }

                    if (handContext != HandContext.ON_MOUSE) {
                        workflow.add(new Token(Symbol.H));
                        handContext = HandContext.ON_MOUSE;
                        workflow.add(new Token(Symbol.M));
                    }

                    String actionSelect = String.format(Templates.TEMPLATE_ACTION, actions.get("select").getDescription()).replace("\"", "");
                    if (token.getDescription().toLowerCase().contains(actionSelect)) {
                        workflow.add(new Token(Symbol.P));
                        workflow.add(new Token(Symbol.BB));
                        workflow.add(new Token(Symbol.T));
                        workflow.add(new Token(Symbol.M));
                    }
                    
                    String actionSelectWoWait = String.format(Templates.TEMPLATE_ACTION, actions.get("selectWithoutSystemResponse").getDescription()).replace("\"", "");
                    if (token.getDescription().toLowerCase().contains(actionSelectWoWait)) {
                        workflow.add(new Token(Symbol.P));
                        workflow.add(new Token(Symbol.BB));
                        workflow.add(new Token(Symbol.M));
                    }

                    workflow.add(new Token(Symbol.P));
                    workflow.addAll(Wrapper.wrap(token));

                    String actionSetCheckbox = String.format(Templates.TEMPLATE_ACTION, actions.get("setCheckBox").getDescription()).replace("\"", "");
                    if (!token.getDescription().toLowerCase().contains(actionSetCheckbox)) {
                        workflow.add(new Token(Symbol.T));
                    }
                    break;

                case KK:
                    if (!"".equals(focusedElement) && token.getDescription().contains(focusedElement)) {
                        if (workflow.get(workflow.size() - 1).getOperator() != Symbol.M) {
                            workflow.add(new Token(Symbol.M, String.format(actions.get("isElementFocused").getDescription(), focusedElement)));
                        } else {
                            workflow.set(workflow.size() - 1, workflow.get(workflow.size() - 1).setDescription(String.format(actions.get("isElementFocused").getDescription(), focusedElement)));
                        }
                    } else {
                        // insert M operator before KK 
                        if (workflow.isEmpty() || workflow.get(workflow.size() - 1).getOperator() != Symbol.M) {
                            workflow.add(new Token(Symbol.M));
                        }

                        // focus on text field by mouse 
                        if (handContext != HandContext.ON_MOUSE) {
                            workflow.add(new Token(Symbol.H));
                            handContext = HandContext.ON_MOUSE;
                            workflow.add(new Token(Symbol.M));
                        }
                        workflow.add(new Token(Symbol.P));
                        workflow.add(new Token(Symbol.BB));
                        workflow.add(new Token(Symbol.M));
                    }
                    
                    // move hands on the keyboard
                    if(handContext != HandContext.ON_KEYBOARD) {
                        workflow.add(new Token(Symbol.H));
                        workflow.add(new Token(Symbol.M));
                    }
                    
                    token.setMultiplier(Regex.get(token.getDescription(), Templates.REGEX_INQUOTES, 2).replace("\"", "").length());

                    workflow.addAll(Wrapper.wrap(token));
                    focusedElement = "";
                    handContext = HandContext.ON_KEYBOARD;
                    break;

                case K:
                    if (workflow.isEmpty() || workflow.get(workflow.size() - 1).getOperator() != Symbol.M) {
                        workflow.add(new Token(Symbol.M));
                    }

                    if (handContext != HandContext.ON_KEYBOARD) {
                        workflow.add(new Token(Symbol.H));
                        handContext = HandContext.ON_KEYBOARD;
                        workflow.add(new Token(Symbol.M));
                    }

                    workflow.addAll(Wrapper.wrap(token));
                    if (!token.getDescription().toLowerCase().contains(Templates.KEY_TAB)) {
                        workflow.add(new Token(Symbol.T));
                    }
                    break;

                default:
                    workflow.addAll(Wrapper.wrap(token));
            }
        }

        return workflow;
    }
}
