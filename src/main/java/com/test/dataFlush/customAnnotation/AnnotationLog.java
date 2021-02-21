package com.test.dataFlush.customAnnotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented     // 注解信息会加入到Java文档中
@Retention(RetentionPolicy.RUNTIME)     // 注解的生命周期，表示注解会被保留到什么阶段。 编译阶段/类加载阶段/运行阶段
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})         // 注解的作用位置， METHOD代表只能作在方法上
public @interface AnnotationLog {

    String value() default "";

}
