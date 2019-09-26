package com.alumniassociation.common.datamock;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface Mock {

    Class<?> mockClass() default Void.class;

    boolean isOpen() default true;

}
