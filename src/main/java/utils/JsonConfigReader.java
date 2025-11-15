package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonConfigReader {

    private static JsonNode root;

    static {
        try {
            File file = new File("config/capabilities.json");
            root = new ObjectMapper().readTree(file);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load capabilities.json", e);
        }
    }

    public static JsonNode getPlatformConfig(String platform) {
        JsonNode node = root.get(platform.toLowerCase());
        if (node == null) {
            throw new RuntimeException("Platform not found in JSON: " + platform);
        }
        return node;
    }
}
