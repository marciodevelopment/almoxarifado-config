package br.org.curitiba.ici.saude.almoxarifado.common.query;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface QuerySearch {
  String columns() default "";

  String query() default "";

  Class<?> viewResult();
}


