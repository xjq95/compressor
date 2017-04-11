package me.fjnu.compressor.process;

import me.fjnu.compressor.domain.CompressInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xerial.snappy.Snappy;

import java.io.IOException;

/**
 * Created by xujiaqi on 17.3.11.
 * snappy（以前称Zippy）是Google基于LZ77的思路用C++语言编写的快速数据压缩与解压程序库
 */
@Component
@Scope("singleton")
public class SnappyProcess implements CompressProcess {
	@Override
	public byte[] compress(byte[] srcBytes, CompressInfo compressInfo) throws IOException {
		return Snappy.compress(srcBytes);
	}
	@Override
	public byte[] uncompress(byte[] bytes, CompressInfo compressInfo) throws IOException {
		return Snappy.uncompress(bytes);
	}
}
