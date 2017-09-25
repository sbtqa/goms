package ru.sbtqa.tag.goms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sbtqa.tag.goms.objects.Model;
import ru.sbtqa.tag.goms.objects.Operator;
import ru.sbtqa.tag.goms.objects.Symbol;

public class Reader {

    private static final String DEFAULT_JSON_PATH = "model/model-klm.json";

    /**
     * Get symbol-operator map by the default path
     *
     * @return the symbol-operator map
     */
    public static Map<Symbol, Operator> getMap() {
        ClassLoader classLoader = Reader.class.getClassLoader();

        return getMap(classLoader.getResource(DEFAULT_JSON_PATH).getFile());
    }

    /**
     * Get symbol-operator map by the specified path
     *
     * @param json a pathname string
     * @return the symbol-operator map
     */
    public static Map<Symbol, Operator> getMap(String json) {
        Map<Symbol, Operator> map = new HashMap<>();

        try {
            // Convert JSON string from file to Object
            ObjectMapper mapper = new ObjectMapper();
            Model model = mapper.readValue(new File(json), Model.class);
            model.getOperator().forEach(operator -> map.put(operator.getSymbol(), operator));
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, "Error while reading model json", ex);
        }

        return map;
    }
}
