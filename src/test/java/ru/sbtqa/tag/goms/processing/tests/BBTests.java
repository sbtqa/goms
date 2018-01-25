package ru.sbtqa.tag.goms.processing.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.processing.Processor;
import ru.sbtqa.tag.goms.tokens.Token;
import ru.sbtqa.tag.goms.tokens.TokenFactory;

public class BBTests {

    @Test
    public void ClickTest() {
        // A1
        String step = "* пользователь (нажимает кнопку) \"Далее\"";
        List<Token> feature = new ArrayList<>(); 
        feature.add(TokenFactory.createToken(step, "BB")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(TokenFactory.createToken("M"));
        expectedWorkflow.add(TokenFactory.createToken("P"));
        expectedWorkflow.add(TokenFactory.createToken(step, "BB"));
        expectedWorkflow.add(TokenFactory.createToken("T"));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }
    
    
//    @Test
//    public void SelectTest() {
//        // A1
//        String select = "* пользователь (выбирает) \"Поиск по каталогу\" \"Представители\"";
//        
//        List<Token> feature = new ArrayList<>(); 
//        feature.add(new Token(Symbol.BB, select)); 
//        
//        List<Token> expectedWorkflow = new ArrayList<>(); 
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.T, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, select, 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.T, "", 1, new ArrayList<>(), false));
//        
//        // A2
//        List<Token> actualWorkflow = Processor.process(feature);
//        
//        // A3
//        Assert.assertEquals(expectedWorkflow, actualWorkflow);
//    }
//
//    @Test
//    public void SelectWoWaitTest() {
//        // A1
//        String select = "* пользователь (выбирает без отклика системы) \"Поиск по каталогу\" \"Представители\"";
//        
//        List<Token> feature = new ArrayList<>(); 
//        feature.add(new Token(Symbol.BB, select)); 
//        
//        List<Token> expectedWorkflow = new ArrayList<>(); 
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, select, 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.T, "", 1, new ArrayList<>(), false));
//        
//        // A2
//        List<Token> actualWorkflow = Processor.process(feature);
//        
//        // A3
//        Assert.assertEquals(expectedWorkflow, actualWorkflow);
//    }
//    
//    @Test
//    public void SelectAfterOpenPageTest() {
//        // A1
//        String select = "* пользователь (выбирает без отклика системы) \"Поиск по каталогу\" \"Представители\"";
//        
//        List<Token> feature = new ArrayList<>(); 
//        feature.add(new Token(Symbol.O,  "* пользователь находится на странице \"Каталог\""));
//        feature.add(new Token(Symbol.BB, select)); 
//        
//        List<Token> expectedWorkflow = new ArrayList<>(); 
//        expectedWorkflow.add(new Token(Symbol.O,  "Экран \"Каталог\"", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, select, 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.T, "", 1, new ArrayList<>(), false));
//        
//        // A2
//        List<Token> actualWorkflow = Processor.process(feature);
//        
//        // A3
//        Assert.assertEquals(expectedWorkflow, actualWorkflow);
//    }
//    
//    @Test
//    public void SetCheckBoxTest() {
//        // A1
//        String setCheckbox = "* пользователь (отмечает признак) \"Поиск по каталогу\"";
//        
//        List<Token> feature = new ArrayList<>(); 
//        feature.add(new Token(Symbol.BB, setCheckbox)); 
//        
//        List<Token> expectedWorkflow = new ArrayList<>(); 
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, setCheckbox, 1, new ArrayList<>(), false));
//        
//        // A2
//        List<Token> actualWorkflow = Processor.process(feature);
//        
//        // A3
//        Assert.assertEquals(expectedWorkflow, actualWorkflow);
//    }
}
