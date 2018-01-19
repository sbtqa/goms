package ru.sbtqa.tag.goms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sbtqa.tag.goms.objects.Model;
import ru.sbtqa.tag.goms.objects.Operator;
import ru.sbtqa.tag.goms.objects.Symbol;
import ru.sbtqa.tag.goms.objects.Token;

public class Reader {

    /**
     * Get symbol-operator map by the specified path
     *
     * @param absolutePath a pathname string
     * @return the symbol-operator map
     * @throws java.io.FileNotFoundException if file not found
     */
    public static Map<Symbol, Operator> getModel(String absolutePath) throws FileNotFoundException {
        return getModel(new FileInputStream(new File(absolutePath)));
    }

    /**
     * Get symbol-operator map by the input stream
     *
     * @param json a json contents
     * @return the symbol-operator map
     */
    public static Map<Symbol, Operator> getModel(InputStream json) {
        Map<Symbol, Operator> map = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Model model = mapper.readValue(json, Model.class);
            model.getOperator().forEach(operator -> map.put(operator.getSymbol(), operator));
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, "Error while reading model json", ex);
        }

        return map;
    }

    /**
     * Get action-token map by the specified path
     *
     * @param absolutePath a pathname string
     * @return the action-token map
     * @throws java.io.FileNotFoundException if file not found
     */
    public static Map<String, Token> getActions(String absolutePath) throws FileNotFoundException {
        return getActions(new FileInputStream(new File(absolutePath)));
    }

    /**
     * Get action-token map by the input stream
     *
     * @param json a json contents
     * @return the action-token map
     */
    public static Map<String, Token> getActions(InputStream json) {
        HashMap<String, Token> map = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Token.class);
            map = mapper.readValue(json, mapType);
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, "Error while reading actions json", ex);
        }

        return map;
    }
    
    public static InputStream readFileFromResources(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
    }
}
