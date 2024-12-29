package org.hein.core.generator.mybatis;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractMapperGenerator;

/**
 * Mybatis Mapper
 */
@Handler(order = 1, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS)
public class MybatisMapperGenerator extends AbstractMapperGenerator {
}