package ru.sbtqa.tag.goms.processing.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import ru.sbtqa.tag.goms.tokens.Token;
import ru.sbtqa.tag.goms.tokens.TokenFactory;

public class KKTests {

    @Test
    public void fillFieldTest() {
        // A1
        
        
        List<Token> feature = new ArrayList<>(); 
        Token KKToken = TokenFactory.createToken("* пользователь (заполняет поле) \"Серия и номер\" \"4593494790\"", "KK"); 
        
        System.out.println(KKToken.rule());
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.H, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Серия и номер\" \"4593494790\"", 10, new ArrayList<>(), false));
//        
//        // A2
//        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
//        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }

//    @Test
//    public void fillFieldWithoutTextTest() {
//        // A1
//        List<Token> feature = new ArrayList<>(); 
//        feature.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Серия и номер\"")); 
//        
//        List<Token> expectedWorkflow = new ArrayList<>(); 
//        
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.H, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Серия и номер\"", 0, new ArrayList<>(), false));
//        
//        // A2
//        List<Token> actualWorkflow = Processor.process(feature);
//        
//        // A3
//        Assert.assertEquals(expectedWorkflow, actualWorkflow);
//    }
//
//    @Test
//    public void fillFieldWithNonWordCharactersTest() {
//        // A1
//        List<Token> feature = new ArrayList<>(); 
//        feature.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Серия и номер\" \"<>?:{}_+|\"")); 
//        
//        List<Token> expectedWorkflow = new ArrayList<>(); 
//        
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.BB, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.H, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
//        expectedWorkflow.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Серия и номер\" \"<>?:{}_+|\"", 9, new ArrayList<>(), false));
//        
//        // A2
//        List<Token> actualWorkflow = Processor.process(feature);
//        for (Token token : actualWorkflow) {
//            System.out.println(token);
//        }
//        
//        // A3
//        Assert.assertEquals(expectedWorkflow, actualWorkflow);
//    }
}
