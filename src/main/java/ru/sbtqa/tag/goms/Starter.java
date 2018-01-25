package ru.sbtqa.tag.goms;

import java.util.ArrayList;
import java.util.List;
import ru.sbtqa.tag.goms.processing.Processor;
import ru.sbtqa.tag.goms.tokens.Token;
import ru.sbtqa.tag.goms.tokens.TokenFactory;

public class Starter {

    public static void main(String[] args) {
        List<Token> feature = new ArrayList<>();
        feature.add(TokenFactory.createToken("* пользователь находится на странице \"Ввод паспортных данных\"", "O"));
        feature.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Серия\" \"5007\"", "KK"));
        feature.add(TokenFactory.createToken("* пользователь (прикладывает таблетку)", "TK"));
        feature.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Номер\" \"483711\"", "KK"));
        feature.add(TokenFactory.createToken("* пользователь (нажимает кнопку) \"Далее\"", "BB"));

        List<Token> actualWorkflow = Processor.process(feature);
        for (Token token : actualWorkflow) {
            System.out.println(token);
        }
//        
//        List<Token> feature = new ArrayList<>();
//        feature.add(TokenFactory.createToken("* пользователь находится на странице \"Ввод паспортных данных\"", "O"));
//        feature.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Серия\" \"5007\"", "KK"));
//        feature.add(TokenFactory.createToken("* пользователь (нажимает клавишу) \"Tab\"", "K"));
//        feature.add(TokenFactory.createToken("* в фокусе находится элемент \"Номер\"", "F"));
//        feature.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Номер\" \"483711\"", "KK"));
//        feature.add(TokenFactory.createToken("* пользователь (нажимает кнопку) \"Далее\"", "BB"));
//
//        List<Token> actualWorkflow = Processor.process(feature);
//        for (Token token : actualWorkflow) {
//            System.out.println(token.getOperator().getSymbol());
//        }
//        
//        List<Token> feature = new ArrayList<>();
//        feature.add(TokenFactory.createToken("* пользователь находится на странице \"Ввод паспортных данных\"", "O"));
//        feature.add(TokenFactory.createToken("* в фокусе находится элемент \"Серия\"", "F"));
//        feature.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Серия\" \"5007\"", "KK"));
//        feature.add(TokenFactory.createToken("* в фокусе находится элемент \"Номер\"", "F"));
//        feature.add(TokenFactory.createToken("* пользователь (заполняет поле) \"Номер\" \"483711\"", "KK"));
//        feature.add(TokenFactory.createToken("* пользователь (нажимает клавишу) \"Enter\"", "K"));
//
//        List<Token> actualWorkflow = Processor.process(feature);
//        for (Token token : actualWorkflow) {
//            System.out.println(token.getOperator().getSymbol());
//        }
    }
}
