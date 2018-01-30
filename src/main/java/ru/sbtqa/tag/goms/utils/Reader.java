package ru.sbtqa.tag.goms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sbtqa.tag.goms.model.Model;

public class Reader {

    /**
     * Get symbol-operator map by the specified path
     *
     * @param absolutePath a pathname string
     * @return the symbol-operator map
     * @throws java.io.FileNotFoundException if file not found
     */
    public static Model getModel(String absolutePath) throws FileNotFoundException {
        return getModel(new FileInputStream(new File(absolutePath)));
    }

    /**
     * Get symbol-operator map by the input stream
     *
     * @param json a json contents
     * @return the symbol-operator map
     */
    public static Model getModel(InputStream json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Model.class);
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, "Error while reading model json", ex);
        }
        
        return null;
    }

    public static InputStream readFileFromResources(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
    }
}
