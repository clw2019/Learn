package com.clw.aop;

import com.alibaba.fastjson.JSONObject;
import com.clw.annotation.MyLog;
import com.clw.vo.SendMessageVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {

    public static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.clw.annotation.MyLog)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(JoinPoint joinPoint) {
        Object object = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        String moduleType = myLog.moduleType().getType();
        String operType = myLog.operType().getCode();
        String description = myLog.description();

//        logger.info(moduleType + " : " + operType + " : " + description);
//        System.out.println(moduleType + " : " + operType + " : " + description);

        ProceedingJoinPoint pjp = (ProceedingJoinPoint) joinPoint;
        String className = pjp.getSignature().getDeclaringType().getName();
        String methodName = pjp.getSignature().getName();

//        logger.info("方法名称 ：" + className + "." + methodName);
//        System.out.println("方法名称 ：" + className + "." + methodName);

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) attributes;
        HttpServletRequest request = sra.getRequest();
        String requestMethod = request.getMethod();

//        logger.info("requestMethod: " + requestMethod);
//        System.out.println("requestMethod: " + requestMethod);

        SendMessageVO smVO = new SendMessageVO();
        smVO.setModuleType(moduleType);
        smVO.setOperType(operType);
        smVO.setDescription(description);
        smVO.setMethod(className + "." + methodName);



        try {
            object = pjp.proceed();
            smVO.setLevel("INFO");
            logger.info(JSONObject.toJSONString(smVO));
            return object;
        } catch (Throwable throwable) {
            smVO.setLevel("ERROR");
            logger.error(JSONObject.toJSONString(smVO));
//            throwable.printStackTrace();
            return object;
        }
    }
}
