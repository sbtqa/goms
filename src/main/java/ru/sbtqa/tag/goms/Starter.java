package ru.sbtqa.tag.goms;

import java.util.List;
import java.util.Map;
import ru.sbtqa.tag.goms.output.Export;
import ru.sbtqa.tag.goms.parse.Parser;
import ru.sbtqa.tag.goms.process.Processor;
import ru.sbtqa.tag.goms.process.tokens.Token;

public class Starter {

    public static void main(String[] args) {
        
        String document = "#language:ru\n" +
"\n" +
"Функционал: Ввод паспортных данных\n" +
"\n" +
"Сценарий: Стандартный ввод\n" +
"* пользователь находится на странице \"Ввод паспортных данных\"\n" +
"* пользователь (заполняет поле) \"Серия\" \"5007\"\n" +
"* пользователь (заполняет поле) \"Номер\" \"483711\"\n" +
"* пользователь (нажимает кнопку) \"Далее\"\n" +
" \n" +
"Сценарий: Ввод с использование кнопки Tab\n" +
"* пользователь находится на странице \"Ввод паспортных данных\"\n" +
"* пользователь (заполняет поле) \"Серия\" \"5007\"\n" +
"* пользователь (нажимает клавишу) \"Tab\"\n" +
"* в фокусе находится элемент \"Номер\"\n" +
"* пользователь (заполняет поле) \"Номер\" \"483711\"\n" +
"* пользователь (нажимает кнопку) \"Далее\"\n" +
"\n" +
"Сценарий: Ввод в смарт-поля\n" +
"* пользователь находится на странице \"Ввод паспортных данных\"\n" +
"* в фокусе находится элемент \"Серия\"\n" +
"* пользователь (заполняет поле) \"Серия\" \"5007\"\n" +
"* в фокусе находится элемент \"Номер\"\n" +
"* пользователь (заполняет поле) \"Номер\" \"483711\"\n" +
"* пользователь (нажимает клавишу) \"Enter\"" +
                 "";
        
        Map<String, List<Token>> feature = Parser.parseFeature(document);
        Map<String, List<Token>> processedFeature = Processor.process(feature);
        for (Map.Entry<String, List<Token>> scenario : processedFeature.entrySet()) {
            System.out.println(scenario.getKey());
            for (Token token : scenario.getValue()) {
                System.out.println(token);
            }
        }
        
        Export.writeIntoExcel("/tmp/a.xlsx", processedFeature);
        
//        System.out.println(Statistics.getInfoByScreens(Processor.process(feature)));
//        for (Map.Entry<String, Map<String, Float>> scenario : Statistics.getInfoByScreens(Processor.process(feature)).entrySet()) {
//            System.out.println(scenario.getKey());
//            for (Map.Entry<String, Float> screen : scenario.getValue().entrySet()) {
//                System.out.println(screen.getKey() + "  " + screen.getValue());
//            }
//        }        
        
    }
}
