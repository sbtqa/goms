package ru.sbtqa.tag.goms.model;

import java.util.List;
import java.util.Map;
import ru.sbtqa.tag.goms.utils.Reader;

public class Model {

    private String name;
    private List<Operator> operators;

    private static class LazyHolder {
        static final Map<String, Operator> OPERATORS = Reader.getModel(Reader.readFileFromResources("model/model.json"));
    }

    public static Operator getOperator(String symbol) {
        return LazyHolder.OPERATORS.get(symbol);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Operator> getOperators() {
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
