package haja.pta.util;

import java.util.Properties;

/**
 * @author Harald Jagenteufel
 * 
 */
public class Config {

    protected Properties _configFile;

    public Config(String propertyFile) {
        _configFile = new java.util.Properties();
        try {
            _configFile.load(this.getClass().getClassLoader().
                    getResourceAsStream(propertyFile));
        } catch(Exception eta) {
            eta.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = _configFile.getProperty(key);
        if(value == null) {
            throw new IllegalArgumentException("key " + key
                    + " in config file not found!");
        }
        return value;
    }

    public int getInt(String key) {
        return Integer.parseInt(getProperty(key));
    }

}
