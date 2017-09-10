package beans.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * *try{
 * 1.@before
 *  saveObject()
 * 2,@AfterReturning
 *}catch{
 * 3,@AfterThrowing
 *}finally{
 * 4,@After
 *}
 *大致执行顺序
 *先执行try，再执行catch，最后执行finally
 *当执行catch遇到return语句时，直接执行finally，执行结束后再返回catch的return语句
 *
 *通过@PointCut来定义切入点
 *
 *
 */


@Aspect
@Component
@Order(1)//定义切面执行顺序从小到大，先执行的后结束
public class TimeAspect {

    long before;
    long after;
    long during;


    /**
     * 第一个*：找到xml里面的beans.service目录
     * 第二个*：找到beans.service目录下所有类中含有的updateObject()方法
     *
     */
    @Pointcut("execution(* beans.service.*.saveObject(..))")
    public void pointCutMethod() {}

    @Around("pointCutMethod()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint){

        Object result =null;

        before=System.nanoTime();
        System.out.println("before=  " +before);

        try {
            result=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        after = System.nanoTime();
        System.out.println("after=  " +after);

        during=after-before;
        System.out.println("during=  " + during);

        return result;


    }



}
