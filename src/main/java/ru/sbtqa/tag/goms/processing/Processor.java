package ru.sbtqa.tag.goms.processing;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;
import ru.sbtqa.tag.goms.objects.contexts.HandContext;
import ru.sbtqa.tag.goms.utils.Regex;

public class Processor {

    public static final String TEMPLATE_ACTION_SELECT = "(выбирает)";
    public static final String TEMPLATE_ACTION_CHECKBOX = "(отмечает признак)";
    public static final String TEMPLATE_STEP_INFOCUS = "в фокусе элемент \"%s\"";
    public static final String TEMPLATE_KEY_TAB = "tab";
    private static final String REGEX_INQUOTES = "\"[\\w\\s]+\"";
    
    
    public static HandContext handContext;
    public static String focusedElement = "";

    public static List<Token> process(List<Token> tokens) {
        List<Token> workflow = new ArrayList<>();
        handContext = HandContext.ON_MOUSE;
        focusedElement = "";

        for (Token token : tokens) {
            switch (token.getOperator()) {
                case O:
                    String pageName = Regex.get(token.getDescription(), REGEX_INQUOTES);
                    token.setDescription("Экран " + pageName);

                    workflow.addAll(Wrapper.wrap(token));
                    workflow.add(new Token(Symbol.M));
                    focusedElement = "";
                    break;

                case F:
                    // init focused element 
                    focusedElement = Regex.get(token.getDescription(), REGEX_INQUOTES).replace("\"", "");
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
                    
                    if (token.getDescription().toLowerCase().contains(TEMPLATE_ACTION_SELECT)) {
                      workflow.add(new Token(Symbol.P));
                      workflow.add(new Token(Symbol.BB));
                      workflow.add(new Token(Symbol.T));
                      workflow.add(new Token(Symbol.M));
                    }
                    
                    workflow.add(new Token(Symbol.P));
                    workflow.addAll(Wrapper.wrap(token));

                    if (!token.getDescription().toLowerCase().contains(TEMPLATE_ACTION_CHECKBOX)) {
                        workflow.add(new Token(Symbol.T));
                    }
                    break;

                case KK:
                    if (!"".equals(focusedElement) && token.getDescription().contains(focusedElement)) {
                        if (workflow.get(workflow.size() - 1).getOperator() != Symbol.M) {
                            workflow.add(new Token(Symbol.M, String.format(TEMPLATE_STEP_INFOCUS, focusedElement)));
                        } else {
                            workflow.set(workflow.size() - 1, workflow.get(workflow.size() - 1).setDescription(String.format(TEMPLATE_STEP_INFOCUS, focusedElement)));
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
                        workflow.add(new Token(Symbol.H));
                        workflow.add(new Token(Symbol.M));
                    }
                    token.setMultiplier(Regex.get(token.getDescription(), REGEX_INQUOTES).replace("\"", "").length());

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
                    if (!token.getDescription().toLowerCase().contains(TEMPLATE_KEY_TAB)) {
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
