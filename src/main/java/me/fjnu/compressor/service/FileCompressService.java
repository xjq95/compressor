package me.fjnu.compressor.service;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;
import me.fjnu.compressor.process.CompressProcess;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.12.
 * todo
 */
@Service
public class FileCompressService {
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 压缩
	 *
	 * @param compressInfo 压缩信息
	 * @return
	 * @throws IOException
	 * @throws InvalidParamsException
	 */
	public CompressInfo compress(CompressInfo compressInfo) throws IOException, InvalidParamsException {
		CompressProcess compressProcess = (CompressProcess) applicationContext
				.getBean(compressInfo.getCompressProcess());
		byte[] bytes = compressInfo.getMultipartFile().getBytes();
		byte[] done = compressProcess.compress(bytes, compressInfo);
		File file = new File(compressInfo.getAbsolutePath());
		FileUtils.writeByteArrayToFile(file, done);
		compressInfo.setAfterFile(file);
		return compressInfo;
	}
	
	/**
	 * 解压缩
	 *
	 * @param compressInfo 压缩信息
	 * @return
	 * @throws IOException
	 * @throws InvalidInputEncodedDataFileException
	 * @throws InvalidParamsException
	 * @throws DataFormatException
	 */
	public CompressInfo unCompress(CompressInfo compressInfo) throws IOException,
			InvalidInputEncodedDataFileException, InvalidParamsException, DataFormatException {
		CompressProcess compressProcess = (CompressProcess) applicationContext
				.getBean(compressInfo.getCompressProcess());
		byte[] bytes = compressInfo.getMultipartFile().getBytes();
		bytes = compressProcess.uncompress(bytes, compressInfo);
		File file = new File(compressInfo.getAbsolutePath());
		FileUtils.writeByteArrayToFile(file, bytes);
		compressInfo.setAfterFile(file);
		return compressInfo;
	}
	
	/**
	 * 获取压缩列表
	 *
	 * @return 算法列表
	 */
	public List<String> getProcessList() {
		return Arrays.asList(applicationContext.getBeanNamesForType(CompressProcess.class));
	}
}
