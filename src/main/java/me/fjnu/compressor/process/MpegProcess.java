package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;
import org.jcodec.codecs.mpeg12.MPEGEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.30.
 * todo
 */
@Component
public class MpegProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException, InvalidParamsException {
		MPEGEncoder encoder = new MPEGEncoder();
		
		return new byte[0];
	}
	
	@Override
	public byte[] uncompress(
			byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException, InvalidParamsException, InvalidInputEncodedDataFileException {
		return new byte[0];
	}
}
