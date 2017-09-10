package beans.Aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Component,
 * @service,
 * @Controller,
 * 都负责将标注的类添加到xml中作为bean元素存在,只不过标注后使用的场景不一样
 * @author Administrator
 *可以将通知理解为要扩展的业务的一个步骤
 *try{
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
 */


@Aspect
@Component
@Order(2)
public class LogAspect {

    //定义前置通知,需要使用@Before
    //注解中的内容为一个切入点(Pointcut),还可以看成是若干个连接点(JoinPoint)的集合
    //@Before("设置通知应用在哪一个切入点上")
    //这个连接点对应具体的一个业务方法对象
    @Before("bean(petServiceImpl)")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName= joinPoint.getSignature().getName();
        System.out.println(methodName + "\t前置方法开始执行");
    }

    //定义后置通知,设置通知应用在哪一个切入点上
    //方法返回之后执行
    @AfterReturning("bean(petServiceImpl)")
    public void afterReturningMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println( methodName+"\t后置通知：方法执行结束...");
    }

    /**
     * 方法抛出异常以后执行
     */
    @AfterThrowing("bean(petServiceImpl)")
    public void exceptionMethod() {
        System.out.println("method.after.throwException...");
    }

    /**
     * 最终通知，方法执行结束之后执行
     * 相当于在finally代码块执行
     */
    @After("bean(petServiceImpl)")
    public void afterMethod() {
        System.out.println("method.after.finally...");
    }



}
