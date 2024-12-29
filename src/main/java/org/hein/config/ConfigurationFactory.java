package org.hein.config;

import org.hein.GenerateBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hein.common.Constant.COMMA;
import static org.hein.common.Constant.CONFIG_FILE_NAME;

/**
 * Configuration Factory
 */
public final class ConfigurationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFactory.class);

    public static volatile Configuration INSTANCE;

    public static Configuration getInstance() {
        if (INSTANCE == null) {
            synchronized (Configuration.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildConfiguration();
                }
            }
        }
        return INSTANCE;
    }

    private static Configuration buildConfiguration() {
        Yaml yaml = new Yaml();
        Configuration cfg = new Configuration();
        try (InputStream in = GenerateBootstrap.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            if (in == null) {
                throw new IllegalArgumentException("File not found!");
            }
            Map<String, Object> root = yaml.load(in);
            Properties properties = toProperties(root, "");
            properties.forEach((k, v) -> cfg.put((String) k, (String) v));
        } catch (Exception e) {
            LOGGER.error("Error reading config", e);
        }
        return cfg;
    }

    private static Properties toProperties(Object obj, String prefix) {
        Properties properties = new Properties();
        if (obj instanceof Map<?, ?>) {
            ((Map<?, ?>) obj).forEach((key, value) -> {
                String newPrefix = prefix.isEmpty() ? key.toString() : prefix + "." + key;
                properties.putAll(toProperties(value, newPrefix));
            });
        } else if (obj instanceof List<?> list) {
            if (!prefix.isEmpty()) {
                properties.setProperty(prefix,
                        list.stream().map(Object::toString).collect(Collectors.joining(COMMA)));
            }
        } else {
            properties.setProperty(prefix, obj == null ? "" : obj.toString());
        }
        return properties;
    }
}