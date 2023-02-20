package framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesResourceManager {
    private final String fileName;
    private Properties properties = null;
    public PropertiesResourceManager(String fileName) {
        this.fileName = fileName;
        properties = new Properties();
    }

    public void getPropertiesFromFile() throws FileNotFoundException {
        try {
            InputStream reader = getClass().getClassLoader().getResourceAsStream(fileName);
            properties.load(reader);
            reader.close();
        }
        catch(FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getPropertyValueByKey(String key)
    {
        return properties.getProperty(key);
    }

}
