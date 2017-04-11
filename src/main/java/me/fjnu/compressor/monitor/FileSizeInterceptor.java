package me.fjnu.compressor.monitor;

import me.fjnu.compressor.domain.CompressInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by xujiaqi on 17.3.13.
 * 计算压缩前后文件大小的切面
 */
@Aspect
@Component
@Order(1)
public class FileSizeInterceptor {
	private static Log logger = LogFactory.getLog(FileSizeInterceptor.class);
	
	// service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
	
	/**
	 * 统计方法执行耗时Around环绕通知
	 *
	 * @param joinPoint
	 * @return
	 */
	@Before("execution(* me.fjnu.compressor.service.*.*(..)) && args(compressInfo, ..)")
	public void beforeFileSize(JoinPoint joinPoint, CompressInfo compressInfo) {
		compressInfo.setBeforeSize(compressInfo.getMultipartFile().getSize());
	}
	
	@AfterReturning(value = "execution(* me.fjnu.compressor.service.*.*(..))",
			returning = "compressInfo")
	public void afterFileSize(CompressInfo compressInfo) {
		long size = FileUtils.sizeOf(compressInfo.getAfterFile());
		compressInfo.setAfterSize(size);
	}
}
