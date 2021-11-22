package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//@EnableLoadTimeWeaving
@Aspect
@Component
public class LogAdvice {
    //execution은 메소드 실행, bean은 특정 대상, within은 패키지
    private Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    
    public LogAdvice(){
        logger.info("logAdvice 생성");
    }

    @Pointcut("execution(* service.*.*(..))"
//    +"||execution(* )"
    )
    public void commonExec(){}

    @Pointcut("execution(* service.*.*(..))")
    public void service(){}

    @Before("commonExec()")
    public void beforeLogAdvice(JoinPoint jp){
        logger.info(jp.getSignature().getName() + "메쏘드사용한돠아");
        logger.info(jp.getArgs() + " 알규멘또다아~");
    }
    @AfterReturning(value = "commonExec()", returning = "returnValue")
    public void afterLogAdvice(JoinPoint jp, Object returnValue){
        logger.info(jp.getSignature().getName() + " 메소드 끝낫똬아");
        logger.info("리턴값 : " + returnValue);


    }



    @Around("service()")
    public void AroundLogAdvice(ProceedingJoinPoint jp){
        logger.info(jp.getArgs().toString());
        logger.info(jp.getKind().toString());
        logger.info(jp.getThis().toString());
        logger.info(jp.getSourceLocation().toString());
        logger.info(jp.getTarget().toString());
    }

}
