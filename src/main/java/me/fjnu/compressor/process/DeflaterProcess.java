package me.fjnu.compressor.process;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by xujiaqi on 17.3.11.
 * 可以指定算法的压缩级别，
 * 这样你可以在压缩时间和输出文件大小上进行平衡。
 * 可选的级别有0（不压缩），以及1(快速压缩)到9（慢速压缩）,这里使用的是以速度为优先。
 */
@Component
@Scope("singleton")
public class DeflaterProcess implements CompressProcess {
	@Override
	public byte[] compress(byte input[]) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Deflater compressor = new Deflater(9);
		try {
			compressor.setInput(input);
			compressor.finish();
			final byte[] buf = new byte[2048];
			while (!compressor.finished()) {
				int count = compressor.deflate(buf);
				bos.write(buf, 0, count);
			}
		} finally {
			bos.close();
			compressor.end();
		}
		return bos.toByteArray();
	}
	@Override
	public byte[] uncompress(byte[] input) throws DataFormatException, IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Inflater decompressor = new Inflater();
		try {
			decompressor.setInput(input);
			final byte[] buf = new byte[2048];
			while (!decompressor.finished()) {
				int count = decompressor.inflate(buf);
				bos.write(buf, 0, count);
			}
		} finally {
			bos.close();
			decompressor.end();
		}
		return bos.toByteArray();
	}
}
