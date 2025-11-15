package utils.factories;

import java.util.HashMap;
import java.util.Map;

public class DriverFactoryRegistry {

    private static final Map<String, IDriverFactory> FACTORIES = new HashMap<>();

    static {
        FACTORIES.put("android", new AndroidDriverFactory());
        FACTORIES.put("ios", new IOSDriverFactory());
    }

    public static IDriverFactory getFactory(String platform) {
        IDriverFactory factory = FACTORIES.get(platform.toLowerCase());
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
        return factory;
    }
}
