package c.util;

import java.io.*;
import java.util.Properties;

public class ReaderProperties {
    public static Properties read(String file) {
        Properties properties;
        try (InputStream inputStream = ReaderProperties.class.getClassLoader().getResourceAsStream(file)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
