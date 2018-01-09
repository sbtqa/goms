package ru.sbtqa.tag.goms.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Token {

    private static final int DEFAULT_MULTIPLIER = 1;
    private static final String DEFAULT_DESCRIPTION = "";
    private static final boolean DEFAULT_SELF_CONTEXT = false;

    private Symbol operator;
    private String description;
    private int multiplier;
    private List<Symbol> prelims;
    private boolean selfContext;

    public Token(Symbol operator) {
        this(operator, DEFAULT_DESCRIPTION, DEFAULT_MULTIPLIER, new ArrayList<>(), DEFAULT_SELF_CONTEXT);
    }

    public Token(Symbol operator, int multiplier) {
        this(operator, DEFAULT_DESCRIPTION, multiplier, new ArrayList<>(), DEFAULT_SELF_CONTEXT);
    }

    @JsonCreator
    public Token(@JsonProperty("operator") Symbol operator, @JsonProperty("description") String description) {
        this(operator, description, DEFAULT_MULTIPLIER, new ArrayList<>(), DEFAULT_SELF_CONTEXT);
    }

    public Token(Symbol operator, List<Symbol> prelims) {
        this(operator, DEFAULT_DESCRIPTION, DEFAULT_MULTIPLIER, prelims, DEFAULT_SELF_CONTEXT);
    }

    public Token(Symbol operator, String description, List<Symbol> prelims) {
        this(operator, description, DEFAULT_MULTIPLIER, prelims, DEFAULT_SELF_CONTEXT);
    }

    public Token(Symbol operator, String description, boolean selfContext) {
        this(operator, description, DEFAULT_MULTIPLIER, new ArrayList<>(), selfContext);
    }

    public Token(Symbol operator, String stepName, int multiplier, List<Symbol> prelims, boolean selfContext) {
        this.description = stepName;
        this.operator = operator;
        this.multiplier = multiplier;
        this.prelims = prelims;
        this.selfContext = selfContext;
    }

    /**
     * @return the prelims
     */
    public List<Symbol> getPrelims() {
        return prelims;
    }

    /**
     * @param prelims the prelims to set
     */
    public void setPrelims(List<Symbol> prelims) {
        this.prelims = prelims;
    }

    /**
     * @return the stepName
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param stepName the stepName to set
     * @return this
     */
    public Token setDescription(String stepName) {
        this.description = stepName;
        return this;
    }

    /**
     * @return the operator
     */
    public Symbol getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(Symbol operator) {
        this.operator = operator;
    }

    /**
     * @return the multiplier
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * @param multiplier the multiplier to set
     */
    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * @return the selfContext
     */
    public boolean isSelfContext() {
        return selfContext;
    }

    /**
     * @param selfContext the selfContext to set
     */
    public void setSelfContext(boolean selfContext) {
        this.selfContext = selfContext;
    }

    @Override
    public String toString() {
        return "Token[stepName=" + description
                + ", operator=" + operator
                + ", multiplier=" + multiplier
                + ", prelims=" + prelims
                + ", selfContext=" + selfContext + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Token)) {
            return false;
        }

        Token token = (Token) o;

        return description.equals(token.description)
                && operator.equals(token.operator)
                && multiplier == token.multiplier
                && prelims.equals(token.prelims)
                && selfContext == token.selfContext;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.operator);
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + this.multiplier;
        hash = 71 * hash + Objects.hashCode(this.prelims);
        hash = 71 * hash + (this.selfContext ? 1 : 0);
        return hash;
    }
}
