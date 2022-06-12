package com.fox.assignment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    protected static final Logger log = LogManager.getLogger(Config.class);

    public static String HTTP_SOCKET_TIMEOUT = getProperty("http.socket.timeout");

    /**Collection of properties read from the automation properties file.*/
    private static final Properties properties;
    static {
        Properties tmp;
        try {
            tmp = new Properties();
            String configPath="src"+ File.separator+"main"+ File.separator+"resources"+ File.separator+ "config.properties";
            tmp.load(new FileReader(configPath));
            log.info("AT Configuration file loaded !");
        } catch (IOException e) {
            log.fatal("Cannot load automation properties : "+e.getMessage());
            tmp = null;
        }
        properties = tmp;
        tmp = null; // Cleanup
    }

    public static String getProperty(String key, String defaultValue){
        return ((properties.getProperty(key, defaultValue).isEmpty()) ? defaultValue : properties.getProperty(key, defaultValue));
    }

    public static String getProperty(String key){
        String defaultValue="";
        return ((getProperty(key, defaultValue).isEmpty()) ? defaultValue : getProperty(key, defaultValue));
    }

}
