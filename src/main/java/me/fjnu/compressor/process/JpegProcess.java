package me.fjnu.compressor.process;

import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.27.
 * todo
 */
public class JpegProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes) throws IOException, InvalidParamsException {
		return new byte[0];
	}
	
	@Override
	public byte[] uncompress(
			byte[] bytes) throws DataFormatException, IOException, InvalidParamsException, InvalidInputEncodedDataFileException {
		return new byte[0];
	}
}
