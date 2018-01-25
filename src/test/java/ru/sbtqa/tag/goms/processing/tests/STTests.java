package ru.sbtqa.tag.goms.processing.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.processing.Processor;
import ru.sbtqa.tag.goms.tokens.Token;
import ru.sbtqa.tag.goms.tokens.TokenFactory;

public class STTests {

    @Test
    public void SelectTest() {
        // A1
        String step = "* пользователь (выбирает без отклика системы) \"Поиск по каталогу\" \"Представители\"";
        List<Token> feature = new ArrayList<>(); 
        feature.add(TokenFactory.createToken(step, "ST")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("P"));
        expectedWorkflow.add(TokenFactory.createToken("BB"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("P"));
        expectedWorkflow.add(TokenFactory.createToken(step, "ST"));
        expectedWorkflow.add(TokenFactory.createToken("T"));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);

        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }
}
