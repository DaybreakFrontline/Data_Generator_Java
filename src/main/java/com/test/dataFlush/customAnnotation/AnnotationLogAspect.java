package com.test.dataFlush.customAnnotation;

import com.test.dataFlush.peopleBase.pojo.UserBasic;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 关于 ProceedingJoinPoint
 *    String toString();         //连接点所在位置的相关信息
 *    Object getThis();         //返回AOP代理对象，也就是com.sun.proxy.$Proxy18
 *    Object getTarget();       //返回目标对象，一般我们都需要它或者（也就是定义方法的接口或类，为什么会是接口呢？这主要是在目标对象本身是动态代理的情况下，例如Mapper。所以返回的是定义方法的对象如aoptest.daoimpl.GoodDaoImpl或com.b.base.BaseMapper<T, E, PK>）
 *    Object[] getArgs();       //返回被通知方法参数列表
 *    Signature getSignature();  //返回当前连接点签名  其getName()方法返回方法的FQN，如void aoptest.dao.GoodDao.delete()或com.b.base.BaseMapper.insert(T)(需要注意的是，很多时候我们定义了子类继承父类的时候，我们希望拿到基于子类的FQN，这直接可拿不到，要依赖于AopUtils.getTargetClass(point.getTarget())获取原始代理对象，下面会详细讲解)
 *    SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置
 *    String getKind();        //连接点类型
 *    StaticPart getStaticPart(); //返回连接点静态部分
 *
 */

@Component      // Spring的一个通用注入注解，取代了之前的xml配置
@Aspect         // AOP切面
public class AnnotationLogAspect {

    /**
     * 声明切点， s
     */
    @Pointcut("@annotation(com.test.dataFlush.customAnnotation.AnnotationLog)")
    private void pointcut(){}


    /**
     * @Before 声明了 通知内容，在具体的通知中，通过 @annotation(logger) 拿到自定义的注解对象
     * @param joinPoint
     */
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint){

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        AnnotationLog myLog = method.getAnnotation(AnnotationLog.class);
        System.out.println("----- 日志内容为[" + myLog.value() + "] -------");

        // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
        Object[] args = joinPoint.getArgs();                // 参数值
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames(); // 参数名

        Map<String, Object> map = new HashMap<String, Object>();
        // 参数值
        for (int i = 0; i < argNames.length; i++) {
            map.put(argNames[i], args[i]);
        }
        UserBasic userBasic = (UserBasic)map.get("userBasic");
        userBasic.setAddres("AAAAAAAAAAAAAAAAAAA");

    }

}
