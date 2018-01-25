package ru.sbtqa.tag.goms.model;

import com.google.common.base.Objects;

public class Operator {

    private String name;
    private int time;
    private boolean isMultipliable;
    private String symbol;
    private String description;

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

    public boolean getIsMultipliable() {
        return isMultipliable;
    }

    public void setIsMultipliable(boolean isMultipliable) {
        this.isMultipliable = isMultipliable;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(name, time, isMultipliable, symbol, description);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Operator) {
            final Operator other = (Operator) obj;
            return Objects.equal(name, other.name)
                    && time == other.time
                    && isMultipliable == other.isMultipliable
                    && Objects.equal(symbol, other.symbol)
                    && Objects.equal(description, other.description);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Operator[symbol = " + symbol + ", name = " + name + ", time = " + time + ", isMultipliable = " + isMultipliable + ", description = " + description + "]";
    }

}
