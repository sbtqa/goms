package ru.sbtqa.tag.goms.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import ru.sbtqa.tag.goms.objects.Symbol;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Goms {

    public Symbol[] prelims() default {};
    public Symbol symbol();
    public boolean selfContext() default false;
}
