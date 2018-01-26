package ru.sbtqa.tag.goms.model;

import java.util.List;

public class Model {

    private String name;
    private List<Operator> operators;

    public String getName() {
        return name;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    @Override
    public String toString() {
        return "Models[name = " + name + ", operators = " + operators + "]";
    }
}
