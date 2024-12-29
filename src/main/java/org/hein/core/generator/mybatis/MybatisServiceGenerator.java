package org.hein.core.generator.mybatis;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractServiceGenerator;

/**
 * Mybatis Service
 */
@Handler(order = 2, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS)
public class MybatisServiceGenerator extends AbstractServiceGenerator {
}