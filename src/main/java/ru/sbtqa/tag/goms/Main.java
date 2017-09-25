package ru.sbtqa.tag.goms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ru.sbtqa.tag.goms.objects.Operator;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;
import ru.sbtqa.tag.goms.processing.Processor;
import ru.sbtqa.tag.goms.utils.Reader;
public class Main {

    public static void main(String[] args) {
        Map<Symbol, Operator> modelKlm = Reader.getMap("/tmp/model-klm.json");
        
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(Symbol.O, "* пользователь находится на странице \"Форма логина\" "));
        tokens.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Логин\" \"логинннн\""));
        tokens.add(new Token(Symbol.K, "* пользовтаель (прикладывает таблетку) ", true));
        tokens.add(new Token(Symbol.O, "* пользователь находится на странице \"Выбор операции\" "));
        tokens.add(new Token(Symbol.BB, "* пользователь (нажимает кнопку) \"Платежи\" "));
        tokens.add(new Token(Symbol.O, "* пользователь находится на странице \"Продуктовая часть\" "));
        tokens.add(new Token(Symbol.K, "* пользователь (нажимает клавишу) \"Enter\" "));
        tokens.add(new Token(Symbol.K, "* пользователь (нажимает клавишу) \"Up\" "));
        tokens.add(new Token(Symbol.K, "* пользователь (сканирует штрихкод) ", true));
        tokens.add(new Token(Symbol.K, "* пользователь (нажимает клавишу) \"Enter\" "));
        tokens.add(new Token(Symbol.O, "* пользователь находится на странице \"Реквизиты\" "));
        tokens.add(new Token(Symbol.K, "* пользователь (нажимает клавишу) \"Enter\" "));
        tokens.add(new Token(Symbol.O, "* пользователь находится на странице \"Сумма\" "));
        tokens.add(new Token(Symbol.BB, "* пользователь (нажимает кнопку) \"Редактировать\" "));
        tokens.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Сумма к оплате\" \"10\" "));
        tokens.add(new Token(Symbol.K, "* пользователь (нажимает клавишу) \"Enter\" "));
        tokens.add(new Token(Symbol.O, "* пользователь находится на странице \"Дополнительные параметры\" "));
        tokens.add(new Token(Symbol.BB, "* пользователь (нажимает кнопку) \"Клиент отказался предоставлять номер\" "));
        tokens.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Проверочный код\" \"г4шнрм\" "));
        tokens.add(new Token(Symbol.K, "* пользователь (нажимает клавишу) \"Enter\" "));
        tokens.add(new Token(Symbol.O, "* пользователь находится на странице \"ИНН\" "));
        tokens.add(new Token(Symbol.K, "* пользователь (нажимает клавишу) \"Enter\" "));
        tokens.add(new Token(Symbol.O, "* пользователь находится на странице \"Получено с клиента\" "));
        tokens.add(new Token(Symbol.F, "* в фокусе находится элемент \"Получено с клиента\""));
        tokens.add(new Token(Symbol.KK, "* пользователь (заполняет поле) \"Получено с клиента\" \"40\" "));
        tokens.add(new Token(Symbol.K, "* пользователь (нажимает клавишу) \"Enter\" "));
        tokens.add(new Token(Symbol.BB, "* пользователь (нажимает кнопку) \"Выход\" "));
        tokens.add(new Token(Symbol.BB, "* пользователь (нажимает кнопку) \"Выход на экран АС ФС\" "));

        List<Token> workflow = Processor.process(tokens);
        
        workflow.forEach((token) -> {
            Operator operator = modelKlm.get(token.getOperator());
            if (token.getOperator() == Symbol.O) {
                System.out.println("                                                " + token.getDescription());
            } else {
                System.out.format("%-4s%.2f  %-2d  %-30s    %-50s%n", token.getOperator(), (float) operator.getTime() / 1000, token.getMultiplier(), operator.getDescription(), token.getDescription());
            }
        });
        System.out.println((float) workflow.stream().mapToInt(token -> modelKlm.get(token.getOperator()).getTime() * token.getMultiplier()).sum() / 1000);
    }
}
