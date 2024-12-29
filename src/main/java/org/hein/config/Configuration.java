package org.hein.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hein.common.Constant.COMMA;

/**
 * Configuration
 */
public class Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    public final Map<String, String> ENV_MAP;

    public Configuration() {
        ENV_MAP = new ConcurrentHashMap<>(16);
    }

    public void put(String key, String value) {
        ENV_MAP.put(key, value);
    }

    public <T> List<T> getAsList(String key, Class<T> innerClazz) {
        if (key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty.");
        }
        String obj = ENV_MAP.get(key);
        if (obj == null) {
            throw new IllegalArgumentException("Key " + key + " not found.");
        }
        try {
            return toListFromString(obj, innerClazz);
        } catch (Exception e) {
            String message = String
                    .format("Failed to convert value '%s' for key '%s' to type %s", obj, key, innerClazz.getName());
            LOGGER.error(message, e);
            throw new IllegalArgumentException(message, e);
        }
    }

    public String getAsString(String key) {
        return get(key, String.class);
    }

    public Integer getAsInteger(String key) {
        return get(key, Integer.class);
    }

    public Long getAsLong(String key) {
        return get(key, Long.class);
    }

    public Boolean getAsBoolean(String key) {
        return get(key, Boolean.class);
    }

    public Double getAsDouble(String key) {
        return get(key, Double.class);
    }

    public Float getAsFloat(String key) {
        return get(key, Float.class);
    }

    public <T> T get(String key, Class<T> clazz) {
        if (key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty.");
        }
        String obj = ENV_MAP.get(key);
        if (obj == null) {
            throw new IllegalArgumentException("Key " + key + " not found.");
        }
        try {
            return convertTypeFromString(obj, clazz);
        } catch (Exception e) {
            String message = String
                    .format("Failed to convert value '%s' for key '%s' to type %s", obj, key, clazz.getName());
            LOGGER.error(message, e);
            throw new IllegalArgumentException(message, e);
        }
    }

    public <T> T getOrDefault(String key, Class<T> clazz, T defaultValue) {
        try {
            return get(key, clazz);
        } catch (ClassCastException e) {
            LOGGER.warn("Failed to cast value for key '{}', returning default value.", key, e);
            return defaultValue;
        }
    }

    private static <T> List<T> toListFromString(String str, Class<T> clazz) throws Exception {
        if (!isSupportedType(clazz)) {
            throw new Exception("Unsupported element type for List: " + clazz.getName());
        }
        List<T> result = new ArrayList<>();
        for (String value : str.split(COMMA)) {
            value = value.trim();
            if (value.isEmpty()) {
                continue;
            }
            result.add(convertTypeFromString(value, clazz));
        }
        return result;
    }

    private static boolean isSupportedType(Class<?> clazz) {
        return clazz == Integer.class || clazz == int.class ||
                clazz == Long.class || clazz == long.class ||
                clazz == Boolean.class || clazz == boolean.class ||
                clazz == Double.class || clazz == double.class ||
                clazz == Float.class || clazz == float.class || clazz == String.class;
    }

    @SuppressWarnings("unchecked")
    private static <T> T convertTypeFromString(String str, Class<T> clazz) throws Exception {
        if (clazz == String.class) {
            return clazz.cast(str);
        } else if (clazz == Integer.class || clazz == int.class) {
            return clazz == Integer.class ? clazz.cast(Integer.valueOf(str)) : (T) Integer.valueOf(str);
        } else if (clazz == Long.class || clazz == long.class) {
            return clazz == Long.class ? clazz.cast(Long.valueOf(str)) : (T) Long.valueOf(str);
        } else if (clazz == Boolean.class || clazz == boolean.class) {
            return clazz == Boolean.class ? clazz.cast(Boolean.valueOf(str)) : (T) Boolean.valueOf(str);
        } else if (clazz == Double.class || clazz == double.class) {
            return clazz == Double.class ? clazz.cast(Double.valueOf(str)) : (T) Double.valueOf(str);
        } else if (clazz == Float.class || clazz == float.class) {
            return clazz == Float.class ? clazz.cast(Float.valueOf(str)) : (T) Float.valueOf(str);
        } else {
            throw new Exception("Unsupported type conversion for class: " + clazz.getName());
        }
    }
}