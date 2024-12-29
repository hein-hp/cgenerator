package org.hein.core;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Annotation Scanner
 */
public final class AnnotationScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationScanner.class);

    public <T, A extends Annotation> Map<T, A> toMapScan(String packageName, Class<T> clazz, Class<A> anno) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(anno);
        Map<T, A> result = new HashMap<>();
        for (Class<?> annotatedClass : annotatedClasses) {
            if (annotatedClass.isAnnotationPresent(anno) && clazz.isAssignableFrom(annotatedClass)) {
                try {
                    @SuppressWarnings("unchecked")
                    T instance = (T) annotatedClass.getDeclaredConstructor().newInstance();
                    result.put(instance, annotatedClass.getAnnotation(anno));
                } catch (Exception e) {
                    LOGGER.error("Failed to instantiate {}", annotatedClass.getName(), e);
                }
            }
        }
        return result;
    }

    public static AnnotationScanner getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private AnnotationScanner() {
    }

    private static class SingletonHolder {
        private static final AnnotationScanner INSTANCE = new AnnotationScanner();
    }
}