package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by xujiaqi on 17.3.11.
 * gzip的实现算法还是deflate，只是在deflate格式上增加了文件头和文件尾，
 * 同样jdk也对gzip提供了支持，分别是GZIPOutputStream和GZIPInputStream类，
 * 同样可以发现GZIPOutputStream是继承于DeflaterOutputStream的
 */
@Component
@Scope("singleton")
public class GzipProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(srcBytes);
			gzip.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		GZIPInputStream ungzip = new GZIPInputStream(in);
		try {
			byte[] buffer = new byte[2048];
			int n;
			while ((n = ungzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ungzip.close();
			out.close();
		}
		
		return out.toByteArray();
	}
}
