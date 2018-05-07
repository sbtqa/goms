package ru.sbtqa.tag.goms.process.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.process.Processor;
import ru.sbtqa.tag.goms.process.tokens.Token;
import ru.sbtqa.tag.goms.process.tokens.TokenFactory;

public class KKTests {

    @Test
    public void fillFieldTest() {
        // A1
        String step = "* пользователь заполняет поле \"Серия и номер\" \"4593494790\"";
        List<Token> feature = new ArrayList<>();
        feature.add(TokenFactory.createToken(step, "KK"));

        List<Token> expectedWorkflow = new ArrayList<>();
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("P"));
        expectedWorkflow.add(TokenFactory.createToken("BB"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("H"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken(step, 10, "KK"));

        // A2
        List<Token> actualWorkflow = Processor.process(feature);

        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }

    @Test
    public void fillFieldWithoutTextTest() {
        // A1
        String step = "* пользователь заполняет поле \"Серия и номер\"";
        List<Token> feature = new ArrayList<>();
        feature.add(TokenFactory.createToken(step, "KK"));

        List<Token> expectedWorkflow = new ArrayList<>();
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("P"));
        expectedWorkflow.add(TokenFactory.createToken("BB"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("H"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken(step, 0, "KK"));

        // A2
        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }

    @Test
    public void fillFieldWithNonWordCharactersTest() {
        // A1
        String step = "* пользователь заполняет поле \"Серия и номер\" \"<>?:{}_+|\"";
        List<Token> feature = new ArrayList<>(); 
        feature.add(TokenFactory.createToken(step, "KK")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("P"));
        expectedWorkflow.add(TokenFactory.createToken("BB"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("H"));
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken(step, 9, "KK"));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }
}
