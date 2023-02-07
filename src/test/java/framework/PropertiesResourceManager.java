package framework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesResourceManager {
    private final String filePath;
    private Properties properties = null;
    public PropertiesResourceManager(String filePath)
    {
        this.filePath = filePath;
    }

    public void getPropertiesFromFile() throws FileNotFoundException {
        try {
            InputStream reader = getClass().getClassLoader().getResourceAsStream(filePath);

            //new FileReader(filePath);
            properties = new Properties();
            properties.load(reader);
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
