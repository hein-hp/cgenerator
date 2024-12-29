package org.hein;

import org.hein.core.AbstractChainContext;

import java.util.ArrayList;

/**
 * Generate Bootstrap
 */
public final class GenerateBootstrap {

    public static void doGenerate() {
        new AbstractChainContext<>().handler(new ArrayList<>());
    }
}