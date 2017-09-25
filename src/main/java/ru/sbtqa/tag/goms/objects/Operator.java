package ru.sbtqa.tag.goms.objects;

public class Operator {

    private String name;
    private int time;
    private boolean isMultipliable;
    private Symbol symbol;
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

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Operator[name = " + name + ", time = " + time + ", isMultipliable = " + isMultipliable + ", symbol = " + symbol + ", description = " + description + "]";
    }

}
