package dev.kostromdan.mods.netjs.utils;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.util.JsonIO;
import dev.latvian.mods.kubejs.util.MapJS;

import java.util.LinkedHashMap;

public interface NetJSUtils {

        static LinkedHashMap<String, Object> parseRawJsonToMap(String raw_text) {
                JsonObject parsing_result = (JsonObject) JsonIO.parseRaw(raw_text); // Parsing raw json string response to JsonObject
                return (LinkedHashMap<String, Object>) MapJS.of(parsing_result); // Converting Json classes to Java classes
        }
}
