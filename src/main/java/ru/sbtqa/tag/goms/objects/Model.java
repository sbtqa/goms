package ru.sbtqa.tag.goms.objects;

import java.util.List;

public class Model {

    private String name;
    private List<Operator> operators;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Operator> getOperator() {
        return operators;
    }

    public void setOperator(List<Operator> operator) {
        this.operators = operator;
    }

    @Override
    public String toString() {
        return "Models[name = " + name + ", operators = " + operators + "]";
    }
}
