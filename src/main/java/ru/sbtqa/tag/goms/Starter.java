package ru.sbtqa.tag.goms;

import java.io.FileNotFoundException;
import ru.sbtqa.tag.goms.tokens.Token;
import ru.sbtqa.tag.goms.tokens.TokenFactory;

public class Starter {

    public static void main(String[] args) throws FileNotFoundException {
        Token KKToken = TokenFactory.createToken("* пользователь (заполняет поле) \"Серия и номер\" \"4593494790\"", "KK");

        for (Token token : KKToken.rule()) {
            System.out.println(token);
        }
    }
}
