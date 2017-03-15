package me.fjnu.compressor.process;

import net.jpountz.lz4.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by xujiaqi on 17.3.11.
 * todo
 */
@Component
@Scope("singleton")
public class Lz4Process implements CompressProcess {
	@Override
	public byte[] compress(byte srcBytes[]) throws IOException {
		LZ4Factory factory = LZ4Factory.fastestInstance();
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		LZ4Compressor compressor = factory.fastCompressor();
		LZ4BlockOutputStream compressedOutput = new LZ4BlockOutputStream(
				byteOutput, 2048, compressor);
		compressedOutput.write(srcBytes);
		compressedOutput.close();
		return byteOutput.toByteArray();
	}
	
	@Override
	public byte[] uncompress(byte[] bytes) throws IOException {
		LZ4Factory factory = LZ4Factory.fastestInstance();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		LZ4FastDecompressor decompresser = factory.fastDecompressor();
		LZ4BlockInputStream lzis = new LZ4BlockInputStream(
				new ByteArrayInputStream(bytes), decompresser);
		int count;
		byte[] buffer = new byte[2048];
		while ((count = lzis.read(buffer)) != -1) {
			baos.write(buffer, 0, count);
		}
		lzis.close();
		baos.close();
		return baos.toByteArray();
	}
}
