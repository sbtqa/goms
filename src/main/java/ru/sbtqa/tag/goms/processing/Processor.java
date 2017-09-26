package ru.sbtqa.tag.goms.processing;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;
import ru.sbtqa.tag.goms.objects.contexts.HandContext;
import ru.sbtqa.tag.goms.utils.Regex;

public class Processor {

    public static HandContext handContext;
    public static String focusedElement = "";

    public static List<Token> process(List<Token> tokens) {
        List<Token> workflow = new ArrayList<>();
        handContext = HandContext.ON_MOUSE;

        for (Token token : tokens) {
            switch (token.getOperator()) {
                case O:
                    workflow.addAll(handleO(token));
                    break;

                case F:
                    handleF(token);
                    break;

                case BB:
                    workflow.addAll(handleBB(token));
                    break;

                case KK:
                    workflow.addAll(handleKK(token));
                    break;

                case K:
                    workflow.addAll(handleK(token));
                    break;

                default:
                    workflow.addAll(Wrapper.wrap(token));
            }
        }

        return workflow;
    }

    private static List<Token> handleO(Token token) {
        List<Token> workflowPortion = new ArrayList<>();

        String pageName = Regex.get(token.getDescription(), "\"[\\w\\s]+\"");
        token.setDescription("Экран " + pageName);

        workflowPortion.addAll(Wrapper.wrap(token));
        workflowPortion.add(new Token(Symbol.M));

        return workflowPortion;
    }

    private static List<Token> handleBB(Token token) {
        List<Token> workflowPortion = new ArrayList<>();

        // insert M operator before BB
        if (workflowPortion.get(workflowPortion.size() - 1).getOperator() != Symbol.M) {
            workflowPortion.add(new Token(Symbol.M));
        }

        if (handContext != HandContext.ON_MOUSE) {
            workflowPortion.add(new Token(Symbol.H));
            handContext = HandContext.ON_MOUSE;
            workflowPortion.add(new Token(Symbol.M));
        }
        workflowPortion.add(new Token(Symbol.P));
        workflowPortion.addAll(Wrapper.wrap(token));
        workflowPortion.add(new Token(Symbol.T));

        return workflowPortion;
    }

    private static List<Token> handleKK(Token token) {
        List<Token> workflowPortion = new ArrayList<>();

        if (!"".equals(focusedElement) && token.getDescription().contains(focusedElement)) {
            if (workflowPortion.get(workflowPortion.size() - 1).getOperator() != Symbol.M) {
                workflowPortion.add(new Token(Symbol.M, "в фокусе элемент \"" + focusedElement + "\""));
            } else {
                workflowPortion.set(workflowPortion.size() - 1, workflowPortion.get(workflowPortion.size() - 1).setDescription("в фокусе элемент \"" + focusedElement + "\""));
            }
        } else {
            // insert M operator before KK
            if (workflowPortion.get(workflowPortion.size() - 1).getOperator() != Symbol.M) {
                workflowPortion.add(new Token(Symbol.M));
            }

            // focus on text field by mouse
            if (handContext != HandContext.ON_MOUSE) {
                workflowPortion.add(new Token(Symbol.H));
                handContext = HandContext.ON_MOUSE;
                workflowPortion.add(new Token(Symbol.M));
            }
            workflowPortion.add(new Token(Symbol.P));
            workflowPortion.add(new Token(Symbol.BB));
            workflowPortion.add(new Token(Symbol.M));
            workflowPortion.add(new Token(Symbol.H));
            handContext = HandContext.ON_KEYBOARD;
            workflowPortion.add(new Token(Symbol.M));
        }
        token.setMultiplier(Regex.get(token.getDescription(), "\"[\\w\\s]+\"").replace("\"", "").length());
        workflowPortion.addAll(Wrapper.wrap(token));

        return workflowPortion;
    }

    private static List<Token> handleK(Token token) {
        List<Token> workflowPortion = new ArrayList<>();

        if (workflowPortion.get(workflowPortion.size() - 1).getOperator() != Symbol.M) {
            workflowPortion.add(new Token(Symbol.M));
        }

        if (handContext != HandContext.ON_KEYBOARD) {
            workflowPortion.add(new Token(Symbol.H));
            handContext = HandContext.ON_KEYBOARD;
            workflowPortion.add(new Token(Symbol.M));
        }

        workflowPortion.addAll(Wrapper.wrap(token));
        workflowPortion.add(new Token(Symbol.T));

        return workflowPortion;
    }

    private static void handleF(Token token) {
        // init focused element
        focusedElement = Regex.get(token.getDescription(), "\"[\\w\\s]+\"").replace("\"", "");
    }
}
