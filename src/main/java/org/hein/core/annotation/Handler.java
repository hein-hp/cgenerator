package org.hein.core.annotation;

import org.hein.core.enums.GenerateTypeEnum;
import org.hein.core.enums.HandlerTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Handler {

    int order() default -1;

    HandlerTypeEnum handlerType() default HandlerTypeEnum.NULL;

    GenerateTypeEnum generateType() default GenerateTypeEnum.NULL;
}