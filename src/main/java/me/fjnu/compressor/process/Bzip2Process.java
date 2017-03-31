package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by xujiaqi on 17.3.11.
 * bzip2是Julian Seward开发并按照自由软件／开源软件协议发布的数据压缩算法及程序。
 * Seward在1996年7月第一次公开发布了bzip2 0.15版，在随后几年中这个压缩工具稳定性得到改善并且日渐流行，Seward在2000年晚些时候发布了1.0版。更多wikibzip2
 * bzip2比传统的gzip的压缩效率更高，但是它的压缩速度较慢。
 */
@Component
@Scope("singleton")
public class Bzip2Process implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BZip2CompressorOutputStream bcos = new BZip2CompressorOutputStream(out);
		bcos.write(srcBytes);
		bcos.close();
		out.close();
		return out.toByteArray();
	}
	
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		BZip2CompressorInputStream ungzip = new BZip2CompressorInputStream(in);
		byte[] buffer = new byte[2048];
		int n;
		while ((n = ungzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		out.close();
		in.close();
		ungzip.close();
		return out.toByteArray();
	}
}
