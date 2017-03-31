package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by xujiaqi on 17.3.13.
 * todo
 */
@Component
public class LzmaProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		LZMACompressorOutputStream lzOut = new LZMACompressorOutputStream(out);
		lzOut.write(srcBytes);
		lzOut.close();
		return out.toByteArray();
	}
	
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws DataFormatException, IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		LZMACompressorInputStream lzI = new LZMACompressorInputStream(in);
		byte[] buffer = new byte[2048];
		int n;
		while (-1 != (n = lzI.read(buffer))) {
			out.write(buffer, 0, n);
		}
		out.close();
		lzI.close();
		return out.toByteArray();
		
		
	}
}
