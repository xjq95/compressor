package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.4.1.
 * h261视频压缩 只是为了前端动态提取编码器的名字并没什么实质作用
 */
@Component
public class H261Process implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException, InvalidParamsException {
		return new byte[0];
	}
	
	@Override
	public byte[] uncompress(byte[] bytes,
			CompressInfo compressInfo) throws DataFormatException, IOException, InvalidParamsException, InvalidInputEncodedDataFileException {
		return new byte[0];
	}
}
