package ru.sbtqa.tag.goms.process;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.process.tokens.Token;

public class Normalizer {

    public static List<Token> normalize(List<Token> workflow) {
        List<Token> normalizedWorkflow = new ArrayList<>();

        for (int i = 0; i < workflow.size(); i++) {
            Token token = workflow.get(i);

            // skip first and last token
            if (i == 0 || i == workflow.size() - 1) {
                normalizedWorkflow.add(token);
                continue;
            }

            // remove M if it is between K(K) and K(K)
            switch (token.getOperator().getSymbol()) {
                case "M":
                    String previousSymbol = workflow.get(i - 1).getOperator().getSymbol();
                    String nextSymbol = workflow.get(i + 1).getOperator().getSymbol();

                    if ("KK".equals(previousSymbol)
                            && ("KK".equals(nextSymbol) || "K".equals(nextSymbol))) {
                        break;
                    }
                default:
                    normalizedWorkflow.add(token);
            }
        }
        
        return normalizedWorkflow;
    }
}
