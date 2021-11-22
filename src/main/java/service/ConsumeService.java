package service;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class ConsumeService {
    private Map<String, String> setConfig;

    ConsumeService() {
//        if (kafkaConfig == null) {
//            kafkaConfig = serConfig();
//        }
    }

    public Map setconfig() {
        Map configMap = new HashMap();
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("consume-config.properties");
        try
        {
            properties.load(inputStream);
            properties.forEach((k, v) -> {
                        System.out.println(k);
                        System.out.println(v);
                        configMap.put(k,v);
                    }
            );
            System.out.println(inputStream.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        properties.load();
        return configMap;
    }

}
