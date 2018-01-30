package ru.sbtqa.tag.goms.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sbtqa.tag.goms.utils.Reader;

public enum Operators {
    INSTANCE;
   
    private final List<Operator> operators = Reader.getModel(Reader.readFileFromResources("model/model.json")).getOperators();
    
    public List<Operator> getOperators() {
        return operators;
    }

    public Operator getOperator(String symbol) {
        Map<String, Operator> map = new HashMap<>();
        operators.forEach(operator -> map.put(operator.getSymbol(), operator));
        
        return map.get(symbol);
    }
}
