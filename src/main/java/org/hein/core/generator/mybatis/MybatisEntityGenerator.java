package org.hein.core.generator.mybatis;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractEntityGenerator;

/**
 * Mybatis Entity
 */
@Handler(order = 0, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS)
public class MybatisEntityGenerator extends AbstractEntityGenerator {
}