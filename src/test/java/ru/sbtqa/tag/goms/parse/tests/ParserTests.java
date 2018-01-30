package ru.sbtqa.tag.goms.parse.tests;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.tag.goms.parse.Parser;
import ru.sbtqa.tag.goms.process.tokens.Token;
import ru.sbtqa.tag.goms.process.tokens.TokenFactory;

public class ParserTests {

    @Test
    public void ParseTest() {
        // A1
        String document = "#language:ru\n"
                + "\n"
                + "Функционал: Ввод паспортных данных\n"
                + "\n"
                + "Сценарий: Стандартный ввод\n"
                + "* пользователь находится на странице \"Ввод паспортных данных\"\n"
                + "* пользователь (заполняет поле) \"Серия\" \"5007\"\n"
                + "* пользователь (заполняет поле) \"Номер\" \"483711\"\n"
                + "* пользователь (прикладывает таблетку)\n"
                + " \n"
                + "Сценарий: Ввод с использование кнопки Tab\n"
                + "* пользователь находится на странице \"Ввод паспортных данных\"\n"
                + "* пользователь (заполняет поле) \"Серия\" \"5007\"\n"
                + "* пользователь (нажимает клавишу) \"Tab\"\n"
                + "* в фокусе находится элемент \"Номер\"\n"
                + "* пользователь (заполняет поле) \"Номер\" \"483711\"\n"
                + "* пользователь (нажимает кнопку) \"Далее\"";
        
        Map<String, List<Token>> expectedFeature = new LinkedHashMap<>();
        List<Token> scenario1 = new ArrayList<>();
        scenario1.add(TokenFactory.createToken("* пользователь находится на странице \"Ввод паспортных данных\"", "O"));
        scenario1.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Серия\" \"5007\"", "KK"));
        scenario1.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Номер\" \"483711\"", "KK"));
        scenario1.add(TokenFactory.createToken("* пользователь (прикладывает таблетку)", "TK"));
        expectedFeature.put("Стандартный ввод", scenario1);
        List<Token> scenario2 = new ArrayList<>();
        scenario2.add(TokenFactory.createToken("* пользователь находится на странице \"Ввод паспортных данных\"", "O"));
        scenario2.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Серия\" \"5007\"", "KK"));
        scenario2.add(TokenFactory.createToken("* пользователь (нажимает клавишу) \"Tab\"", "K"));
        scenario2.add(TokenFactory.createToken("* в фокусе находится элемент \"Номер\"", "F"));
        scenario2.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Номер\" \"483711\"", "KK"));
        scenario2.add(TokenFactory.createToken("* пользователь (нажимает кнопку) \"Далее\"", "BB"));
        expectedFeature.put("Ввод с использование кнопки Tab", scenario2);

        // A2
        Map<String, List<Token>> actualFeature = Parser.parseFeature(document);

        // A3
        Assert.assertEquals(expectedFeature, actualFeature);
    }
}
