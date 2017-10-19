package jfeoks.annotation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DFParam {
    String name();

    ShowPropertyPolicy showPropertyPolicy() default ShowPropertyPolicy.SHOW;

    boolean mdcAware() default false;
}
