package com.fucota.base.core;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


//@Component
@Slf4j
public class LanguageConfigMessage {
    private static Map<String, String> enMessage = new HashMap<>();

    @SneakyThrows
    public static void initMap() {
        File file = new ClassPathResource("./en_message.json").getFile();
        JsonReader reader = new JsonReader(new FileReader(file));
        enMessage = new Gson().fromJson(
            reader, new TypeToken<HashMap<String, Object>>() {
            }.getType()
        );
        log.info("Init message error success");
    }

    public static String getMessage(String key) {
        log.debug("Key {}:value: {}", key, enMessage.get(key));
        return enMessage.get(key);
    }

}
