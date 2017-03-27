package me.fjnu.compressor.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;

/**
 * Created by xujiaqi on 17.3.11.
 * todo
 */

@Data
@ToString
public class CompressInfo {
	@NotNull
	private MultipartFile multipartFile; //待压缩文件
	private File afterFile; //处理完的文件
	@NotNull
	private int action = 0; //解压还是压缩
	@NotNull
	private String compressProcess; //使用的压缩算法
	private long beforeSize; //压缩前文件大小
	private long afterSize;
	private long startTime; //压缩前时间戳
	private long endTime; //压缩后时间戳
	private long compressRate; //压缩率
	private long compressTime; //压缩用时
	private long unCompressTime; //压缩用时
	private int compressLevel; //压缩等级（压缩了多少遍）
	private double compressM; //压缩因子
	private double compressN; //压缩效率
	private String contentType; //文件类型
	private String filename; //文件名
	@NotNull
	private String absolutePath; //路径
	
	public double getCompressRate() {
		return (float) afterSize / beforeSize * 100.0;
	}
	public double getCompressM() {
		return (float) beforeSize / afterSize * 1.0;
	}
	public long getCompressTime() {
		return endTime - startTime;
	}
	
	
}
