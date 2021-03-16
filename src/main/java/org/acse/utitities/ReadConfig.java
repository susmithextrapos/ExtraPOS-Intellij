package org.acse.utitities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    Properties pro;

    public ReadConfig() {

        File src = new File("./src/main/java/org/acse/resources/data.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            try {
                pro.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getApplicationURL() {

        String url = pro.getProperty("baseURL");
        return url;

    }

    public String getUsername() {

        String uname = pro.getProperty("username");
        return uname;
    }

    public String getPassword() {

        String password = pro.getProperty("password");
        return password;
    }

    public String getChromePath() {

        String chromePath = pro.getProperty("chromePath");
        return chromePath;

    }

    public String getFirefoxPath() {

        String firefoxPath = pro.getProperty("firefoxPath");
        return firefoxPath;

    }

    //give this
    public String getInventoryOutputPath() {

        String InventoryOutputPath = pro.getProperty("InventoryOutput");
        return InventoryOutputPath;
    }

    public String getInventoryInputPath() {

        String InventoryInputPath = pro.getProperty("InventoryInput");
        return InventoryInputPath;
    }

}
