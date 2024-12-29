package org.hein.core.generator.mybatis;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractControllerGenerator;

/**
 * Mybatis Controller
 */
@Handler(order = 4, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS)
public class MybatisControllerGenerator extends AbstractControllerGenerator {
}