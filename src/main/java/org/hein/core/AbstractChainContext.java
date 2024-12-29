package org.hein.core;

import org.hein.config.ConfigurationFactory;
import org.hein.core.annotation.Handler;
import org.hein.core.build.BuildTableInfoFilter;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.generator.CodeGenerateFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.hein.common.Constant.GENERATOR_PREFIX;

/**
 * Abstract Chain Context
 */
public class AbstractChainContext<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractChainContext.class);

    private final List<AbstractChainHandler<T>> container = new ArrayList<>();
    private volatile Boolean loaded = false;

    private static final String SCAN_ROOT_PACKAGE_NAME = "org.hein.core";
    private static final GenerateTypeEnum GENERATE_TYPE =
            GenerateTypeEnum.of(ConfigurationFactory.getInstance().getAsString(GENERATOR_PREFIX + "generate-type"));

    public AbstractChainContext() {
        if (!loaded) {
            synchronized (this) {
                if (!loaded) {
                    load0();
                    loaded = true;
                }
            }
        }
    }

    public void handler(T data) {
        container.forEach(each -> each.handle(data));
    }

    private void load0() {
        loadBuildFilter();
        loadGenerateFilter();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void loadGenerateFilter() {
        AnnotationScanner scanner = AnnotationScanner.getInstance();
        Map<CodeGenerateFilter, Handler> handlerMap =
                scanner.toMapScan(SCAN_ROOT_PACKAGE_NAME, CodeGenerateFilter.class, Handler.class);
        List<AbstractChainHandler<T>> handlers = handlerMap.entrySet().stream()
                .filter(entry -> entry.getValue().generateType() == GENERATE_TYPE)
                .sorted(Comparator.comparingInt(entry -> entry.getValue().order()))
                .map(entry -> ((AbstractChainHandler<T>) entry.getKey())).toList();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Generate chain filter: {}", handlers);
        }
        container.addAll(handlers);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void loadBuildFilter() {
        AnnotationScanner scanner = AnnotationScanner.getInstance();
        Map<BuildTableInfoFilter, Handler> handlerMap =
                scanner.toMapScan(SCAN_ROOT_PACKAGE_NAME, BuildTableInfoFilter.class, Handler.class);
        List<AbstractChainHandler<T>> handlers = handlerMap.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().order()))
                .map(entry -> ((AbstractChainHandler<T>) entry.getKey())).toList();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Build chain filter: {}", handlers);
        }
        container.addAll(handlers);
    }
}