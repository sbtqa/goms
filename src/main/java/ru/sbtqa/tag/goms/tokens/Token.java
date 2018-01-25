package ru.sbtqa.tag.goms.tokens;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.contexts.HandContext;
import ru.sbtqa.tag.goms.model.Operator;
import ru.sbtqa.tag.goms.processing.Context;

public abstract class Token {

    private String step;
    private int multiplier;
    private final Operator operator;

    public Token(String step, int multiplier, Operator operator) {
        this.step = step;
        this.multiplier = multiplier;
        this.operator = operator;
    }

    public abstract List<Token> rule();

    /**
     * Apply prelims and self-context to token
     *
     * @return wrapped token
     */
    protected List<Token> wrap() {
        List<Token> wrappedToken = new ArrayList<>();

        wrappedToken.addAll(this.applyPrelims());
        wrappedToken.addAll(this.applySelfContext());
        wrappedToken.add(this);

        return wrappedToken;
    }

    /**
     * Insert prelims tokens before this token
     */
    private List<Token> applyPrelims() {
        // TODO взять из operator prelims и добавить их
        return new ArrayList<>();
    }

    /**
     * Insert H->M before token if it is self-contexted
     */
    private List<Token> applySelfContext() {
        // TODO взять из operator selfContext и добавить его
        return new ArrayList<>();
    }

    protected List<Token> moveHandsOn(HandContext handContext) {
        List<Token> workflow = new ArrayList<>();

        if (Context.handContext != handContext) {
            workflow.add(TokenFactory.createToken("H"));
            Context.handContext = handContext;
            workflow.add(TokenFactory.createToken("M"));
        }

        return workflow;
    }

    public String getStep() {
        return step;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(step, multiplier, operator);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Token) {
            final Token other = (Token) obj;
            return Objects.equal(step, other.step)
                    && multiplier == other.multiplier
                    && Objects.equal(operator, other.operator);
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Token[step=" + step
                + ", multiplier=" + multiplier
                + ", operator=" + operator
                + "]";
    }
}
