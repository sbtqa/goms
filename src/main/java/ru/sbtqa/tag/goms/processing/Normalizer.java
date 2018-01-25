package ru.sbtqa.tag.goms.processing;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;

public class Normalizer {

    /**
     * Normalize workflow.
     * Apply rules to workflow
     * 
     * @param workflow workflow
     * @return normalized worflow
     */
    public static List<Token> normalize(List<Token> workflow) {
        List<Token> normalizedWorkflow = new ArrayList<>();

        for (int i = 1; i < workflow.size(); i++) {
            Token token = workflow.get(i);
            
            // skip first and last token
            if(i == 1 || i == workflow.size() - 1) {
                normalizedWorkflow.add(token);
                continue;
            }
            
            // remove M if it is between K(K) and K(K)
            switch (token.getOperator()) {
                case M:
                    Symbol previousSymbol = workflow.get(i - 1).getOperator();
                    Symbol nextSymbol = workflow.get(i + 1).getOperator();

                    if ((previousSymbol == Symbol.KK || previousSymbol == Symbol.K)
                            && (nextSymbol == Symbol.KK || nextSymbol == Symbol.K)) {
                        break;
                    }
                default:
                    normalizedWorkflow.add(token);
            }
            
            // TODO remove M - M sequences
            
        }
        return normalizedWorkflow;
    }
}
