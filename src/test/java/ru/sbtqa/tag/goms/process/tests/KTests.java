package ru.sbtqa.tag.goms.process.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.process.Processor;
import ru.sbtqa.tag.goms.process.tokens.Token;
import ru.sbtqa.tag.goms.process.tokens.TokenFactory;

public class KTests {

    @Test
    public void pressKeyTest() {
        // A1
        String step = "* пользователь (нажимает клавишу) \"Enter\"";
        List<Token> feature = new ArrayList<>(); 
        feature.add(TokenFactory.createToken(step, "K")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("H"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken(step, "K"));
        expectedWorkflow.add(TokenFactory.createToken("T"));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }

    @Test
    public void pressKeyWithHandOnKeyboardTest() {
       // A1
        String step = "* пользователь (нажимает клавишу) \"Enter\"";
        List<Token> feature = new ArrayList<>(); 
        feature.add(TokenFactory.createToken(step, "K")); 
        feature.add(TokenFactory.createToken(step, "K")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("H"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken(step, "K"));
        expectedWorkflow.add(TokenFactory.createToken("T"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken(step, "K"));
        expectedWorkflow.add(TokenFactory.createToken("T"));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }

    @Test
    public void pressTabKeyTest() {
        // A1
        String step = "* пользователь (нажимает клавишу) \"TAB\"";
        List<Token> feature = new ArrayList<>(); 
        feature.add(TokenFactory.createToken(step, "K")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("H"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken(step, "K"));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }
}
