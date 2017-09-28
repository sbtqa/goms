package ru.sbtqa.tag.goms.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import ru.sbtqa.tag.goms.objects.Symbol;

/**
 * Declare method as goms operator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Goms {

    // Sequence of the operators to add before this
    public Symbol[] prelims() default {};
    // This operator's symbol
    public Symbol symbol();
    // True if user need to transfer hands to the object which is not a mouse or a keyboard
    public boolean selfContext() default false;
}
