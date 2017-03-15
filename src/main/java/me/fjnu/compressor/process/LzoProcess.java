package me.fjnu.compressor.process;

import org.anarres.lzo.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by xujiaqi on 17.3.11.
 * LZO是致力于解压速度的一种数据压缩算法，LZO是Lempel-Ziv-Oberhumer的缩写。这个算法是无损算法，
 */
@Component
@Scope("singleton")
public class LzoProcess implements CompressProcess {
	@Override
	public byte[] compress(byte srcBytes[]) throws IOException {
		LzoCompressor compressor = LzoLibrary.getInstance().newCompressor(
				LzoAlgorithm.LZO1X, null);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		LzoOutputStream cs = new LzoOutputStream(os, compressor);
		cs.write(srcBytes);
		cs.close();
		os.close();
		return os.toByteArray();
	}
	@Override
	public byte[] uncompress(byte[] bytes) throws IOException {
		LzoDecompressor decompressor = LzoLibrary.getInstance()
				.newDecompressor(LzoAlgorithm.LZO1X, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		LzoInputStream us = new LzoInputStream(is, decompressor);
		int count;
		byte[] buffer = new byte[2048];
		while ((count = us.read(buffer)) != -1) {
			baos.write(buffer, 0, count);
		}
		us.close();
		baos.close();
		return baos.toByteArray();
	}
}
