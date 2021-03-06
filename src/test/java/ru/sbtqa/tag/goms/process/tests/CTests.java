package ru.sbtqa.tag.goms.process.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.process.Processor;
import ru.sbtqa.tag.goms.process.tokens.Token;
import ru.sbtqa.tag.goms.process.tokens.TokenFactory;

public class CTests {

    @Test
    public void SetCheckBoxTest() {
        // A1
        String step = "* пользователь отмечает чекбокс \"Запомнить пароль\"";
        List<Token> feature = new ArrayList<>(); 
        feature.add(TokenFactory.createToken(step, "C")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("P"));
        expectedWorkflow.add(TokenFactory.createToken(step, "C"));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);

        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }
}
