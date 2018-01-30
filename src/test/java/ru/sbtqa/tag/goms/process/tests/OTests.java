package ru.sbtqa.tag.goms.process.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.process.Processor;
import ru.sbtqa.tag.goms.process.tokens.Token;
import ru.sbtqa.tag.goms.process.tokens.TokenFactory;

public class OTests {

    @Test
    public void OpenPageTest() {
        // A1
        String step = "* пользователь находится на странице \"Ввод паспортных данных\"";
        List<Token> feature = new ArrayList<>(); 
        feature.add(TokenFactory.createToken(step, "O")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(TokenFactory.createToken(step, "O"));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }
}
