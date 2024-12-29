package org.hein.core.generator;

import org.hein.core.AbstractChainHandler;

/**
 * Code Generate Filter
 */
public interface CodeGenerateFilter<T> extends AbstractChainHandler<T> {

    @Override
    default String mark() {
        return "CodeGenerate";
    }
}