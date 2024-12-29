package org.hein.core.build;

import org.hein.core.AbstractChainHandler;

public interface BuildTableInfoFilter<T> extends AbstractChainHandler<T> {

    @Override
    default String mark() {
        return "TableInfoBuild";
    }
}