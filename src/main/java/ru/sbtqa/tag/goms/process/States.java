package ru.sbtqa.tag.goms.process;

import ru.sbtqa.tag.goms.contexts.HandContext;

public class States {
    
    private static HandContext handContext;
    private static String focusedElement;

    public static HandContext getHandContext() {
        return handContext;
    }

    public static void setHandContext(HandContext aHandContext) {
        handContext = aHandContext;
    }

    public static String getFocusedElement() {
        return focusedElement;
    }

    public static void setFocusedElement(String aFocusedElement) {
        focusedElement = aFocusedElement;
    }

    public static void setStatesToDefault() {
        States.setHandContext(HandContext.ON_MOUSE);
        States.setFocusedElement(null);
    }
}
