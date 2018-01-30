package ru.sbtqa.tag.goms.model;

import com.google.common.base.Objects;

public class Operator {

    private String name;
    private int time;
    private String symbol;
    private String description;
    private String term;
    private boolean selfContexted;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    
    public boolean isSelfContexted() {
        return selfContexted;
    }

    public void setSelfContexted(boolean selfContexted) {
        this.selfContexted = selfContexted;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, time, symbol, description, term);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Operator) {
            final Operator other = (Operator) obj;
            return Objects.equal(name, other.name)
                    && time == other.time
                    && Objects.equal(symbol, other.symbol)
                    && Objects.equal(description, other.description)
                    && Objects.equal(term, other.term);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Operator[symbol = " + symbol 
                + ", name = " + name 
                + ", time = " + time 
                + ", description = " + description 
                + ", term = " + term + "]";
    }
}
