package me.fjnu.compressor.monitor;

import me.fjnu.compressor.domain.CompressInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by xujiaqi on 17.3.13.
 * todo
 */
@Aspect
@Component
@Order(2)
public class TimeInterceptor {
	
	private static Log logger = LogFactory.getLog(TimeInterceptor.class);
	
	// 一分钟，即1000ms
	private static final long ONE_MINUTE = 1000;
	
	// service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
	private static final String POINT = "execution(* me.fjnu.compressor.service..*(..))";
	
	/**
	 * 统计方法执行耗时Around环绕通知
	 *
	 * @param joinPoint
	 * @return
	 */
	@Around("execution(* me.fjnu.compressor.service.*.*(..)) && args(compressInfo, ..)")
	public Object timeAround(ProceedingJoinPoint joinPoint, CompressInfo compressInfo) {
		// 定义返回对象、得到方法需要的参数
		Object obj = null;
		Object[] args = joinPoint.getArgs();
		long startTime = System.currentTimeMillis();
		
		try {
			obj = joinPoint.proceed(args);
		} catch (Throwable e) {
			logger.error("统计某方法执行耗时环绕通知出错", e);
		}
		long endTime = System.currentTimeMillis();
		
		compressInfo.setStartTime(startTime);
		compressInfo.setEndTime(endTime);
		return obj;
	}
}
