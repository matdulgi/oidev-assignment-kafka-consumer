package common;

import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonFunctions {
    private static Integer loadPropSec;


    public static Properties loadProperties(String propFilePath) {
        if (loadPropSec == null || loadPropSec != 0) loadPropSec = 0;
        Properties properties = new Properties();
        InputStream inputStream = null;
        System.out.println("try to load Properties file \'" + propFilePath + "\'");
        try {
            loadPropSec++;
            switch (loadPropSec) {
                case 1:
                    inputStream = new ClassPathResource(propFilePath).getInputStream();
                    break;
                case 2:
                    inputStream = ClassLoader.getSystemResourceAsStream(propFilePath);
                    break;
                case 3:
                    //tmp
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            properties.load(inputStream);
        } catch (IOException e) {
            if (loadPropSec >= 3) e.printStackTrace();
            else {
                System.out.println("failed to load " + propFilePath);
                System.out.println("try again");
                loadProperties(propFilePath);
            }

        }
        return properties;
    }
}
