package com.mycompany.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is for configuration of the whole project including database, port
 * and so on.
 *
 * @author Sanjeev Panta <sanjeev@moco.com.np>
 * @Creation Date Dec 5, 2019
 */
public class Config {

    static final Properties PROP = new Properties();

    static {
        try (InputStream in = new FileInputStream("etc/config.properties")) {
            PROP.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return PROP.getProperty(key);
    }
}
