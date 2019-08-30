package com.skindow.logAop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/8/8.
 * */
@Component
@Aspect
@Slf4j
public class LogAspect {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Pointcut("execution(public * com.skindow.controller..*.*(..))")//切入点描述 这个是controller包的切入点
    public void webLog() {//签名，可以理解成这个切入点的一个名称
    }
    @Around("webLog()") //环绕通知，执行webLog方法
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        Date startDate = new Date();
        //返回目标方法的签名
        Signature signature = joinPoint.getSignature();
        //返回目标方法所有的参数
        Object[] args = joinPoint.getArgs();
        log.info("{} 调用时间：{}", signature, sdf.format(startDate));
        long start = System.currentTimeMillis();
        //用改变后的参数执行目标方法
        Object object = joinPoint.proceed();
        //目标执行完成并记录结束时间
        long end = System.currentTimeMillis();
        String time = this.formatExecuteTime(end - start);
        log.info("{} 执行时间：{}", signature.toString(), time);
        (new Thread(() -> {
            log.info("{} 方法入参 {}", signature.toString(),new ArrayList<>(Arrays.asList(args)).stream().map(a -> JSON.toJSONString(a)).collect(Collectors.joining(",")));
            log.info("{} 方法出参 {}",signature.toString(), object);
        })).start();
        return object;
    }

    /**通过毫秒计算出时分秒并输出xxmxxsxxms的格式字符
     * @param executeTime
     * @return
     */
    private String formatExecuteTime(long executeTime) {
        long min = executeTime % 3600000L / 60000L;
        long sec = executeTime % 60000L / 1000L;
        long msec = executeTime % 10000L;
        StringBuilder sb = new StringBuilder();
        if(min > 0L) {
            sb.append(min).append("m ");
        }
        if(sec > 0L) {
            sb.append(sec).append("s ");
        }
        sb.append(msec).append("ms");
        return sb.toString();
    }
}
