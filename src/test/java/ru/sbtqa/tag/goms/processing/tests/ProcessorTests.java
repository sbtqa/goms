package ru.sbtqa.tag.goms.processing.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;
import ru.sbtqa.tag.goms.processing.Processor;

public class ProcessorTests {

    @Test
    public void Test1() {
        List<Token> feature = new ArrayList<>(); 
        feature.add(new Token(Symbol.BB, "* пользователь (выбирает) \"Поиск по каталогу\" \"Представители\"")); 
        List<Token> expectedWorkflow = new ArrayList<>(); 
        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.BB, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.T, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.M, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.P, "", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.BB, "* пользователь (выбирает) \"Поиск по каталогу\" \"Представители\"", 1, new ArrayList<>(), false));
        expectedWorkflow.add(new Token(Symbol.T, "", 1, new ArrayList<>(), false));
        
        List<Token> actualWorkflow = Processor.process(feature);
        
        Assert.assertEquals(expectedWorkflow, actualWorkflow);
    }
}
