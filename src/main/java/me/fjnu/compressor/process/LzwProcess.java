package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;
import me.fjnu.compressor.util.lzw.LZWDecoder;
import me.fjnu.compressor.util.lzw.LZWEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.13.
 * lzw算法
 */
@Component
public class LzwProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException, InvalidParamsException {
		return new LZWEncoder().encode(srcBytes);
	}
	
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException, InvalidParamsException, InvalidInputEncodedDataFileException {
		return new LZWDecoder().decode(bytes);
	}
}
