package com.mgj.base.annotation;

import java.lang.annotation.*;

/**
 * Column , apply to persistent property
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoIncre {
}
