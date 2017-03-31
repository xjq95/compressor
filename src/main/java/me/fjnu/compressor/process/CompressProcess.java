package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.12.
 * 编码器接口
 */
public interface CompressProcess {
	/**
	 * 传入字节数组，然后压缩
	 * @param srcBytes 待压缩的字节数组
	 * @param compressInfo
	 * @return 压缩完毕的字节数组
	 * @throws IOException
	 */
	byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException, InvalidParamsException;
	
	/**
	 * 传入压缩过的字节数组，解压缩
	 * @param bytes 压缩过的字节数组
	 * @param compressInfo
	 * @return 返回解压缩的字节数组
	 * @throws DataFormatException
	 * @throws IOException
	 */
	byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException, InvalidParamsException, InvalidInputEncodedDataFileException;
}
