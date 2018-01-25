package ru.sbtqa.tag.goms.processing.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.junk.Processor;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;

public class HTests {
    
    @Test
    public void moveHandsOnKeyboardAfterMouseClickAndAutofocusTest() {
        // A1
        List<Token> feature = new ArrayList<>(); 
        feature.add(new Token(Symbol.O, "* пользователь находится на странице \"Способ идентификации\"")); 
        feature.add(new Token(Symbol.BB, "* пользователь (нажимает кнопку) \"У клиента нет карты\"")); 
        feature.add(new Token(Symbol.O, "* пользователь находится на странице \"Проверка по ДУЛ\"")); 
        feature.add(new Token(Symbol.F, "* в фокусе находится элемент \"Серия и номер\"")); 
        feature.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Серия и номер\" \"4593494790\"")); 
        
        List<Token> expectedWorkflow = new ArrayList<>(); 
        
        expectedWorkflow.add(new Token(Symbol.O, "Экран \"Способ идентификации\"", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.BB, "* пользователь (нажимает кнопку) \"У клиента нет карты\"", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.T, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.O, "Экран \"Проверка по ДУЛ\"", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.M, "в фокусе элемент \"Серия и номер\"", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.H, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Серия и номер\" \"4593494790\"", 10, new ArrayList<>(), false));
        
        // A2
        List<Token> actualWorkflow = Processor.process(feature);
        
        // A3
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }
}
