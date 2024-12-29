package org.hein.core.generator.mybatisplus;

import org.hein.core.annotation.Handler;
import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;
import org.hein.core.generator.AbstractControllerGenerator;

/**
 * MybatisPlus Controller
 */
@Handler(order = 4, handlerType = HandlerTypeEnum.GENERATE, generateType = GenerateTypeEnum.MYBATIS_PLUS)
public class MybatisPlusControllerGenerator extends AbstractControllerGenerator {
}